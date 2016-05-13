/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
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
