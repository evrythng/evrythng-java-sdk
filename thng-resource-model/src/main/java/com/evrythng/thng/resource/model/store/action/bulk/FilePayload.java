/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.action.bulk;

public class FilePayload extends Payload {

	private static final long serialVersionUID = -5109459255229234882L;
	private String location;
	private Format format;

	public FilePayload() {

		setType(Type.FILE);
	}

	public enum Format {
		PLAIN, ZIP
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
