/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.action.jobs.creation;

public final class ExternalIdsListInput extends ListCreateActionJobInput {

	private String key;

	public String getKey() {

		return key;
	}

	public void setKey(final String key) {

		this.key = key;
	}

	@Override
	public Type getType() {

		return Type.LIST;
	}

	@Override
	public ContentType getContentType() {

		return ContentType.EXTERNAL_IDS;
	}
}
