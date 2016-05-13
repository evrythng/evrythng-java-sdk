/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * A {@link com.evrythng.java.wrapper.core.api.QueryParamValue} subclass that holds long value.
 */
public class LongQueryParamValue extends QueryParamValue {

	public LongQueryParamValue(final String key, final long value) {
		super(key, Long.toString(value));
	}
}
