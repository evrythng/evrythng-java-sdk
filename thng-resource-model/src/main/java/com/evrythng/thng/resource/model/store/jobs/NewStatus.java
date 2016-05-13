/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
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

	public final Status getStatus() {

		return status;
	}

	public final boolean isTerminal() {

		return status != null && status.isTerminal();
	}
}
