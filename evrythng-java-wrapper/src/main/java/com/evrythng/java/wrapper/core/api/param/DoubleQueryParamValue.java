/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * A {@link com.evrythng.java.wrapper.core.api.QueryParamValue} subclass that holds double value.
 */
public class DoubleQueryParamValue extends QueryParamValue {

	public DoubleQueryParamValue(final String key, final double value) {
		super(key, Double.toString(value));
	}
}
