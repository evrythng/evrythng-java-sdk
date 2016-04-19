/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.commons.validation.preconditions;

public final class GreaterThan<TARGET extends Comparable<TARGET>> extends AbstractPrecondition<TARGET> {

	private static final String NAME = "GREATER_THAN";
	private final TARGET minimum;

	public static <T extends Comparable<T>> GreaterThan<T> greaterThan(final T minimum) {

		return new GreaterThan<>(minimum);
	}

	private GreaterThan(final TARGET minimum) {

		super(NAME);
		this.minimum = minimum;
	}

	@Override
	public final boolean test(final TARGET target) {

		return target.compareTo(minimum) > 0;
	}
}
