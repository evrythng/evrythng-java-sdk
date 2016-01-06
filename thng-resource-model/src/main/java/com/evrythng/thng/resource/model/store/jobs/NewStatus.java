/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.jobs;

public final class NewStatus {

	private Status status;
	private Long timestamp;

	public NewStatus(final Status status, final Long timestamp) {

		this.timestamp = timestamp;
		this.status = status;
	}

	public NewStatus() {

	}

	public final Long getTimestamp() {

		return timestamp;
	}

	public final void setTimestamp(final Long timestamp) {

		this.timestamp = timestamp;
	}

	public final Status getStatus() {

		return status;
	}

	public final void setStatus(final Status status) {

		this.status = status;
	}

	public final boolean isTerminal() {

		return status != null && status.isTerminal();
	}
}
