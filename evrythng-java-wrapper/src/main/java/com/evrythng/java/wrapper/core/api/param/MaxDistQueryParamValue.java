/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api.param;

/**
 * Provides support for the {@code maxDist} query param.
 */
public class MaxDistQueryParamValue extends DoubleQueryParamValue {

	public static final String NAME = "maxDist";

	public MaxDistQueryParamValue(final double value) {
		super(NAME, value);
	}

	/**
	 * @param maxDist
	 *            maxDist
	 * @return query parameter for this maxDist
	 */
	public static MaxDistQueryParamValue maxDist(final double maxDist) {
		return new MaxDistQueryParamValue(maxDist);
	}
}
