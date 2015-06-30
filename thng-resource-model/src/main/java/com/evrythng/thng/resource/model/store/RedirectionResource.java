/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store;

/**
 * One redirection definition.
 */
public class RedirectionResource {

	private String shortId;

	public static final String FIELD_SHORT_ID = "shortId";

	public String getShortId() {

		return shortId;
	}

	public void setShortId(final String shortId) {

		this.shortId = shortId;
	}
}
