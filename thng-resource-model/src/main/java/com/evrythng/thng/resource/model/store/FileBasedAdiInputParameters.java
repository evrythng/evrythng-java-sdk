/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store;

import java.util.Map;

/**
 * TODO write javadoc here
 */
public class FileBasedAdiInputParameters extends BaseAdiInputParameters {

	private ThngTemplate thngTemplate;
	private Boolean generateThngs;
	private Boolean generateRedirections;
	
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

	public void setGenerateThngs(final Boolean generateThngs) {

		this.generateThngs = generateThngs;
	}

	public ThngTemplate getThngTemplate() {

		return thngTemplate;
	}

	public void setThngTemplate(final ThngTemplate thngTemplate) {

		this.thngTemplate = thngTemplate;
	}

	public Map<String, String> getHeaders() {

		return headers;
	}

	public void setHeaders(final Map<String, String> headers) {

		this.headers = headers;
	}

	@Override
	public Boolean getGenerateThngs() {

		return generateThngs;
	}

	public void setGenerateRedirections(final Boolean generateRedirections) {

		this.generateRedirections = generateRedirections;
	}

	@Override
	public Boolean getGenerateRedirections() {

		return generateRedirections;
	}

	public static enum Format {
		
		CSV
	}
}
