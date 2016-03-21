/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.thng.jobs.creation;

import com.evrythng.thng.resource.model.li.UrlBinding;
import com.evrythng.thng.resource.model.store.Thng;
import com.fasterxml.jackson.annotation.JsonIgnore;

public final class ThngCreationBundle {

	private Thng thng;
	private UrlBinding redirection;

	public ThngCreationBundle() {

	}

	public ThngCreationBundle(final Thng thng, final UrlBinding redirection) {

		this.thng = thng;
		this.redirection = redirection;
	}

	public Thng getThng() {

		return thng;
	}

	public UrlBinding getRedirection() {

		return redirection;
	}

	public void setThng(final Thng thng) {

		this.thng = thng;
	}

	public void setRedirection(final UrlBinding redirection) {

		this.redirection = redirection;
	}

	@JsonIgnore
	public boolean hasThng() {

		return thng != null;
	}

	@JsonIgnore
	public boolean hasRedirection() {

		return redirection != null;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder("{");
		sb.append("thng=").append(thng);
		sb.append(", redirection=").append(redirection);
		sb.append('}');
		return sb.toString();
	}
}
