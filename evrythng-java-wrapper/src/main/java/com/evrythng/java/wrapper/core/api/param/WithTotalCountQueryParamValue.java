/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
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
