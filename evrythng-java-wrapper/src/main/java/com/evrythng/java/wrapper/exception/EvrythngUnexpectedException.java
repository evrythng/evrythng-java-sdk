/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.exception;

import com.evrythng.thng.resource.model.exception.ErrorMessage;

/**
 * TODO Comment this class
 * 
 * @author Pedro De Almeida (almeidap)
 **/
public class EvrythngUnexpectedException extends EvrythngErrorException {

	private static final long serialVersionUID = 1L;

	public EvrythngUnexpectedException(final ErrorMessage message) {
		super(message);
	}

	public EvrythngUnexpectedException(final ErrorMessage message, final Throwable cause) {
		super(message, cause);
	}
}
