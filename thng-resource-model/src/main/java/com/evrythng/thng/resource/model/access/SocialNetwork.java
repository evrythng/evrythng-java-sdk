/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.access;

/**
 * SocialNetwork access of an {@link com.evrythng.thng.resource.model.store.Application}. Contains the appId, e.g.
 * facebook application id.
 * As defined on the social network side.
 */
public class SocialNetwork {

	public SocialNetwork() {
		// nop
	}

	/**
	 * Create a SocialNetwork having the give appId set
	 */
	public SocialNetwork(final String appId) {

		this.appId = appId;
		this.appSecret = null;
	}

	private String appId;
	private String appSecret;

	/**
	 * @return the appId
	 */
	public String getAppId() {

		return appId;
	}

	/**
	 * @param appId the appId to set
	 */
	public void setAppId(final String appId) {

		this.appId = appId;
	}

	/**
	 * @return the appSecret
	 */
	public String getAppSecret() {

		return appSecret;
	}

	/**
	 * @param appSecret the appSecret to set
	 */
	public void setAppSecret(final String appSecret) {

		this.appSecret = appSecret;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + (appId == null ? 0 : appId.hashCode());
		result = prime * result + (appSecret == null ? 0 : appSecret.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		SocialNetwork other = (SocialNetwork) obj;
		if (appId == null) {
			if (other.appId != null) {
				return false;
			}
		} else if (!appId.equals(other.appId)) {
			return false;
		}
		if (appSecret == null) {
			if (other.appSecret != null) {
				return false;
			}
		} else if (!appSecret.equals(other.appSecret)) {
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {

		return "[appId=" + appId + "]";
	}
}
