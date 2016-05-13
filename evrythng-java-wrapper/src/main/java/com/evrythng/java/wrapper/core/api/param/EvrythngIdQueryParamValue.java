/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code "evrythngId"} query param.
 */
public class EvrythngIdQueryParamValue extends QueryParamValue {

	public static final String NAME = "evrythngId";

	public EvrythngIdQueryParamValue(final String value) {
		super(NAME, value);
	}

	/**
	 * @param id
	 *            evrythng id
	 * @return query parameter for this evrythng id
	 */
	public static EvrythngIdQueryParamValue id(final String id) {
		return new EvrythngIdQueryParamValue(id);
	}
}
