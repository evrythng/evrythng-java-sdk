/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code after} query param.
 */
public class AfterQueryParamValue extends QueryParamValue {

	public static final String NAME = "after";

	public AfterQueryParamValue(String value) {

		super(NAME, value);
	}

	/**
	 * @param after after
	 * @return query parameter for this after
	 */
	public static AfterQueryParamValue after(String after) {

		return new AfterQueryParamValue(after);
	}
}
