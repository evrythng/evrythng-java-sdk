/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.action.jobs.creation;

public abstract class PrivateFileCreateActionJobInput  extends CreateActionJob.Input {

	public static final String FIELD_FILE_RELATIVE_PATH = "fileRelativePath";
	private String fileRelativePath;

	public static final String FIELD_FORMAT = "format";
	private Format format;

	@Override
	public Type getType() {

		return Type.PRIVATE_FILE;
	}

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
