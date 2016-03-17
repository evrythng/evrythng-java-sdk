/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.thng.jobs;

import com.evrythng.thng.resource.model.store.jobs.Job;
import com.evrythng.thng.resource.model.store.jobs.RequestedAmountProgress;

public abstract class ThngJob<OPTION_TYPE extends Enum<OPTION_TYPE>> extends Job<RequestedAmountProgress, ThngJob.Type, OPTION_TYPE> {

	private static final long serialVersionUID = -1660083283205605709L;

	public enum Type {
		CREATE_THNGS
	}
}
