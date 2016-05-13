/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code "creationScopes"} query param.
 */
public class UserScopeQueryParamValue extends QueryParamValue {

	public static final String NAME = "userScope";

	public UserScopeQueryParamValue(final String value) {

		super(NAME, value);
	}

	public static UserScopeQueryParamValue valueOf(final String value) {

		return new UserScopeQueryParamValue(value);
	}
}
