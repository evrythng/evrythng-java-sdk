/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.service;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.java.wrapper.core.api.param.AfterQueryParamValue;
import com.evrythng.java.wrapper.core.api.param.SearchQueryParamValue;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.java.wrapper.exception.EvrythngException;
import com.evrythng.java.wrapper.util.FileUtils;
import com.evrythng.thng.resource.model.core.FileToSign;
import com.evrythng.thng.resource.model.core.SignedUploadRequest;
import com.evrythng.thng.resource.model.store.File;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;

/**
 * Service wrapper for the {@code /files} endpoint.
 **/
public class FileService extends EvrythngServiceBase {

	public static final String PATH_FILES = "/files";
	public static final String PATH_FILE = PATH_FILES + "/%s";
	public static final String PATH_SIGNATURE = PATH_FILES + "/signatures";

	/**
	 * @param api {@link ApiManager} instance
	 */
	public FileService(final ApiManager api) {

		super(api);
	}

	/**
	 * Finds files.
	 *
	 * @param after find files starting from file name {@code after} or if {@code null} find from the beginning.
	 *
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<List<File>> filesReader(final String after) throws EvrythngClientException {

		return get(PATH_FILES, new TypeReference<List<File>>() {

		}).queryParam(AfterQueryParamValue.after(after));
	}

	/**
	 * Finds files.
	 *
	 * @param after        find files starting from file name {@code after} or if {@code null} find from the beginning.
	 * @param searchString only find files starting with search string or {@code null} to find all files.
	 *
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<List<File>> filesReader(final String after, final String searchString) throws EvrythngClientException {

		return filesReader(after).queryParam(SearchQueryParamValue.search(searchString));
	}

	/**
	 * Find file by name.
	 *
	 * @param name file name.
	 *
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<File> fileReader(final String name) throws EvrythngClientException {

		return get(String.format(PATH_FILE, urlEncodePathPart(name)), new TypeReference<File>() {

		});
	}

	/**
	 * Delete a file by name.
	 *
	 * @param name file name.
	 *
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<Boolean> fileDeleter(final String name) throws EvrythngClientException {

		return delete(String.format(PATH_FILE, urlEncodePathPart(name)));
	}

	/**
	 * Obtain the signatures necessary to upload files to the Cloud.
	 *
	 * @param toSign list of pairs {@code fileName} and {@code contentType}.
	 *
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<List<SignedUploadRequest>> fileUploadRequestsSigner(final List<FileToSign> toSign)
			throws EvrythngClientException {

		Builder<List<SignedUploadRequest>> b = get(PATH_SIGNATURE, new TypeReference<List<SignedUploadRequest>>() {

		});
		int i = 0;
		for (FileToSign f : toSign) {
			if (f.getName() != null) {
				b.queryParam("name" + i, f.getName());
			}
			if (f.getType() != null) {
				b.queryParam("type" + i, f.getType());
			}
			++i;
		}
		return b;
	}

	/**
	 * Obtains a signed url where to upload and uploads there a single {@link java.io.File}. File name for the Cloud will be
	 * determined from {@link java.io.File} provided. Will try to determine content type from {@link java.io.File} provided.
	 *
	 * @param file {@link java.io.File} to upload.
	 *
	 * @return {@link SignedUploadRequest} instance containing url.
	 */
	public SignedUploadRequest uploadSingleFile(final java.io.File file) throws EvrythngException, IOException {

		final String fileName = file.getName();
		final String contentType = URLConnection.guessContentTypeFromName(fileName);
		return uploadSingleFile(new FileToSign(fileName, contentType), file);
	}

	/**
	 * Obtains a signed url where to upload and uploads there a single {@link java.io.File}.
	 *
	 * @param toSign a pair {@code fileName} and {@code contentType}.
	 * @param file   {@link java.io.File} to upload.
	 *
	 * @return {@link SignedUploadRequest} instance containing url.
	 */
	public SignedUploadRequest uploadSingleFile(final FileToSign toSign, final java.io.File file)
			throws EvrythngException, IOException {

		final List<FileToSign> toSignList = Collections.singletonList(toSign);
		final List<SignedUploadRequest> signedUploadRequests = fileUploadRequestsSigner(toSignList).execute();
		final SignedUploadRequest signedUploadRequest = signedUploadRequests.get(0);
		final URL url = new URL(signedUploadRequest.getSignedUploadUrl());
		FileUtils.uploadFile(url, toSign.getType(), file);
		return signedUploadRequest;
	}

	/**
	 * Obtains a signed url where to upload and uploads there text content.
	 *
	 * @param toSign  a pair {@code fileName} and {@code contentType}.
	 * @param content text content.
	 *
	 * @return {@link SignedUploadRequest} instance containing url.
	 */
	public SignedUploadRequest uploadSingleFile(final FileToSign toSign, final String content)
			throws EvrythngException, IOException {

		final List<FileToSign> toSignList = Collections.singletonList(toSign);
		final List<SignedUploadRequest> signedUploadRequests = fileUploadRequestsSigner(toSignList).execute();
		final SignedUploadRequest signedUploadRequest = signedUploadRequests.get(0);
		final URL url = new URL(signedUploadRequest.getSignedUploadUrl());
		FileUtils.uploadContent(url, toSign.getType(), content);
		return signedUploadRequest;
	}

	/**
	 * Obtains a signed url where to upload and uploads there data from {@link InputStream} provided.
	 *
	 * @param toSign a pair {@code fileName} and {@code contentType}.
	 * @param stream {@link InputStream} where to read from. Should be closed externally.
	 *
	 * @return {@link SignedUploadRequest} instance containing url.
	 */
	public SignedUploadRequest uploadSingleFile(final FileToSign toSign, final InputStream stream)
			throws EvrythngException, IOException {

		final List<FileToSign> toSignList = Collections.singletonList(toSign);
		final List<SignedUploadRequest> signedUploadRequests = fileUploadRequestsSigner(toSignList).execute();
		final SignedUploadRequest signedUploadRequest = signedUploadRequests.get(0);
		final URL url = new URL(signedUploadRequest.getSignedUploadUrl());
		FileUtils.uploadStream(url, toSign.getType(), stream);
		return signedUploadRequest;
	}
}
