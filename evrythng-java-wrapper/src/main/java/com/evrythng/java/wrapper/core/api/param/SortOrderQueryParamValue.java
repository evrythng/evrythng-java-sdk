/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.commons.domain.SortOrder;
import com.evrythng.java.wrapper.core.api.QueryParamValue;

public final class SortOrderQueryParamValue extends QueryParamValue {

	public static final String NAME = "sortOrder";

	public static SortOrderQueryParamValue of(final SortOrder value) {

		return new SortOrderQueryParamValue(value);
	}

	private SortOrderQueryParamValue(final SortOrder value) {

		super(NAME, value.direction().name());
	}
}