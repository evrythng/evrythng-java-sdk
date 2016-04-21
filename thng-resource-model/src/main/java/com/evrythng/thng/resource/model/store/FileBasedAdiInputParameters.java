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

	public static final String FIELD_HEADERS = "headers";
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

	@Override
	public String toString() {

		final StringBuilder sb = new StringBuilder("FileBasedAdiInputParameters{");
		sb.append("generateThngs=").append(this.getGenerateThngs());
		sb.append(", generateRedirections=").append(this.getGenerateRedirections());
		sb.append(", shortDomain='").append(this.getShortDomain()).append('\'');
		sb.append(", defaultRedirectUrl='").append(this.getDefaultRedirectUrl()).append('\'');
		sb.append(", thngTemplate=").append(this.getThngTemplate());
		sb.append(", type=").append(this.getType());
		sb.append(", location='").append(location).append('\'');
		sb.append(", format=").append(format);
		sb.append(", headers=").append(headers);
		sb.append('}');
		return sb.toString();
	}
}
