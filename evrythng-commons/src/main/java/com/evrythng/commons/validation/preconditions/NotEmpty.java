/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.commons.validation.preconditions;

import java.util.Collection;

public final class NotEmpty<COLLECTION extends Collection<ELEMENT>, ELEMENT> extends AbstractPrecondition<COLLECTION> {

	private static final String NAME = "NOT_EMPTY_COLLECTION";

	public static <T extends Collection<E>, E> NotEmpty<T, E> notEmpty() {

		return new NotEmpty();
	}

	private NotEmpty() {

		super(NAME);
	}

	@Override
	public final boolean test(final COLLECTION target) {

		return !target.isEmpty();
	}
}
