/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.access;

/**
 * Access credentials of an {@link com.evrythng.thng.resource.model.store.Thng}.
 */
public class ThngCredentials {

	private String thngId;
	private String thngApiKey;

	/**
	 * @return the thng id
	 */
	public String getThngId() {

		return thngId;
	}

	/**
	 * @param thngId the thng id to provide access to
	 */
	public void setThngId(final String thngId) {

		this.thngId = thngId;
	}

	/**
	 * @return the thng api key
	 */
	public String getThngApiKey() {

		return thngApiKey;
	}

	/**
	 * @param thngApiKey provided api key of the thing
	 */
	public void setThngApiKey(final String thngApiKey) {

		this.thngApiKey = thngApiKey;
	}

	@Override
	public final boolean equals(final Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ThngCredentials that = (ThngCredentials) o;

		if (thngId != null ? !thngId.equals(that.thngId) : that.thngId != null) {
			return false;
		}
		if (thngApiKey != null ? !thngApiKey.equals(that.thngApiKey) : that.thngApiKey != null) {
			return false;
		}

		return true;
	}

	@Override
	public final int hashCode() {

		int result = thngId != null ? thngId.hashCode() : 0;
		result = 31 * result + (thngApiKey != null ? thngApiKey.hashCode() : 0);
		return result;
	}
}
