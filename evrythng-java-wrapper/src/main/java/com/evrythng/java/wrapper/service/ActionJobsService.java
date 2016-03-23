/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.java.wrapper.service;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.java.wrapper.core.api.AcceptedResourceResponse;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.java.wrapper.mapping.EvrythngJacksonModule;
import com.evrythng.thng.resource.model.store.action.jobs.creation.ActionJob;
import com.evrythng.thng.resource.model.store.jobs.JobLogEntry;
import com.evrythng.thng.resource.model.store.jobs.NewStatus;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.regex.Pattern;

public class ActionJobsService extends EvrythngServiceBase {

	private static final String PATH = "/jobs/actions";
	private static final String SINGLE_PATH = PATH + "/%s";
	private static final String LOG_ENTRIES_PATH = SINGLE_PATH + "/logs";
	public static final Pattern ID_LOCATION_PATTERN = Pattern.compile(".*/([^/]*)$");

	private static final class ListReference extends TypeReference<List<ActionJob<?>>> {

		private static final ListReference INSTANCE = new ListReference();

		private static ListReference instance() {

			return INSTANCE;
		}

		private ListReference() {

		}
	}

	private static final class Reference extends TypeReference<ActionJob<?>> {

		private static final Reference INSTANCE = new Reference();

		private static Reference instance() {

			return INSTANCE;
		}

		private Reference() {

		}
	}

	public ActionJobsService(final ApiManager apiManager, final EvrythngJacksonModule module) {

		super(apiManager);
	}

	public EvrythngApiBuilder.Builder<List<ActionJob<?>>> reader() throws EvrythngClientException {

		return get(PATH, ListReference.instance());
	}

	public EvrythngApiBuilder.Builder<ActionJob<?>> reader(final String jobId) throws EvrythngClientException {

		return get(String.format(SINGLE_PATH, jobId), Reference.instance());
	}

	// TODO _MS_ add MQTT listener
	public EvrythngApiBuilder.Builder<AcceptedResourceResponse> creator(final ActionJob<?> job) throws EvrythngClientException {

		return postAsynchronously(PATH, job, ID_LOCATION_PATTERN);
	}

	public interface OnActionJobExecuted {

		// TODO _MS_ review
		void apply(final NewStatus finalStatus);
	}

	public EvrythngApiBuilder.Builder<List<JobLogEntry>> logsReader(final String jobId) throws EvrythngClientException {

		return get(String.format(LOG_ENTRIES_PATH, jobId), LogsListReference.instance());
	}

//	public EvrythngApiBuilder.Builder<AcceptedResourceResponse> creator(final ActionJob<?> job, final OnActionJobExecuted onActionJobExecuted) throws EvrythngClientException {
//
//		// TODO _MS_
//		return postAsynchronouslyWithCallback(PATH, job, ID_LOCATION_PATTERN, "/jobs/actions", NewStatus.class, new EvrythngApiBuilder.MqttCallback<NewStatus>() {
//
//			@Override
//			public void apply(final NewStatus notified) {
//
//				// TODO _MS_ consider adding here
//				onActionJobExecuted.apply(notified);
//			}
//		});
//	}
}
