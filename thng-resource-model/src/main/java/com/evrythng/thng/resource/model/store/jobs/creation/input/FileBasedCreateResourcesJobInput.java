/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.jobs.creation.input;

public class FileBasedCreateResourcesJobInput extends CreateResourcesJobInput {

	public FileBasedCreateResourcesJobInput() {

		setType(Type.FILE_BASED);
	}
}
