/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.jobs;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class Progress {

	public static final double FULL_PROGRESS = 1.0d;

	public abstract Double percentage();

	@JsonIgnore
	public final boolean isComplete() {

		return Double.compare(percentage(), FULL_PROGRESS) >= 0;
	}
}
