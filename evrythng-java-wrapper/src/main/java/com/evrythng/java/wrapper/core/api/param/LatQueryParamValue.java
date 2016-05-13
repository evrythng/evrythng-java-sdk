/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api.param;

/**
 * Provides support for the {@code lat} query param.
 */
public class LatQueryParamValue extends DoubleQueryParamValue {

	public static final String NAME = "lat";

	public LatQueryParamValue(final double value) {
		super(NAME, value);
	}

	/**
	 * @param latitude
	 *            latitude
	 * @return query parameter for this latitude
	 */
	public static LatQueryParamValue lat(final double latitude) {
		return new LatQueryParamValue(latitude);
	}
}
