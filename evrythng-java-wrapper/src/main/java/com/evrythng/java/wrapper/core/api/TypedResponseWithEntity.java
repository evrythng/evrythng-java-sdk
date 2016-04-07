/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
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
