/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.commons.validation.preconditions;

public final class NotEmptyString extends AbstractPrecondition<String> {

	private static final String NAME = "NOT_EMPTY_STRING";

	public static NotEmptyString notEmptyString() {

		return new NotEmptyString();
	}

	private NotEmptyString() {

		super(NAME);
	}

	@Override
	public final boolean test(final String target) {

		return !target.isEmpty();
	}
}
