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
	
	private String location;
	private Format format;
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

	@Override
	public Boolean getGenerateRedirections() {

		return true;
	}

	public static enum Format {
		
		CSV
	}
}
