/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;
import com.evrythng.thng.commons.config.ApiConfiguration;

/**
 * Provides support for the {@code "from"} query param.
 */
public class FromQueryParamValue extends QueryParamValue {

	public static final String NAME = "from";

	public FromQueryParamValue(final String value) {
		super(NAME, value);
	}

	/**
	 * @param from
	 *            from value
	 * @return query parameter for this from value
	 */
	public static FromQueryParamValue from(final String from) {
		return new FromQueryParamValue(from);
	}

	public static FromQueryParamValue zero() {
		return new FromQueryParamValue("0");
	}
	/**
	 * 
	 * @param from
	 *            from value
	 * @return true if value equals {@code "latest"}
	 */
	public static boolean isLatest(final String from) {
		return ApiConfiguration.QueryKeyword.LATEST.toString().equals(from);
	}
}
