/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api.param;

/**
 * Provides support for the {@code "perPage"} query param.
 */
public class PerPageQueryParamValue extends IntegerQueryParamValue {

	public static final String NAME = "perPage";

	public PerPageQueryParamValue(final int value) {
		super(NAME, value);
	}

	/**
	 * 
	 * @param perPage
	 *            items per page
	 * @return query parameter for this items per page count
	 */
	public static PerPageQueryParamValue perPage(final int perPage) {
		return new PerPageQueryParamValue(perPage);
	}
}
