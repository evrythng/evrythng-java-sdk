/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * A {@link QueryParamValue} subclass that holds integer value.
 */
public class IntegerQueryParamValue extends QueryParamValue {

	public IntegerQueryParamValue(final String key, final int value) {
		super(key, Integer.toString(value));
	}
}
