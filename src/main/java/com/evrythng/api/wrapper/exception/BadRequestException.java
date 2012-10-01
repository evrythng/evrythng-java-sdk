/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.api.wrapper.exception;

import com.evrythng.thng.resource.model.exception.ErrorMessage;

/**
 * TODO Comment this class
 * 
 * @author Pedro De Almeida (almeidap)
 **/
public class BadRequestException extends EvrythngErrorException {

	public BadRequestException(ErrorMessage message) {
		super(message);
	}

	public BadRequestException(ErrorMessage message, Throwable cause) {
		super(message, cause);
	}
}
