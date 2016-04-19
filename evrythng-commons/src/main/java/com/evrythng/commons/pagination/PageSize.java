/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.commons.pagination;

import com.evrythng.commons.domain.Value;

import static com.evrythng.commons.validation.preconditions.GreaterThanOrEqualTo.greaterThanOrEqualTo;

public final class PageSize extends Value<Integer> {

	public static PageSize of(final Integer value) {

		return new PageSize(value);
	}

	private PageSize(final Integer value) {

		super(value, greaterThanOrEqualTo(0));
	}
}
