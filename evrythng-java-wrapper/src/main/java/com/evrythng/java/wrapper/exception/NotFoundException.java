/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.exception;

import com.evrythng.java.wrapper.core.http.Status;
import com.evrythng.thng.resource.model.exception.ErrorMessage;

/**
 * {@link Status#NOT_FOUND} (404)
 * 
 * @author Thomas Pham (tpham)
 * @author Pedro De Almeida (almeidap)
 **/
public class NotFoundException extends EvrythngErrorException {

	private static final long serialVersionUID = 1L;

	public NotFoundException(final ErrorMessage message) {
		super(message);
	}

	public NotFoundException(final ErrorMessage message, final Throwable cause) {
		super(message, cause);
	}
}
