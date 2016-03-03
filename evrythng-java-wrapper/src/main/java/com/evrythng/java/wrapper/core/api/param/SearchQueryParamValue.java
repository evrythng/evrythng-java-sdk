/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
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
