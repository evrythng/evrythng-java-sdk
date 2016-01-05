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
	private final String resourceId;

	/**
	 * Content of the Location http header if present.
	 */
	private final String location;

	public AcceptedResourceResponse(final String resourceId, final String location) {
		
		this.resourceId = resourceId;
		this.location = location;
	}

	/**
	 * Id of the created resource if applicable
	 */
	public String getResourceId() {

		return resourceId;
	}

	/**
	 * Content of the Location http header if present.
	 */
	public String getLocation() {

		return location;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder("{");
		sb.append("location='").append(location).append('\'');
		sb.append(", resourceId='").append(resourceId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
