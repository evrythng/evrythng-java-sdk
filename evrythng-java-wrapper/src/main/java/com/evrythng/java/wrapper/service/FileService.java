/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.service;

import java.util.List;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.java.wrapper.core.api.param.AfterQueryParamValue;
import com.evrythng.java.wrapper.core.api.param.SearchQueryParamValue;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.thng.resource.model.core.FileToSign;
import com.evrythng.thng.resource.model.core.SignedUploadRequest;
import com.evrythng.thng.resource.model.store.File;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Service wrapper for the {@code /files} endpoint.
 * 
 **/
public class FileService extends EvrythngServiceBase {

	public static final String PATH_FILES = "/files";
	public static final String PATH_FILE = PATH_FILES + "/%s";
	public static final String PATH_SIGNATURE = PATH_FILES + "/signatures";

	public FileService(ApiManager api) {
		super(api);
	}

	public Builder<List<File>> filesReader(String after) throws EvrythngClientException {
		return get(PATH_FILES, new TypeReference<List<File>>() {

		}).queryParam(AfterQueryParamValue.after(after));
	}

	public Builder<List<File>> filesReader(String after, String searchString) throws EvrythngClientException {

		return filesReader(after).queryParam(SearchQueryParamValue.search(searchString));
	}

	public Builder<File> fileReader(String name) throws EvrythngClientException {
		return get(String.format(PATH_FILE, urlEncodePathPart(name)), new TypeReference<File>() {
		});
	}

	public Builder<Boolean> fileDeleter(String name) throws EvrythngClientException {
		return delete(String.format(PATH_FILE, urlEncodePathPart(name)));
	}

	public Builder<List<SignedUploadRequest>> fileUploadRequestsSigner(List<FileToSign> toSign) throws EvrythngClientException {
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

}
