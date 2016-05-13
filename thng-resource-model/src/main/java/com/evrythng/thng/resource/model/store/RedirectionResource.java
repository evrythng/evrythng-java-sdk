/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
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

	@Override
	public boolean equals(final Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		RedirectionResource that = (RedirectionResource) o;

		if (shortId != null ? !shortId.equals(that.shortId) : that.shortId != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {

		return shortId != null ? shortId.hashCode() : 0;
	}
}
