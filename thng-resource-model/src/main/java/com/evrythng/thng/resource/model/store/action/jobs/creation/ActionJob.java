/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.action.jobs.creation;

import com.evrythng.thng.resource.model.store.jobs.Job;
import com.evrythng.thng.resource.model.store.jobs.RequestedAmountProgress;

public abstract class ActionJob<OPTION_TYPE extends Enum<OPTION_TYPE>> extends Job<RequestedAmountProgress, ActionJob.Type, OPTION_TYPE> {

	private static final long serialVersionUID = -6528842645610876618L;

	public enum Type {
		ACTION_CREATION
	}
}
