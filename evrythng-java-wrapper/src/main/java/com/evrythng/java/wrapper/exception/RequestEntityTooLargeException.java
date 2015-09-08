/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.java.wrapper.exception;

import com.evrythng.java.wrapper.core.http.Status;
import com.evrythng.thng.resource.model.exception.ErrorMessage;

/**
 * {@link Status#REQUEST_ENTITY_TOO_LARGE} (413)
 */
public class RequestEntityTooLargeException extends EvrythngErrorException {

	private static final long serialVersionUID = 3659315995335995964L;

	public RequestEntityTooLargeException(final ErrorMessage message) {

		super(message);
	}

	public RequestEntityTooLargeException(final ErrorMessage message, final Throwable cause) {

		super(message, cause);
	}
}
