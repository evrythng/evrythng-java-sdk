/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code "callback"} query param.
 */
public class CallbackQueryParamValue extends QueryParamValue {

	public static final String NAME = "callback";

	public CallbackQueryParamValue(final String value) {
		super(NAME, value);
	}

	/**
	 * @param callback
	 *            callback value
	 * @return query parameter for this callback value
	 */
	public static CallbackQueryParamValue callback(final String callback) {
		return new CallbackQueryParamValue(callback);
	}

	/**
	 * @return query parameter for empty callback
	 */
	public static CallbackQueryParamValue empty() {
		return new CallbackQueryParamValue(null);
	}
}
