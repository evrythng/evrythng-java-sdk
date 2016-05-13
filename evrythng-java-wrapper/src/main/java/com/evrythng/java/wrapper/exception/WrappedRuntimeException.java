/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
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
