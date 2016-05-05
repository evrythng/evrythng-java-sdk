/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

public class PrivateFileBasedAdiInputParameters extends BaseAdiInputParameters {

	public static final String FIELD_FILE_RELATIVE_PATH = "fileRelativePath";
	private String fileRelativePath;

	public static final String FIELD_FORMAT = "format";
	private Format format;

	public String getFileRelativePath() {

		return fileRelativePath;
	}

	public void setFileRelativePath(final String fileRelativePath) {

		this.fileRelativePath = fileRelativePath;
	}

	public Format getFormat() {

		return format;
	}

	public void setFormat(final Format format) {

		this.format = format;
	}
}
