/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;
import com.evrythng.thng.resource.model.core.SortOrder;

public final class SortOrderQueryParamValue extends QueryParamValue {

	private static final String NAME = "sortOrder";

	public static SortOrderQueryParamValue of(final SortOrder value) {

		return new SortOrderQueryParamValue(value);
	}

	private SortOrderQueryParamValue(final SortOrder value) {

		super(NAME, value.direction().name());
	}

	public static String name() {

		return NAME;
	}
}