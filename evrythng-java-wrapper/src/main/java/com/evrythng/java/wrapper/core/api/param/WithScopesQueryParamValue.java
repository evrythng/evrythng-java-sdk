/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code withScopes} query param.
 */
public class WithScopesQueryParamValue extends QueryParamValue {

	public static final String NAME = "withScopes";

	/**
	 * @param value {@code "true"} or {@code "false"}
	 */
	public WithScopesQueryParamValue(final String value) {

		super(NAME, value);
	}

	/**
	 * @param value {@code "true"} or {@code "false"}
	 */
	public static WithScopesQueryParamValue valueOf(final String value) {

		return new WithScopesQueryParamValue(value);
	}
}
