/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store;

/**
 * TODO write javadoc here
 */
public class FileBasedAdiInputParameters extends BaseAdiInputParameters {

	private String location;
	private Format format;
	private String shortDomain;
	// Authorization

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

	public String getShortDomain() {

		return shortDomain;
	}

	public void setShortDomain(final String shortDomain) {

		this.shortDomain = shortDomain;
	}

	public static enum Format {
		
		CSV
	}
}
