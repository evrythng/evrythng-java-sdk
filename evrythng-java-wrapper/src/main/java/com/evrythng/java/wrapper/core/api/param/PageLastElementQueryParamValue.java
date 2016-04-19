/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;
import com.evrythng.thng.resource.model.template.WithId;

public final class PageLastElementQueryParamValue extends QueryParamValue {

	private static final String NAME = "pageLastElement";

	public static PageLastElementQueryParamValue of(final String elementId) {

		return new PageLastElementQueryParamValue(elementId);
	}

	public static <RESOURCE extends WithId> PageLastElementQueryParamValue of(final RESOURCE resource) {

		return new PageLastElementQueryParamValue(resource.getId());
	}

	private PageLastElementQueryParamValue(final String value) {

		super(NAME, value);
	}

	public static String name() {

		return NAME;
	}
}
