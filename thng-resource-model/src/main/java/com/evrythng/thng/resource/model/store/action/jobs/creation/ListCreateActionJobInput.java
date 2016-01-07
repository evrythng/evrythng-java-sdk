/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.action.jobs.creation;

import java.util.ArrayList;
import java.util.List;

public abstract class ListCreateActionJobInput extends CreateActionJob.Input {

	private List<String> payload;

	public List<String> getPayload() {

		return payload != null ? new ArrayList<>(payload) : null;
	}

	public void setPayload(final List<String> payload) {

		this.payload = payload != null ? new ArrayList<>(payload) : null;
	}
}
