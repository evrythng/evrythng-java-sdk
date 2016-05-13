/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.jobs;

public final class RequestedAmountProgress extends Progress {

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