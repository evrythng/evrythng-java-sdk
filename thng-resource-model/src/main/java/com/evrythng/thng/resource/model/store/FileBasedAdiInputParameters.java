/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store;

import java.util.Map;

/**
 * Input for ADI BatchPopulating task. Use a file to provide the shortIds to be generated.
 */
public class FileBasedAdiInputParameters extends BaseAdiInputParameters {

	private String location;
	public static final String FIELD_LOCATION = "location";

	private Format format;
	public static final String FIELD_FORMAT = "format";

	// Authorization
	private Map<String, String> headers;

	public Format getFormat() {

		return format;
	}

	public void setFormat(final Format format) {

		this.format = format;
	}

	public String getLocation() {

		return location;
	}

	public void setLocation(final String location) {

		this.location = location;
	}

	public Map<String, String> getHeaders() {

		return headers;
	}

	public void setHeaders(final Map<String, String> headers) {

		this.headers = headers;
	}

	public static enum Format {
		
		CSV
	}
}
