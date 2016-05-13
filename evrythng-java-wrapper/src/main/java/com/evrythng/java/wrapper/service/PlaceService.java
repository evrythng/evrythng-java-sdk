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
import com.evrythng.thng.resource.model.store.Place;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

/**
 * Service wrapper for the {@code /places} endpoint of the EVRYTHNG API.
 */
public class PlaceService extends EvrythngServiceBase {

	public static final String PATH_PLACES = "/places";
	public static final String PATH_PLACE = PATH_PLACES + "/%s";

	/**
	 * @param apiManager {@link ApiManager} instance
	 */
	public PlaceService(final ApiManager apiManager) {

		super(apiManager);
	}

	/* ***** Place ***** */

	/**
	 * Creates a new {@link Place}.
	 * <p>
	 * POST {@value #PATH_PLACES}
	 *
	 * @param place {@link Place} instance
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<Place> placeCreator(final Place place) throws EvrythngClientException {

		return post(PATH_PLACES, place, new TypeReference<Place>() {

		});
	}

	/**
	 * Retrieves list of all {@link Place} resources.
	 * <p>
	 * GET {@value #PATH_PLACES}
	 *
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<List<Place>> placesReader() throws EvrythngClientException {

		return get(PATH_PLACES, new TypeReference<List<Place>>() {

		});
	}

	/* ***** /places/{id} ***** */

	/**
	 * Retrieves the referenced {@link Place}.
	 * <p>
	 * GET {@value #PATH_PLACE}
	 *
	 * @param placeId place id
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<Place> placeReader(final String placeId) throws EvrythngClientException {

		return get(String.format(PATH_PLACE, placeId), new TypeReference<Place>() {

		});
	}

	/**
	 * Updates the referenced {@link Place}.
	 * <p>
	 * PUT {@value #PATH_PLACE}
	 *
	 * @param placeId place id
	 * @param place   {@link Place} instance
	 * @return the updated place
	 */
	public Builder<Place> placeUpdater(final String placeId, final Place place) throws EvrythngClientException {

		return put(String.format(PATH_PLACE, placeId), place, new TypeReference<Place>() {

		});
	}

	/**
	 * Deletes the referenced {@link Place}.
	 * <p>
	 * DELETE {@value #PATH_PLACE}
	 *
	 * @param placeId place id
	 * @return <tt>true</tt> if the place was successfully removed, otherwise <tt>false</tt>
	 */
	public Builder<Boolean> placeDeleter(final String placeId) throws EvrythngClientException {

		return delete(String.format(PATH_PLACE, placeId));
	}

}
