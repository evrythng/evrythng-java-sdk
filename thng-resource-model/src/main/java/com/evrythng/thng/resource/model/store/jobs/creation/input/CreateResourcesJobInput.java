/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.jobs.creation.input;

import com.evrythng.thng.resource.model.store.jobs.creation.CreateResourcesJob;

abstract class CreateResourcesJobInput implements CreateResourcesJob.Input {

	private Type type;

	@Override
	public void setType(final Type type) {

		this.type = type;
	}

	@Override
	public Type getType() {

		return type;
	}
}
