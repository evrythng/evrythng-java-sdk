/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code "scope"} query param.
 */
public class ScopeQueryParamValue extends QueryParamValue {

	public static final String NAME = "scope";

	public ScopeQueryParamValue(final String scope) {

		super(NAME, scope);
	}

	public static ScopeQueryParamValue valueOf(final String scope) {

		return new ScopeQueryParamValue(scope);
	}
}
