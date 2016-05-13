/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code "access_token"} query param.
 */
public class AccessTokenQueryParamValue extends QueryParamValue {

	public static final String NAME = "access_token";

	public AccessTokenQueryParamValue(final String value) {
		super(NAME, value);
	}

	/**
	 * @param key
	 *            access token
	 * @return query parameter for this access token
	 */
	public static AccessTokenQueryParamValue key(final String key) {
		return new AccessTokenQueryParamValue(key);
	}

	/**
	 * @return query parameter for empty access token
	 */
	public static AccessTokenQueryParamValue empty() {
		return new AccessTokenQueryParamValue(null);
	}
}
