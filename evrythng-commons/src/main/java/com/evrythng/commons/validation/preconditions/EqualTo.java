/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.commons.validation.preconditions;

public final class EqualTo<TARGET> extends AbstractPrecondition<TARGET> {

	private static final String NAME = "NOT_NULL";
	private final TARGET comparison;

	public static <T> EqualTo<T> equalTo(final T comparison) {

		return new EqualTo<>(comparison);
	}

	private EqualTo(final TARGET comparison) {

		super(NAME);
		this.comparison = comparison;
	}

	@Override
	public final boolean test(final TARGET target) {

		return target.equals(comparison);
	}
}
