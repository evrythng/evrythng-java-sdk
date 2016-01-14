/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.action.jobs.creation;

import java.util.HashMap;
import java.util.Map;

public abstract class FileCreateActionJobInput extends CreateActionJob.Input {

	private String location;
	public static final String FIELD_LOCATION = "location";

	private Format format;
	public static final String FIELD_FORMAT = "format";

	// Authorization
	private Map<String, String> headers;
	public static final String FIELD_HEADERS = "headers";

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

		return headers != null ? new HashMap<>(headers) : null;
	}

	public void setHeaders(final Map<String, String> headers) {

		this.headers = headers != null ? new HashMap<>(headers) : null;
	}

	public enum Format {

		CSV, ZIP
	}
}
