/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.exception;

/**
 * Root definition for handling any exception thrown by the wrapper.
 **/
public abstract class EvrythngException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	protected EvrythngException(final String message) {
		super(message);
	}

	protected EvrythngException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
