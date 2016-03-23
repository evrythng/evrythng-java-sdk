/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.java.wrapper.service;

import com.evrythng.thng.resource.model.store.jobs.JobLogEntry;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

final class LogsListReference extends TypeReference<List<JobLogEntry>> {

	private static final LogsListReference INSTANCE = new LogsListReference();

	static LogsListReference instance() {

		return INSTANCE;
	}

	private LogsListReference() {

	}
}
