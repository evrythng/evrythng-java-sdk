/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.jobs;

public enum Status {

	ENQUEUED(false), STARTING(false), EXECUTING(false), CANCELING(false), COMPLETED(true), COMPLETED_WITH_FAILURES(true), CANCELLED(true), FAILED(true);

	private final boolean terminal;

	Status(final boolean terminal) {

		this.terminal = terminal;
	}

	public boolean isTerminal() {

		return terminal;
	}
}
