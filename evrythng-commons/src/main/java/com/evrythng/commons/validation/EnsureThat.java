/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
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
