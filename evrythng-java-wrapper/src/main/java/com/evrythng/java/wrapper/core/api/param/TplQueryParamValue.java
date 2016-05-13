/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code "tpl"} query param.
 */
public class TplQueryParamValue extends QueryParamValue {

	public static final String NAME = "tpl";
	private static final String DEFAULT_SHORTID = "default-shortid";
	private static final String DEFAULT = "default";
	private static final String DEMO_EVRYTHNG = "demo-evrythng";

	public TplQueryParamValue(final String value) {
		super(NAME, value);
	}

	/**
	 * @param template
	 *            template
	 * @return query parameter for this template
	 */
	public static TplQueryParamValue template(final String template) {
		return new TplQueryParamValue(template);
	}

	/**
	 * @param template
	 *            template
	 * @return true if app id equals {@code "default-shortid"}
	 */
	public static boolean isDefaultShortId(final String template) {
		return DEFAULT_SHORTID.equals(template);
	}

	/**
	 * @param template
	 *            template
	 * @return true if app id equals {@code "default"}
	 */
	public static boolean isDefault(final String template) {
		return DEFAULT.equals(template);
	}

	/**
	 * @param template
	 *            template
	 * @return true if app id equals {@code "demo-evrythng"}
	 */
	public static boolean isDemoEvrythng(final String template) {
		return DEMO_EVRYTHNG.equals(template);
	}
}
