/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.service;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.thng.resource.model.store.Application;
import com.evrythng.thng.resource.model.store.OAuthClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

/**
 * Methods for the application service "/applications" endpoint. Api is implemented in thng-access
 */
public class ApplicationService extends EvrythngServiceBase {

	public static final String QP_SOCIAL_NETWORK_NAME = "socialNetworkName";
	public static final String QP_SOCIAL_NETWORK_APP_ID = "socialNetworkAppId";
	public static final String PATH_APPLICATIONS = "/projects/%s/applications";
	public static final String PATH_APPLICATION = PATH_APPLICATIONS + "/%s";
	public static final String PATH_APPLICATIONS_LEGACY = "/applications";
	public static final String PATH_APPLICATION_LEGACY = PATH_APPLICATIONS_LEGACY + "/%s";
	public static final String PATH_APPLICATION_OAUTH_CLIENTS = PATH_APPLICATION + "/oauthClients";
	public static final String PATH_APPLICATION_OAUTH_CLIENT = PATH_APPLICATION_OAUTH_CLIENTS + "/%s";

	public ApplicationService(final ApiManager apiManager) {

		super(apiManager);
	}

	/**
	 * Creates a new {@link Application}
	 * <p>
	 * POST {@value #PATH_APPLICATIONS}
	 *
	 * @param projectId project id
	 * @param app       : App to create. Customer and Description are mandatory.
	 * @return the app created, including its brand-new "appApiKey" and its id to be used for further call of the service.
	 */
	public Builder<Application> applicationCreator(final String projectId, final Application app) throws EvrythngClientException {

		return post(projectApplications(projectId), app, new TypeReference<Application>() {

		});
	}

	/**
	 * List all {@link Application}.
	 * <p>
	 * GET {@value #PATH_APPLICATIONS}
	 *
	 * @param projectId project id
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<List<Application>> applicationsReader(final String projectId) throws EvrythngClientException {

		return get(projectApplications(projectId), new TypeReference<List<Application>>() {

		});
	}

	/**
	 * Load a single {@link Application}.
	 * <p>
	 * GET {@value #PATH_APPLICATION}/:appId
	 *
	 * @param projectId project id
	 * @param appId     app id
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<Application> applicationReader(final String projectId, final String appId) throws EvrythngClientException {

		return get(projectApplication(projectId, appId), new TypeReference<Application>() {

		});
	}

	/**
	 * Update a single {@link Application}.
	 * <p>
	 * PUT {@value #PATH_APPLICATION}/:appId
	 *
	 * @param projectId project id
	 * @param appId     app id
	 * @param app       : input for field update. Will currently update the fields description, customer, and social networks.
	 * @return the updated data.
	 */
	public Builder<Application> applicationUpdater(final String projectId, final String appId, final Application app) throws EvrythngClientException {

		return put(projectApplication(projectId, appId), app, new TypeReference<Application>() {

		});
	}

	/**
	 * Delete a single {@link Application}. All application users are also removed from the system.
	 *
	 * @param projectId project id
	 * @param appId     app id
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<Boolean> applicationDeleter(final String projectId, final String appId) throws EvrythngClientException {

		return delete(projectApplication(projectId, appId));
	}

	/**
	 * Delete multiple {@link Application}s. All application users are also removed from the system.
	 *
	 * @param projectId          project id
	 * @param socialNetworkName  social network name
	 * @param socialNetworkAppId social network app id
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<List<Application>> applicationsReader(final String projectId, final String socialNetworkName, final String socialNetworkAppId) throws EvrythngClientException {

		return get(projectApplications(projectId), new TypeReference<List<Application>>() {

		}).queryParam(QP_SOCIAL_NETWORK_NAME, socialNetworkName).queryParam(QP_SOCIAL_NETWORK_APP_ID, socialNetworkAppId);
	}

	private String projectApplications(final String projectId) {

		return String.format(PATH_APPLICATIONS, projectId);
	}

	private String projectApplication(final String projectId, final String appId) {

		return String.format(PATH_APPLICATION, projectId, appId);
	}

	/**
	 * List all {@link Application} from project scope.
	 * <p>
	 * GET {@value #PATH_APPLICATIONS_LEGACY}
	 *
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<List<Application>> applicationsReader() throws EvrythngClientException {

		return get(PATH_APPLICATIONS_LEGACY, new TypeReference<List<Application>>() {

		});
	}

	/**
	 * Load a single {@link Application} from project scope.
	 * <p>
	 * GET {@value #PATH_APPLICATION_LEGACY}/:appId
	 *
	 * @param appId app id
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<Application> applicationReader(final String appId) throws EvrythngClientException {

		return get(String.format(PATH_APPLICATION_LEGACY, appId), new TypeReference<Application>() {

		});
	}

	/**
	 * Creates a new {@link OAuthClient} for the application
	 * <p>
	 * POST {@value #PATH_APPLICATION_OAUTH_CLIENTS}
	 *
	 * @param projectId project id
	 * @param appId: application id
	 * @param oauthClient OAuth client to create
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<OAuthClient> oauthClientCreator(final String projectId, final String appId, final OAuthClient oauthClient) throws EvrythngClientException {

		return post(String.format(PATH_APPLICATION_OAUTH_CLIENTS, projectId, appId), oauthClient, new TypeReference<OAuthClient>() {

		});
	}

	/**
	 * List all {@link OAuthClient} registered for the application.
	 * <p>
	 * GET {@value #PATH_APPLICATION_OAUTH_CLIENTS}
	 *
	 * @param projectId project id
	 * @param appId app id
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<List<OAuthClient>> oauthClientsReader(final String projectId, final String appId) throws EvrythngClientException {

		return get(String.format(PATH_APPLICATION_OAUTH_CLIENTS, projectId, appId), new TypeReference<List<OAuthClient>>() {

		});
	}

	/**
	 * Load a single {@link OAuthClient}.
	 * <p>
	 * GET {@value #PATH_APPLICATION_OAUTH_CLIENT}
	 *
	 * @param projectId project id
	 * @param appId app id
	 * @param clientId OAuth client id
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<OAuthClient> oauthClientReader(final String projectId, final String appId, final String clientId) throws EvrythngClientException {

		return get(String.format(PATH_APPLICATION_OAUTH_CLIENT, projectId, appId, clientId), new TypeReference<OAuthClient>() {

		});
	}

	/**
	 * Updates {@link OAuthClient}.
	 * <p>
	 * GET {@value #PATH_APPLICATION_OAUTH_CLIENT}
	 *
	 * @param projectId project id
	 * @param appId app id
	 * @param clientId OAuth client id
	 * @param oauthClient OAuth client for update
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<OAuthClient> oauthClientUpdater(final String projectId, final String appId, final String clientId, final OAuthClient oauthClient) throws EvrythngClientException {

		return put(String.format(PATH_APPLICATION_OAUTH_CLIENT, projectId, appId, clientId), oauthClient, new TypeReference<OAuthClient>() {

		});
	}

	/**
	 * Deletes {@link OAuthClient}.
	 *
	 * @param projectId project id
	 * @param appId app id
	 * @param clientId OAuth client id
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<Boolean> oauthClientDeleter(final String projectId, final String appId, final String clientId) throws EvrythngClientException {

		return delete(String.format(PATH_APPLICATION_OAUTH_CLIENT, projectId, appId, clientId));
	}

}
