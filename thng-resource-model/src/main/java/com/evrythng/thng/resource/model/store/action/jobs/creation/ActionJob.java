/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.action.jobs.creation;

import com.evrythng.thng.resource.model.store.jobs.Job;
import com.evrythng.thng.resource.model.store.jobs.RequestedAmountProgress;

public abstract class ActionJob extends Job<RequestedAmountProgress> {

	private static final long serialVersionUID = -6528842645610876618L;
	private Type type;
	public static final String FIELD_TYPE = "type";

	public Type getType() {

		return type;
	}

	public void setType(final Type type) {

		this.type = type;
	}

	public enum Type {
		ACTION_CREATION
	}
}
