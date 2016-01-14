/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.jobs;

import com.evrythng.thng.resource.model.core.ResourceModel;

public final class JobLogEntryResource extends ResourceModel {

	private static final long serialVersionUID = 7272048986549940726L;

	public enum Type {
		ERROR
	}

	private String jobId;

	private Object payload;

	private Type type;

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
}