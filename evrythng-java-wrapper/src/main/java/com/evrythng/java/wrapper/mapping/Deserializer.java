/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.mapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Deserializer<T> extends StdDeserializer<T> {

	private static final long serialVersionUID = 6174087253172016674L;

	protected Deserializer(final Class<?> vc) {

		super(vc);
	}

	protected final <X> X getFieldValue(final JsonNode node) {

		if (node == null) {
			return null;
		}
		Object value = null;
		if (node.isBoolean()) {
			value = node.asBoolean();
		}
		if (node.isNumber()) {
			value = node.asDouble();
		}
		if (node.isTextual()) {
			value = node.asText();
		}
		if (node.isArray()) {
			value = new ArrayList<>();
		}
		if (node.isObject()) {
			value = new HashMap<>();
		}
		return (X) value;
	}
}
