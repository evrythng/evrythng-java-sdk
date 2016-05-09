/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.commons.domain;

import static com.evrythng.commons.validation.preconditions.GreaterThanOrEqualTo.greaterThanOrEqualTo;

public final class Size extends Value<Integer> {

	public static Size of(final Integer value) {

		return new Size(value);
	}

	private Size(final Integer value) {

		super(value, greaterThanOrEqualTo(0));
	}

	public final Integer value() {

		return internalValue();
	}
}
