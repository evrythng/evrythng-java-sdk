/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
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
