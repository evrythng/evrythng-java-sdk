/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Enumearates Evrythng's object types.
 */
public enum EvrythngType {

	THNG("thng"), PRODUCT("product"), PLACE("place"), COLLECTION("collection");
	private final String jsonValue;
	private static final Map<String, EvrythngType> lookup;

	EvrythngType(final String jsonValue) {

		this.jsonValue = jsonValue;
	}

	static {
		lookup = new HashMap<>();
		for (EvrythngType t : EnumSet.allOf(EvrythngType.class)) {
			lookup.put(t.getJsonValue(), t);
		}
	}

	@JsonValue
	public String getJsonValue() {

		return jsonValue;
	}

	/**
	 * Returns an {@link EvrythngType} value given its corresponding name.
	 *
	 * @return The {@link EvrythngType} value or null if the parameter is null.
	 * @throws IllegalArgumentException if the name does not match any value.
	 */
	@JsonCreator
	public static EvrythngType forValue(final String v) {

		if (v == null) {
			return null;
		}
		EvrythngType t = lookup.get(v);
		if (t == null) {
			throw new IllegalArgumentException();
		}
		return t;
	}
}
