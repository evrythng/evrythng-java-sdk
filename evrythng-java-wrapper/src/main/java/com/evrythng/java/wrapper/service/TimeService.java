package com.evrythng.java.wrapper.service;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.thng.resource.model.store.TimeInfo;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Service wrapper for the {@code /time} endpoint of the EVRYTHNG Engine API.
 */
public class TimeService extends EvrythngServiceBase {

	public static final String PATH_TIME = "/time";

	public TimeService(final ApiManager apiManager) {

		super(apiManager);
	}

	/**
	 *  Gets the time info.
	 * <p>
	 * GET {@value #PATH_TIME}
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<TimeInfo> timeReader() throws EvrythngClientException {

		return get(PATH_TIME, new TypeReference<TimeInfo>() {
		});
	}
}
