/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
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
