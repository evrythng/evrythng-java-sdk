/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code search} query param.
 */
public class SearchQueryParamValue extends QueryParamValue {

	public static final String NAME = "search";

	public SearchQueryParamValue(String value) {

		super(NAME, value);
	}

	/**
	 * @param search search
	 * @return query parameter for this search
	 */
	public static SearchQueryParamValue search(String search) {

		return new SearchQueryParamValue(search);
	}
}
