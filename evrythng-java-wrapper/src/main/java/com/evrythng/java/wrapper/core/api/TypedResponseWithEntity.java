/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api;

import org.apache.http.HttpResponse;

/**
 * Bundle both http response and response entities
 * 
 **/
public class TypedResponseWithEntity<T> {

	private final HttpResponse httpResponse;

	private final T entity;

	public TypedResponseWithEntity(final HttpResponse httpResponse, final T entity) {

		this.httpResponse = httpResponse;
		this.entity = entity;
	}

	public final HttpResponse response() {

		return httpResponse;
	}

	public final T entity() {

		return entity;
	}
}
