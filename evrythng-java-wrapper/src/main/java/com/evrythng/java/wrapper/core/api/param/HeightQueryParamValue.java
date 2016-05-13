/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api.param;

/**
 * Provides support for the {@code "h"} query param.
 */
public class HeightQueryParamValue extends IntegerQueryParamValue {

	public static final String NAME = "h";

	public HeightQueryParamValue(final int value) {
		super(NAME, value);
	}

	/**
	 * 
	 * @param height
	 *            height
	 * @return query parameter for height
	 */
	public static HeightQueryParamValue height(final int height) {
		return new HeightQueryParamValue(height);
	}
}
