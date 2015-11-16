/*
 * (c) Copyright 2012-2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.service;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.thng.resource.model.access.Credentials;
import com.evrythng.thng.resource.model.access.ThngCredentials;
import com.evrythng.thng.resource.model.store.User;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Map;

/**
 * Methods for the authentication API.
 * End point /auth. Implemented by thng-access module.
 */
public class AuthService extends EvrythngServiceBase {

	// ==== configuration ================================================== //

	public static final String PATH_AUTH = "/auth";
	public static final String PATH_AUTH_EVRYTHNG = PATH_AUTH + "/evrythng";
	public static final String PATH_AUTH_EVRYTHNG_USERS = PATH_AUTH_EVRYTHNG + "/users";
	public static final String PATH_AUTH_EVRYTHNG_USER_VALIDATE = PATH_AUTH_EVRYTHNG_USERS + "/%s/validate";

	public static final String PATH_AUTH_ALL = PATH_AUTH + "/all";
	public static final String PATH_AUTH_ALL_LOGOUT = PATH_AUTH_ALL + "/logout";

	public static final String PATH_AUTH_EVRYTHNG_THNGS = PATH_AUTH_EVRYTHNG + "/thngs";
	public static final String PATH_AUTH_EVRYTHNG_THNG = PATH_AUTH_EVRYTHNG_THNGS + "/%s";

	// ==== structor ======================================================= //

	public AuthService(final ApiManager apiManager) {
		super(apiManager);
	}

	// === requests ======================================================== //

	/**
	 * Register a new {@link User} in the system.
	 * <p>
	 * POST {@value #PATH_AUTH_EVRYTHNG_USERS}
	 *
	 * @param user {@link User} instance
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<Credentials> evrythngUserCreator(final User user) throws EvrythngClientException {

		return post(PATH_AUTH_EVRYTHNG_USERS, user, new TypeReference<Credentials>() {

		});
	}

	/**
	 * Activate a user.
	 * The activation code must match the one received during the registration
	 * process.
	 * <p>
	 * POST {@value #PATH_AUTH_EVRYTHNG_USER_VALIDATE}
	 *
	 * @param userId         user id
	 * @param activationCode activation code
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<Credentials> evrythngUserValidator(final String userId, final String activationCode) throws EvrythngClientException {

		Credentials validator = new Credentials();
		validator.setActivationCode(activationCode);
		return post(String.format(PATH_AUTH_EVRYTHNG_USER_VALIDATE, userId), validator, new TypeReference<Credentials>() {

		});
	}

	/**
	 * Authenticate a user.
	 * Based on the email + password pair.
	 * Engine provides the ApiKey of a successfully authenticated user.
	 * <p>
	 * POST {@value #PATH_AUTH_EVRYTHNG}
	 *
	 * @param credentials {@link Credentials} instance
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<Credentials> evrythngUserAuthenticator(final Credentials credentials) throws EvrythngClientException {

		return post(PATH_AUTH_EVRYTHNG, credentials, new TypeReference<Credentials>() {

		});
	}

	/**
	 * Logout. Invalidate user's key.
	 * <p>
	 * POST {@value #PATH_AUTH_ALL_LOGOUT}
	 *
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<Map<String, String>> authLogouter() throws EvrythngClientException {

		return post(PATH_AUTH_ALL_LOGOUT, null, new TypeReference<Map<String, String>>() {

		});
	}

	/**
	 * Create credentials for a thng. Engine provides the ApiKey of an registered thng.
	 * <p>
	 * POST {@value #PATH_AUTH_EVRYTHNG_THNGS}
	 *
	 * @param thngId thng id to provide credentials to
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<ThngCredentials> thngRegistrator(final String thngId) throws EvrythngClientException {

		ThngCredentials thngCredentials = new ThngCredentials();
		thngCredentials.setThngId(thngId);
		return post(PATH_AUTH_EVRYTHNG_THNGS, thngCredentials, new TypeReference<ThngCredentials>() {

		});
	}

	/**
	 * Deletes credentials of a thng. Engine revokes access of an registered thng.
	 * <p>
	 * DELETE {@value #PATH_AUTH_EVRYTHNG_THNG}
	 *
	 * @param thngId thng id to revoke credentials
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<Boolean> thngRegistrationDeleter(final String thngId) throws EvrythngClientException {

		return delete(String.format(PATH_AUTH_EVRYTHNG_THNG, thngId));
	}

	/**
	 * Retrieves credentials of a thng.
	 * <p>
	 * GET {@value #PATH_AUTH_EVRYTHNG_THNG}
	 *
	 * @param thngId the id of the thng whose credentials will be retrieved
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<ThngCredentials> thngRegistrationReader(final String thngId) throws EvrythngClientException {

		return get(String.format(PATH_AUTH_EVRYTHNG_THNG, thngId), new TypeReference<ThngCredentials>() {

		});
	}
}
