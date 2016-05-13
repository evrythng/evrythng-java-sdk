/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

/**
 * Application OAuth client.
 */
public class OAuthClient extends DurableResourceModel {

	private String name;
	private String redirectUrl;

	public String getName() {

		return name;
	}

	public void setName(final String name) {

		this.name = name;
	}

	public String getRedirectUrl() {

		return redirectUrl;
	}

	public void setRedirectUrl(final String redirectUrl) {

		this.redirectUrl = redirectUrl;
	}

}
