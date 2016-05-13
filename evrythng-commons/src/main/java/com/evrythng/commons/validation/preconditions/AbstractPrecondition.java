/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.commons.validation.preconditions;

import com.evrythng.commons.validation.Precondition;
import com.evrythng.commons.validation.ValidationException;

abstract class AbstractPrecondition<TARGET> implements Precondition<TARGET> {

	private final String preconditionName;

	protected AbstractPrecondition(final String preconditionName) {

		this.preconditionName = preconditionName;
	}

	@Override
	public final void testThrowing(final TARGET target) throws ValidationException {

		boolean result = test(target);
		if (!result) {
			throw ValidationException.of(target, preconditionName);
		}
	}

	@Override
	public final String name() {

		return preconditionName;
	}

	@Override
	public final Precondition<TARGET> or(final Precondition<TARGET> other) {

		return new AbstractPrecondition<TARGET>(name() + " OR " + other.name()) {

			@Override
			public boolean test(final TARGET target) {

				return AbstractPrecondition.this.test(target) || other.test(target);
			}
		};
	}
}
