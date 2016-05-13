/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code "types"} query param.
 */
public class TypesQueryParamValue extends QueryParamValue {

	public static final String NAME = "types";

	public TypesQueryParamValue(final String value) {
		super(NAME, value);
	}

	/**
	 * @param types
	 *            comma separated types
	 * @return query parameter for this types
	 */
	public static TypesQueryParamValue types(final String types) {
		return new TypesQueryParamValue(types);
	}
}
