/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store;

import java.util.List;

public class ListBasedAdiInputParameters extends BaseAdiInputParameters {

	public static final String FIELD_REDIRECTIONS = "redirections";

	private List<RedirectionResource> redirections;


	public List<RedirectionResource> getRedirections() {

		return redirections;
	}

	public void setRedirections(final List<RedirectionResource> redirections) {

		this.redirections = redirections;
	}
}
