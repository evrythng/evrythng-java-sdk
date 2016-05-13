/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code "q"} query param.
 */
public class QSearchQueryParamValue extends QueryParamValue {

	public static final String NAME = "q";

	public QSearchQueryParamValue(final String value) {
		super(NAME, value);
	}

	/**
	 * @param pattern
	 *            search pattern
	 * @return query parameter for this search pattern
	 */
	public static QSearchQueryParamValue pattern(final String pattern) {
		return new QSearchQueryParamValue(pattern);
	}
}
