/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
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
