/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.action.jobs.creation;

public final class ShortIdsListInput extends ListCreateActionJobInput {

	private String shortDomain;

	public String getShortDomain() {

		return shortDomain;
	}

	public void setShortDomain(final String shortDomain) {

		this.shortDomain = shortDomain;
	}

	@Override
	public Type getType() {

		return Type.LIST;
	}

	@Override
	public ContentType getContentType() {

		return ContentType.SHORT_IDS;
	}
}
