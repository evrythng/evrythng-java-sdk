/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
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
