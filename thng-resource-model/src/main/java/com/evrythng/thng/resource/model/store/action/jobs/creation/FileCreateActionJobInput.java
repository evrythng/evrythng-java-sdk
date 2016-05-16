/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
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

	@Override
	public Type getType() {

		return Type.FILE;
	}

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
}
