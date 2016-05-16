/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.action.jobs.creation;

public class ExternalIdsPrivateFileInput extends PrivateFileCreateActionJobInput {

	public static final String FIELD_KEY = "key";
	private String key;

	public String getKey() {

		return key;
	}

	public void setKey(final String key) {

		this.key = key;
	}

	@Override
	public ContentType getContentType() {

		return ContentType.EXTERNAL_IDS;
	}
}
