/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.service;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.thng.resource.model.store.ReactorSchedule;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

/**
 * Service wrapper for the {@code /projects/{projectId}/applications/{applicationId}/reactor/schedules} endpoint of the EVRYTHNG
 * API.
 */
public class ReactorSchedulesService extends EvrythngServiceBase {

	private static final String PATH_REACTOR_SCHEDULES = "/projects/%s/applications/%s/reactor/schedules";
	private static final String PATH_REACTOR_SCHEDULE = PATH_REACTOR_SCHEDULES + "/%s";

	public ReactorSchedulesService(final ApiManager api) {

		super(api);
	}

	/**
	 * Introduces parameters into reactor schedules url.
	 */
	private static String reactorSchedulesUrl(final String projectId, final String appId) {

		return String.format(PATH_REACTOR_SCHEDULES, projectId, appId);
	}

	/**
	 * Introduces parameters into reactor schedule url.
	 */
	private static String reactorScheduleUrl(final String projectId, final String appId, final String reactorScheduleId) {

		return String.format(PATH_REACTOR_SCHEDULE, projectId, appId, reactorScheduleId);
	}

	/**
	 * Retrieves all {@link ReactorSchedule} from specified project and application. <p>
	 * {@code GET} {@value #PATH_REACTOR_SCHEDULES}.
	 *
	 * @param projectId id of a project containing reactor schedules.
	 * @param appId     id of an application containing reactor schedules.
	 *
	 * @return list of found {@link ReactorSchedule}.
	 */
	public Builder<List<ReactorSchedule>> reactorSchedulesReader(final String projectId, final String appId)
			throws EvrythngClientException {

		return get(reactorSchedulesUrl(projectId, appId), new TypeReference<List<ReactorSchedule>>() {});
	}

	/**
	 * Retrieves {@link ReactorSchedule} with the specified id from specified project and application. <p>
	 * {@code GET} {@value #PATH_REACTOR_SCHEDULE}.
	 *
	 * @param projectId         id of a project containing reactor schedule.
	 * @param appId             id of an application containing reactor schedule.
	 * @param reactorScheduleId id of a reactor schedule.
	 *
	 * @return found {@link ReactorSchedule} or HTTP 404 NOT FOUND.
	 */
	public Builder<ReactorSchedule> reactorScheduleReader(final String projectId, final String appId,
	                                                      final String reactorScheduleId)
			throws EvrythngClientException {

		return get(reactorScheduleUrl(projectId, appId, reactorScheduleId), new TypeReference<ReactorSchedule>() {});
	}

	/**
	 * Updates {@link ReactorSchedule} by specified id.<p>
	 * {@code PUT} {@value #PATH_REACTOR_SCHEDULE}.
	 *
	 * @param projectId         if of a project containing reactor schedule.
	 * @param appId             id of an application containing reactor schedule.
	 * @param reactorScheduleId id of a reactor schedule.
	 * @param reactorSchedule   reactor schedule update.
	 *
	 * @return Updated reactor schedule or HTTP 404 NOT FOUND.
	 */
	public Builder<ReactorSchedule> reactorScheduleUpdater(final String projectId, final String appId,
	                                                       final String reactorScheduleId,
	                                                       final ReactorSchedule reactorSchedule)
			throws EvrythngClientException {

		return put(reactorScheduleUrl(projectId, appId, reactorScheduleId), reactorSchedule,
		           new TypeReference<ReactorSchedule>() {});
	}

	/**
	 * Creates {@link ReactorSchedule}. <p>
	 * {@code POST} {@value #PATH_REACTOR_SCHEDULES}.
	 *
	 * @param projectId       id of a project.
	 * @param appId           if of an application.
	 * @param reactorSchedule reactor schedule create fields.
	 *
	 * @return Created reactor schedule.
	 */
	public Builder<ReactorSchedule> reactorScheduleCreator(final String projectId, final String appId,
	                                                       final ReactorSchedule reactorSchedule)
			throws EvrythngClientException {

		return post(reactorSchedulesUrl(projectId, appId), reactorSchedule, new TypeReference<ReactorSchedule>() {});
	}

	/**
	 * Removes {@link ReactorSchedule}. <p>
	 * {@code DELETE} {@value #PATH_REACTOR_SCHEDULE}.
	 *
	 * @param projectId         id of a project containing reactor schedule.
	 * @param appId             if of an application containing reactor schedule.
	 * @param reactorScheduleId if of a reactor schedule.
	 *
	 * @return {@code TRUE} if operation was successful, {@code FALSE} otherwise.
	 */
	public Builder<Boolean> reactorScheduleDeleter(final String projectId, final String appId,
	                                               final String reactorScheduleId)
			throws EvrythngClientException {

		return delete(reactorScheduleUrl(projectId, appId, reactorScheduleId));
	}
}
