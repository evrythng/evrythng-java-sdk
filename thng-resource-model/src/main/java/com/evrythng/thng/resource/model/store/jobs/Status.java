/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.jobs;

public final class Status {

	public enum Type {
		ENQUEUED, STARTING, EXECUTING, EXECUTED, CANCELING, CANCELLED, FAILED
	}

	private Type type;
	private Long timestamp;

	public Status(final Type type, final Long timestamp) {

		this.timestamp = timestamp;
		this.type = type;
	}

	public Status() {

	}

	public final Long getTimestamp() {

		return timestamp;
	}

	public final void setTimestamp(final Long timestamp) {

		this.timestamp = timestamp;
	}

	public final Type getType() {

		return type;
	}

	public final void setType(final Type type) {

		this.type = type;
	}
}
