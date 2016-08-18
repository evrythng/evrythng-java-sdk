/*
 *
 *  * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 *  * Use of this material is subject to license.
 *  * Copying and unauthorised use of this material strictly prohibited.
 *
 */

package com.evrythng.java.wrapper.service;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.thng.resource.model.store.File;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * This class provides a wrapper around version 2 of the Files REST API (/v2/files).
 *
 */
public class FilesService extends EvrythngServiceBase {

	private static final Logger LOG = LoggerFactory.getLogger(FilesService.class);

	private static final String PATH_V2_FILES = "/v2/files";
	private static final String PATH_V2_FILES_BY_ID = PATH_V2_FILES + "/%s";

	public FilesService(ApiManager apiManager) {

		super(apiManager);
	}

	/**
	 * Creates a metadata record for the given filename, with an 'uploadUrl' that can be used by a client to upload a file using HTTP PUT.
	 * The 'uploadUrl' expires after 30 minutes, regardless of whether the file will have 'public' or 'private' access.
	 *
	 * @param fileName name of the file to be uploaded.
	 * @param fileType mime-type of file to be uploaded, e.g. text/plain, image/jpg, etc.
	 * @param privateAccess flag indicating whether or not the file should be privately accessible on remote storage. A value of false means the file will be accessible with a public URL.
	 * @return a {@code File} metadata instance, with an 'uploadUrl' attribute.
	 */
	public File createFileRecordWithUploadUrl(String fileName, String fileType, Boolean privateAccess) {

		File file = new File();
		file.setName(fileName);
		file.setType(fileType);
		file.setPrivateAccess(privateAccess);

		return post(PATH_V2_FILES, file, new TypeReference<File>() {}).execute();
	}

	/**
	 * Looks up a {@code File} metadata record by id. The returned object will have a 'contentUrl' attribute that can be used by a client to download the remote file using HTTP GET.
	 * The 'contentUrl' expires after 30 minutes if the file was marked as 'private'.
	 *
	 * @param id id of the file to retrieve metadata for.
	 * @return a {@code File} metadata instance, with an 'contentUrl' attribute.
	 */
	public File findFileById(String id) {

		return get(String.format(PATH_V2_FILES_BY_ID, id), new TypeReference<File>() {}).execute();
	}

	/**
	 * Deletes a {@code File} metadata record by id. <b>This method will also delete the actual physical file on remote storage.</b>
	 *
	 * @param id id of the file to be deleted.
	 * @return boolean response indicating whether the delete operation succeeded or failed.
	 */
	public Boolean deleteFileById(String id) {

		return delete(String.format(PATH_V2_FILES_BY_ID, id)).execute();
	}

	/**
	 * Looks up a list of {@code File} metadata records based on the provided filter.
	 *
	 * @param filter filter string to be used as criteria for looking up {@code File} records.
	 * @return list of {@code File} metadata records based on the provided filter.
	 */
	public List<File> filesBuilder(String filter) {

		return get(PATH_V2_FILES, new TypeReference<List<File>>() {}).filter(filter).execute();
	}
}
