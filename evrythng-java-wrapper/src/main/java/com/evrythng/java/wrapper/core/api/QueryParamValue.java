/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api;

/**
 * Holds a key and a value for a query param.
 * 
 **/
public class QueryParamValue {

	private final String key;
	private final String value;

	public QueryParamValue(final String key, final String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
}
