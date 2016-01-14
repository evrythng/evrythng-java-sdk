/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.action.jobs.creation;

import com.evrythng.thng.resource.model.store.jobs.Job;
import com.evrythng.thng.resource.model.store.jobs.RequestedAmountProgress;

public abstract class ActionJob extends Job<RequestedAmountProgress, ActionJob.Type> {

	private static final long serialVersionUID = -6528842645610876618L;

	public enum Type {
		ACTION_CREATION
	}
}
