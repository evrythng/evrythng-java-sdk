/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code "ecl"} query param.
 */
public class EclQueryParamValue extends QueryParamValue {

	public static final String NAME = "ecl";

	public EclQueryParamValue(final String value) {
		super(NAME, value);
	}

	/**
	 * @param ecl
	 *            error correction level
	 * @return query parameter for this error correction level
	 */
	public static EclQueryParamValue ecl(final String ecl) {
		return new EclQueryParamValue(ecl);
	}
}
