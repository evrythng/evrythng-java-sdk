/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.jobs;

public final class RequestedAmountProgress extends Progress {

	public static final String FIELD_AMOUNT_REQUESTED = "amountRequested";

	private Long amountRequested;
	private Long amountCompleted;

	public RequestedAmountProgress(final Long amountCompleted, final Long amountRequested) {

		this.amountCompleted = amountCompleted;
		this.amountRequested = amountRequested;
	}

	public RequestedAmountProgress() {

	}

	public final Long getAmountCompleted() {

		return amountCompleted;
	}

	public final void setAmountCompleted(final Long amountCompleted) {

		this.amountCompleted = amountCompleted;
	}

	public final Long getAmountRequested() {

		return amountRequested;
	}

	public final void setAmountRequested(final Long amountRequested) {

		this.amountRequested = amountRequested;
	}

	@Override
	public final Double percentage() {

		return (double) amountCompleted / amountRequested;
	}
}