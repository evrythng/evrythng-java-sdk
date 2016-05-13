/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api.param;

/**
 * Provides support for the {@code lon} query param.
 */
public class LonQueryParamValue extends DoubleQueryParamValue {

	public static final String NAME = "lon";

	public LonQueryParamValue(final double value) {
		super(NAME, value);
	}

	/**
	 * @param longitude
	 *            longitude
	 * @return query parameter for this longitude
	 */
	public static LonQueryParamValue lon(final double longitude) {
		return new LonQueryParamValue(longitude);
	}
}
