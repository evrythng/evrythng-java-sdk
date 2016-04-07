/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
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
