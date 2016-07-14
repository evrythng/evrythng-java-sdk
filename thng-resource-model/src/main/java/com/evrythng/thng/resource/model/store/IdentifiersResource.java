/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

/**
 * Represents list of identifiers resource with the same key.
 */
public class IdentifiersResource {

	public static final String FIELD_KEY = "key";
	public static final String FIELD_VALUES = "values";

	private String key;
	private Collection<String> values;

	private IdentifiersResource(final String key, final Collection<String> values) {

		this.key = key;
		this.values = values;
	}

	/**
	 * Instantiates new instance of {@link IdentifiersResource}.
	 *
	 * @param key    identifier key.
	 * @param values collection of values of identifier.
	 *
	 * @return instance of {@link IdentifiersResource}.
	 */
	@JsonCreator
	public static IdentifiersResource of(@JsonProperty("key") final String key,
	                                     @JsonProperty("values") final Collection<String> values) {

		return new IdentifiersResource(key, values);
	}

	public String getKey() {

		return key;
	}

	public void setKey(final String key) {

		this.key = key;
	}

	public Collection<String> getValues() {

		return values;
	}

	public void setValues(final Collection<String> values) {

		this.values = values;
	}
}
