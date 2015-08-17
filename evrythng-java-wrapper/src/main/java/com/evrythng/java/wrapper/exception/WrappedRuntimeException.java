/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.exception;

/**
 * Exception wrapper for throwing important checked exceptions
 * over non-checked methods. It should be used with care,
 * and in limited circumstances.
 * 
 * @author Pedro De Almeida (almeidap)
 **/
public class WrappedRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public WrappedRuntimeException(final Exception e) {
		super(e);
	}

    public WrappedRuntimeException(final String message, final Exception e) {
        super(message, e);
    }

}
