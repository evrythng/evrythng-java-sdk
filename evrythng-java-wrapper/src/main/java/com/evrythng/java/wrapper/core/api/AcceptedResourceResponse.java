/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.java.wrapper.core.api;

/**
 * Hold the response of a 202 Accepted status.
 */
public class AcceptedResourceResponse {

	/**
	 * Id of the created resource if applicable
	 */
	public final String resourceId;

	/**
	 * Content of the Location http header if present.
	 */
	public final String location;

	public AcceptedResourceResponse(String resourceId, String location) {
		this.resourceId = resourceId;
		this.location = location;
	}

}
