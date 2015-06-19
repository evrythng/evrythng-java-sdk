/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.mapping;

import com.evrythng.java.wrapper.util.JSONUtils;
import com.evrythng.thng.resource.model.store.ArrayProperty;
import com.evrythng.thng.resource.model.store.BooleanProperty;
import com.evrythng.thng.resource.model.store.NumberProperty;
import com.evrythng.thng.resource.model.store.ObjectProperty;
import com.evrythng.thng.resource.model.store.Property;
import com.evrythng.thng.resource.model.store.StringProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class PropertyDeserializer extends StdDeserializer<Property<?>> {

	private static final long serialVersionUID = 272184685620984657L;

	protected PropertyDeserializer() {

		super(Property.class);
	}

	@Override
	public Property<?> deserialize(final JsonParser jp, final DeserializationContext ctx) throws IOException {

		ObjectMapper mapper = JSONUtils.OBJECT_MAPPER;
		JsonNode node = mapper.readTree(jp);
		JsonNode valueNode = node.get(Property.FIELD_VALUE);
		if (valueNode == null) {
			throw new JsonMappingException("Cannot deserialize property without value field");
		}
		Object value = getFieldValue(valueNode);
		Property.Type type = Property.Type.forPropertyValue(value);
		Class<? extends Property<?>> propertyClass = propertyClassForType(type);
		return mapper.readValue(node.toString(), propertyClass);
	}

	private Class<? extends Property<?>> propertyClassForType(final Property.Type type) {

		switch (type) {
			case BOOLEAN:
				return BooleanProperty.class;
			case STRING:
				return StringProperty.class;
			case NUMBER:
				return NumberProperty.class;
			case ARRAY:
				return ArrayProperty.class;
			case OBJECT:
				return ObjectProperty.class;
		}
		throw new IllegalArgumentException("Unsupported property type: " + type);
	}

	private Object getFieldValue(final JsonNode node) {

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
		return value;
	}
}
