/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.commons.validation.preconditions;

public final class LessThan<TARGET extends Comparable<TARGET>> extends AbstractPrecondition<TARGET> {

	private static final String NAME = "LESS_THAN";
	private final TARGET maximum;

	public static <T extends Comparable<T>> LessThan<T> lessThan(final T maximum) {

		return new LessThan<>(maximum);
	}

	private LessThan(final TARGET maximum) {

		super(NAME);
		this.maximum = maximum;
	}

	@Override
	public final boolean test(final TARGET target) {

		return target.compareTo(maximum) < 0;
	}
}
