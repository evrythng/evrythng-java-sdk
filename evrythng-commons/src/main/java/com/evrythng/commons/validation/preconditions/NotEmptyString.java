/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
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
