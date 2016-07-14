/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

/**
 * Represents {@link BatchPopulatingTask} input parameters based on list of identifiers
 */
public class IdentifiersListBasedAdiInputParameters extends BaseAdiInputParameters {

	public static final String FIELD_CUSTOM_IDENTIFIER = "customIdentifier";
	private IdentifiersResource customIdentifier;

	/**
	 * Retrieves custom identifier.
	 *
	 * @return instance of {@link IdentifiersResource}.
	 */
	public IdentifiersResource getCustomIdentifier() {

		return customIdentifier;
	}

	/**
	 * Sets new custom identifier.
	 *
	 * @param customIdentifier the instance of {@link IdentifiersResource} to set.
	 */
	public void setCustomIdentifier(final IdentifiersResource customIdentifier) {

		this.customIdentifier = customIdentifier;
	}
}
