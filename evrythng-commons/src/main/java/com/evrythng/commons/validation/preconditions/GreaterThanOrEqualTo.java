/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.commons.validation.preconditions;

public final class GreaterThanOrEqualTo<TARGET extends Comparable<TARGET>> extends AbstractPrecondition<TARGET> {

	private static final String NAME = "GREATER_THAN_OR_EQUAL_TO";
	private final TARGET minimum;

	public static <T extends Comparable<T>> GreaterThanOrEqualTo<T> greaterThanOrEqualTo(final T minimum) {

		return new GreaterThanOrEqualTo<>(minimum);
	}

	private GreaterThanOrEqualTo(final TARGET minimum) {

		super(NAME);
		this.minimum = minimum;
	}

	@Override
	public final boolean test(final TARGET target) {

		return target.compareTo(minimum) >= 0;
	}
}
