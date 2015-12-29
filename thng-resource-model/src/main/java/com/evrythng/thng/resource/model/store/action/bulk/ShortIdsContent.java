/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.action.bulk;

public class ShortIdsContent extends Content {

	private static final long serialVersionUID = -875148501481587297L;
	private String shortDomain;

	public ShortIdsContent() {

		setType(Type.EXTERNAL_IDS);
	}

	public String getShortDomain() {

		return shortDomain;
	}

	public void setShortDomain(final String shortDomain) {

		this.shortDomain = shortDomain;
	}
}
