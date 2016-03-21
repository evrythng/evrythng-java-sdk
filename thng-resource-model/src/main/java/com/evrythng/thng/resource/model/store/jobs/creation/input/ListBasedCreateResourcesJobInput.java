/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.jobs.creation.input;

import java.util.List;

public abstract class ListBasedCreateResourcesJobInput<PAYLOAD_TYPE> extends CreateResourcesJobInput {

	private List<PAYLOAD_TYPE> payload;

	// TODO _MS_ add payload
	protected ListBasedCreateResourcesJobInput() {

		setType(Type.LIST_BASED);
	}

	public final List<PAYLOAD_TYPE> getPayload() {

		return payload;
	}

	public final void setPayload(final List<PAYLOAD_TYPE> payload) {

		this.payload = payload;
	}
}
