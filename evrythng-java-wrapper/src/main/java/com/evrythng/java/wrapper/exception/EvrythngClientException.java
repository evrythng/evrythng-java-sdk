/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.exception;

/**
 * TODO Comment this class
 * 
 * @author Thomas Pham (tpham)
 * @author Pedro De Almeida (almeidap)
 **/
public class EvrythngClientException extends EvrythngException {

	private static final long serialVersionUID = 1L;

	public EvrythngClientException(final String message) {
		super(message);
	}

	public EvrythngClientException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
