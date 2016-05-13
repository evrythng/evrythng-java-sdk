/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.service;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.thng.resource.model.store.Project;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

/**
 * Service wrapper for the {@code /projects} endpoint of the EVRYTHNG API.
 */
public class ProjectService extends EvrythngServiceBase {

	private static final String PATH_PROJECTS = "/projects";
	private static final String PATH_PROJECT = PATH_PROJECTS + "/%s";

	/**
	 * @param apiManager {@link ApiManager} instance
	 */
	public ProjectService(final ApiManager apiManager) {

		super(apiManager);
	}

	/**
	 * Creates a new {@link Project}
	 * <p>
	 * POST {@value #PATH_PROJECTS}
	 *
	 * @param project {@link Project} instance
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<Project> projectCreator(final Project project) throws EvrythngClientException {

		return post(PATH_PROJECTS, project, new TypeReference<Project>() {

		});
	}

	/**
	 * Retrieves list of all {@link Project} resources.
	 * <p>
	 * GET {@value #PATH_PROJECTS}
	 *
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<List<Project>> projectsReader() throws EvrythngClientException {

		return get(PATH_PROJECTS, new TypeReference<List<Project>>() {

		});
	}

	/**
	 * Retrieves the referenced {@link Project}
	 * <p>
	 * GET {@value #PATH_PROJECT}
	 *
	 * @param id project id
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<Project> projectReader(final String id) throws EvrythngClientException {

		return get(String.format(PATH_PROJECT, id), new TypeReference<Project>() {

		});
	}

	/**
	 * Updates the referenced {@link Project}
	 * <p>
	 * PUT {@value #PATH_PROJECT}
	 *
	 * @param id      project id
	 * @param project {@link Project} instance
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<Project> projectUpdater(final String id, final Project project) throws EvrythngClientException {

		return put(String.format(PATH_PROJECT, id), project, new TypeReference<Project>() {

		});
	}

	/**
	 * Deletes the referenced {@link Project}
	 * <p>
	 * DELETE {@value #PATH_PROJECT}
	 *
	 * @param id project id
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Boolean> projectDeleter(final String id) throws EvrythngClientException {

		return delete(String.format(PATH_PROJECT, id));
	}

	/**
	 * Deletes the referenced {@link Project}
	 * <p>
	 * DELETE {@value #PATH_PROJECT}
	 *
	 * @param ids project ids
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Long> projectsDeleter(final List<String> ids) throws EvrythngClientException {

		return deleteMultiple(PATH_PROJECTS).ids(ids);
	}

	/**
	 * Deletes the referenced {@link Project}
	 * <p>
	 * DELETE {@value #PATH_PROJECT}
	 *
	 * @param filter filter for projects to delete
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Long> projectsDeleter(final String filter) throws EvrythngClientException {

		return deleteMultiple(PATH_PROJECTS).filter(filter);
	}
}
