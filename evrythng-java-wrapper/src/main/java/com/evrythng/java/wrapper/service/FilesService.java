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
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;

/**
 * This class provides a wrapper around version 2 of the Files REST API (/files).
 *
 */
public class FilesService extends EvrythngServiceBase {

	private static final Logger LOG = LoggerFactory.getLogger(FilesService.class);

	private static final String PATH_FILES = "/files";
	private static final String PATH_FILES_BY_ID = PATH_FILES + "/%s";

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
	 * @return a {@link File} metadata instance, with an 'uploadUrl' attribute.
	 */
	public File createFileRecordWithUploadUrl(final String fileName, final String fileType, final boolean privateAccess, final String... tags) {

		File file = new File();
		file.setName(fileName);
		file.setType(fileType);
		file.setPrivateAccess(privateAccess);
		if (tags != null && ArrayUtils.isNotEmpty(tags)) {
			file.setTags(Arrays.asList(tags));
		}

		return fileCreator(file).execute();
	}

	/**
	 * Looks up a {@link File} metadata record by id. The returned object will have a 'contentUrl' attribute that can be used by a client to download the remote file using HTTP GET.
	 * The 'contentUrl' expires after 30 minutes if the file was marked as 'private'.
	 *
	 * @param id id of the file to retrieve metadata for.
	 * @return a {@link File} metadata instance, with an 'contentUrl' attribute.
	 */
	public File findFileById(final String id) {

		return fileReader(id).execute();
	}

	/**
	 * Deletes a {@link File} metadata record by id. <b>This method will also delete the actual physical file on remote storage.</b>
	 *
	 * @param id id of the file to be deleted.
	 * @return boolean response indicating whether the delete operation succeeded or failed.
	 */
	public boolean deleteFileById(final String id) {

		return fileDeleter(id).execute();
	}

	/**
	 * Looks up a list of {@link File} metadata records based on the provided filter.
	 *
	 * @param filter filter string to be used as criteria for looking up {@link File} records.
	 * @return list of {@link File} metadata records based on the provided filter.
	 */
	public List<File> findFilesByFilter(final String filter) {

		return filesReader(filter).execute();
	}

	/**
	 * {@link Builder} that creates a new {@link File} instance when it is executed.
	 *
	 * @param file {@link File} to be created.
	 * @return {@link Builder} to create a new {@link File}.
	 */
	public Builder<File> fileCreator(final File file) {

		return post(PATH_FILES, file, new TypeReference<File>() {});
	}

	/**
	 * {@link Builder} that returns a single {@link File} instance by id when executed.
	 *
	 * @param id id of {@link File} to find.
	 * @return {@link Builder} to find a {@link File} by id.
	 */
	public Builder<File> fileReader(final String id) {

		return get(String.format(PATH_FILES_BY_ID, id), new TypeReference<File>() {});
	}

	/**
	 * {@link Builder} that returns a a list of {@link File} instances by filter when executed.
	 *
	 * @param filter filter criteria to apply. Supported are 'tags=tag1,tag2' and 'name=filename'.
	 * @return {@link Builder} to find a list of {@link File}s by filter.
	 */
	public Builder<List<File>> filesReader(final String filter) {

		return get(PATH_FILES, new TypeReference<List<File>>() {}).filter(filter);
	}

	/**
	 * {@link Builder} that deletes a {@link File} by id when executed.
	 *
	 * @param id id of {@link File} to delete.
	 * @return {@link Builder} to delete a {@link File} by id.
	 */
	public Builder<Boolean> fileDeleter(final String id) {

		return delete(String.format(PATH_FILES_BY_ID, id));
	}
}
