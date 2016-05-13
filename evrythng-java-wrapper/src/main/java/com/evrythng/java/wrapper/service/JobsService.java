/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.service;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.java.wrapper.mapping.EvrythngJacksonModule;

public class JobsService extends EvrythngServiceBase {

	private final ActionJobsService actions;

	public JobsService(final ApiManager apiManager, final EvrythngJacksonModule evrythngJacksonModule) {

		super(apiManager);
		this.actions = new ActionJobsService(apiManager, evrythngJacksonModule);
	}

	public ActionJobsService actions() {

		return actions;
	}
}
