/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.exception;

import com.evrythng.java.wrapper.core.http.Status;
import com.evrythng.thng.resource.model.exception.ErrorMessage;

/**
 * {@link Status#UNAUTHORIZED} (401)
 * 
 * @author Pedro De Almeida (almeidap)
 **/
public class UnauthorizedException extends EvrythngErrorException {

	private static final long serialVersionUID = 1L;

	public UnauthorizedException(final ErrorMessage message) {
		super(message);
	}

	public UnauthorizedException(final ErrorMessage message, final Throwable cause) {
		super(message, cause);
	}
}
