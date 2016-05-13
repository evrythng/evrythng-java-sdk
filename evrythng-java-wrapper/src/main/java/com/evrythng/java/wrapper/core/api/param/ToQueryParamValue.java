/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code "to"} query param.
 */
public class ToQueryParamValue extends QueryParamValue {

	public static final String NAME = "to";

	public ToQueryParamValue(final String value) {
		super(NAME, value);
	}

	/**
	 * @param to
	 *            from value
	 * @return query parameter for this from value
	 */
	public static ToQueryParamValue to(final String to) {
		return new ToQueryParamValue(to);
	}
}
