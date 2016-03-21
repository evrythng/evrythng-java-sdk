/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.files;

import java.util.Map;

public class FileLocationResource {

	public static final String FIELD_HEADERS = "headers";
	private Map<String, String> headers;

	private String location;
	public static final String FIELD_LOCATION = "location";

	private Format format;
	public static final String FIELD_FORMAT = "format";

	public enum Format {
		JSON, JSON_ZIP
	}

	public Map<String, String> getHeaders() {

		return headers;
	}

	public void setHeaders(final Map<String, String> headers) {

		this.headers = headers;
	}

	public String getLocation() {

		return location;
	}

	public void setLocation(final String location) {

		this.location = location;
	}

	public Format getFormat() {

		return format;
	}

	public void setFormat(final Format format) {

		this.format = format;
	}
}
