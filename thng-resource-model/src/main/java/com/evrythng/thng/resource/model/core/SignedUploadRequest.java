/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.core;

import java.io.Serializable;

public class SignedUploadRequest implements Serializable {

	private static final long serialVersionUID = -199602602136687343L;

	private String signedUploadUrl;
	private String contentUrl;
	private String name;

	public String getSignedUploadUrl() {
		return signedUploadUrl;
	}

	public void setSignedUploadUrl(String signedUploadUrl) {
		this.signedUploadUrl = signedUploadUrl;
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {

		final StringBuilder sb = new StringBuilder("SignedUploadRequest{");
		sb.append("signedUploadUrl='").append(signedUploadUrl).append('\'');
		sb.append(", contentUrl='").append(contentUrl).append('\'');
		sb.append(", name='").append(name).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
