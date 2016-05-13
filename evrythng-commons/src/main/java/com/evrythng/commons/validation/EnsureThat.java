/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.commons.validation;

public final class EnsureThat {

	@SafeVarargs
	public static <TARGET> void ensureThat(final TARGET target, final Precondition<TARGET> first, final Precondition<TARGET>... preconditions) throws ValidationException {

		first.testThrowing(target);
		for (Precondition<TARGET> precondition : preconditions) {
			precondition.testThrowing(target);
		}
	}

	private EnsureThat() {
		// no instances
	}
}
