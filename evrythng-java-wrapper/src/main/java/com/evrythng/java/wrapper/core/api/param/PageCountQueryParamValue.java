/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

public class PageCountQueryParamValue extends QueryParamValue {

	public static final String NAME = "pageCount";

	public PageCountQueryParamValue(final String value) {

		super(NAME, value);
	}

	public static PageCountQueryParamValue of(final String value) {

		return new PageCountQueryParamValue(value);
	}
}
