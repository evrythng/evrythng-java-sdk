/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api.param;

/**
 * Provides support for the {@code "page"} query param.
 */
public class PageQueryParamValue extends IntegerQueryParamValue {

	public static final String NAME = "page";

	public PageQueryParamValue(final int value) {
		super(NAME, value);
	}

	/**
	 * 
	 * @param page
	 *            page number
	 * @return query parameter for this page number
	 */
	public static PageQueryParamValue page(final int page) {
		return new PageQueryParamValue(page);
	}
}
