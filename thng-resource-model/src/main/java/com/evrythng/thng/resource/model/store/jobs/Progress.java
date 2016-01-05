/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.jobs;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class Progress {

	public abstract Double percentage();

	@JsonIgnore
	public final boolean isComplete() {

		return Double.compare(percentage(), 100.0d) >= 0;
	}
}
