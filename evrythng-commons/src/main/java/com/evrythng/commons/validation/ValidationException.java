/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.commons.validation;

public final class ValidationException extends IllegalArgumentException {

	private static final long serialVersionUID = 4711631435232483273L;

	private static final String TEMPLATE = "Precondition ``%s`` failed for target ``%s``";

	public static ValidationException of(final Object target, final String preconditionName) {

		return new ValidationException(target, preconditionName);
	}

	private ValidationException(final Object target, final String preconditionName) {

		super(String.format(TEMPLATE, preconditionName, target));
	}
}
