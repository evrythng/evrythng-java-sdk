/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.jobs.creation.input;

import com.evrythng.thng.resource.model.store.files.FileLocationResource;

public abstract class FileBasedCreateResourcesJobInput extends CreateResourcesJobInput {

	private FileLocationResource file;

	protected FileBasedCreateResourcesJobInput() {

		setType(Type.FILE_BASED);
	}

	public FileLocationResource getFile() {

		return file;
	}

	public void setFile(final FileLocationResource file) {

		this.file = file;
	}
}
