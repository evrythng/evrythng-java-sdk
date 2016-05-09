/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.commons.validation.preconditions;

public final class NotNull<TARGET> extends AbstractPrecondition<TARGET> {

	private static final String NAME = "NOT_NULL";

	public static <T> NotNull<T> notNull() {

		return new NotNull<>();
	}

	private NotNull() {

		super(NAME);
	}

	@Override
	public final boolean test(final TARGET target) {

		return target != null;
	}
}
