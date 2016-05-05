/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.core;

import java.io.Serializable;
import java.net.URI;

public class PrivateSignedUploadRequest implements Serializable {

	private URI signedUploadUri;
	private String relativeFilePath;
	private String serviceProvider;

	public PrivateSignedUploadRequest() {}

	public URI getSignedUploadUri() {

		return signedUploadUri;
	}

	public void setSignedUploadUri(final URI signedUploadUri) {

		this.signedUploadUri = signedUploadUri;
	}

	public String getRelativeFilePath() {

		return relativeFilePath;
	}

	public void setRelativeFilePath(final String relativeFilePath) {

		this.relativeFilePath = relativeFilePath;
	}

	public String getServiceProvider() {

		return serviceProvider;
	}

	public void setServiceProvider(final String serviceProvider) {

		this.serviceProvider = serviceProvider;
	}

	@Override
	public String toString() {

		final StringBuilder sb = new StringBuilder("PrivateSignedUploadRequest{");
		sb.append("signedUploadUri=").append(signedUploadUri);
		sb.append(", relativeFilePath='").append(relativeFilePath).append('\'');
		sb.append(", serviceProvider='").append(serviceProvider).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
