/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.action.jobs.creation;

public final class ExternalIdsFileInput extends FileCreateActionJobInput {

	private String key;

	public String getKey() {

		return key;
	}

	public void setKey(final String key) {

		this.key = key;
	}
}
