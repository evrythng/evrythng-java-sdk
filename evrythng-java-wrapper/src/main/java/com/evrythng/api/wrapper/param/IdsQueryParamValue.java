/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.api.wrapper.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;
import org.apache.commons.lang3.StringUtils;

/**
 * Provides support for the {@code ids} query param.
 */
public class IdsQueryParamValue extends QueryParamValue {

	public static final String NAME = "ids";

	public IdsQueryParamValue(final String value) {

		super(NAME, value);
	}

	/**
	 * @param ids comma separated ids
	 * @return query parameter for this ids
	 */
	public static IdsQueryParamValue ids(final String ids) {

		return new IdsQueryParamValue(ids);
	}

	/**
	 * @param ids collection of ids
	 * @return query parameter for this ids
	 */
	public static IdsQueryParamValue ids(final Iterable<String> ids) {

		return new IdsQueryParamValue(StringUtils.join(ids, ","));
	}
}
