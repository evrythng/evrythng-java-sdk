/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

public final class WithTotalCountQueryParamValue extends QueryParamValue {

	public static final String NAME = "withTotalCount";

	private WithTotalCountQueryParamValue(final String value) {
		super(NAME, value);
	}

	public static WithTotalCountQueryParamValue of(final String from) {
		return new WithTotalCountQueryParamValue(from);
	}
}
