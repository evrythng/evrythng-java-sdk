/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code tags} query param.
 */
public class TagsQueryParamValue extends QueryParamValue {

	public static final String NAME = "tags";

	public TagsQueryParamValue(final String value) {
		super(NAME, value);
	}

	/**
	 * @param tags
	 *            tags
	 * @return query parameter for this tags
	 */
	public static TagsQueryParamValue tags(final String tags) {
		return new TagsQueryParamValue(tags);
	}
}
