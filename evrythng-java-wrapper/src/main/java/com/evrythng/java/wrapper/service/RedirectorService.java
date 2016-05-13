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
import com.evrythng.thng.resource.model.store.RedirectorRules;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Service wrapper for the {@code /redirector} endpoint of the EVRYTHNG API.
 */
public class RedirectorService extends EvrythngServiceBase {

	public static final String PATH_ACCOUNT_REDIRECTOR = "/redirector";
	public static final String PATH_APPLICATION_REDIRECTOR = "/projects/%s/applications/%s/redirector";

	/**
	 * Creates a new instance of {@link RedirectorService} using the provided {@link ApiManager}.
	 *
	 * @param apiManager the {@link ApiManager} for accessing the API service.
	 */
	public RedirectorService(final ApiManager apiManager) {

		super(apiManager);
	}

	/**
	 * Load a single Account level {@link RedirectorRules}.
	 * <p>
	 * GET {@value #PATH_ACCOUNT_REDIRECTOR}
	 *
	 * @return a preconfigured {@link Builder}.
	 */
	public Builder<RedirectorRules> redirectorRulesReader() throws EvrythngClientException {

		return get(PATH_ACCOUNT_REDIRECTOR, new TypeReference<RedirectorRules>() {

		});
	}

	/**
	 * Create or update Account level {@link RedirectorRules}.
	 * <p>
	 * PUT {@value #PATH_ACCOUNT_REDIRECTOR}
	 *
	 * @param redirectorRules {@link RedirectorRules} object.
	 *
	 * @return a preconfigured {@link Builder}.
	 */
	public Builder<RedirectorRules> redirectorRulesUpdater(final RedirectorRules redirectorRules) throws
	                                                                                              EvrythngClientException {

		return put(PATH_ACCOUNT_REDIRECTOR, redirectorRules, new TypeReference<RedirectorRules>() {

		});
	}

	/**
	 * Load a single Application level {@link RedirectorRules}.
	 * <p>
	 * GET {@value #PATH_APPLICATION_REDIRECTOR}
	 *
	 * @param projectId project id.
	 * @param appId     application id.
	 *
	 * @return a preconfigured {@link Builder}.
	 */
	public Builder<RedirectorRules> redirectorRulesReader(final String projectId, final String appId)
			throws EvrythngClientException {

		return get(String.format(PATH_APPLICATION_REDIRECTOR, projectId, appId), new TypeReference<RedirectorRules>() {

		});
	}

	/**
	 * Create or update Application level {@link RedirectorRules}.
	 * <p>
	 * PUT {@value #PATH_APPLICATION_REDIRECTOR}
	 *
	 * @param projectId       project id.
	 * @param appId           application id.
	 * @param redirectorRules {@link RedirectorRules} object.
	 *
	 * @return a preconfigured {@link Builder}.
	 */
	public Builder<RedirectorRules> redirectorRulesUpdater(final String projectId, final String appId,
	                                                              final RedirectorRules redirectorRules) throws
	                                                                                              EvrythngClientException {

		return put(String.format(PATH_APPLICATION_REDIRECTOR, projectId, appId), redirectorRules,
		           new TypeReference<RedirectorRules>() {

		           });
	}
}
