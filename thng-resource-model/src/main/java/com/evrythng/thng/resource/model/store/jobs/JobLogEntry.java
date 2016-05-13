/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.jobs;

import com.evrythng.thng.resource.model.core.ResourceModel;

public final class JobLogEntry extends ResourceModel {

	private static final long serialVersionUID = 7272048986549940726L;

	public enum Type {
		ERROR
	}

	private String jobId;
	private Object payload;
	private Type type;
	private String message;

	public Type getType() {

		return type;
	}

	public void setType(final Type type) {

		this.type = type;
	}

	public Object getPayload() {

		return payload;
	}

	public void setPayload(final Object payload) {

		this.payload = payload;
	}

	public void setJobId(final String jobId) {

		this.jobId = jobId;
	}

	public String getJobId() {

		return jobId;
	}

	public void setMessage(final String message) {

		this.message = message;
	}

	public String getMessage() {

		return message;
	}
}