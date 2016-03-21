/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.mapping;

import com.evrythng.java.wrapper.util.JSONUtils;
import com.evrythng.thng.resource.model.store.thng.jobs.creation.CreateThngsJob;
import com.evrythng.thng.resource.model.store.thng.jobs.creation.FileBasedCreateThngsJobInput;
import com.evrythng.thng.resource.model.store.thng.jobs.creation.ListBasedCreateThngsJobInput;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public final class CreateThngsJobInputDeserializer extends StdDeserializer<CreateThngsJob.Input> {

	private static final long serialVersionUID = -509441683273967836L;

	public CreateThngsJobInputDeserializer() {

		super(CreateThngsJob.Input.class);
	}

	@Override
	public CreateThngsJob.Input deserialize(final JsonParser jp, final DeserializationContext ctx) throws IOException {

		ObjectMapper mapper = JSONUtils.OBJECT_MAPPER;
		JsonNode node = mapper.readTree(jp);
		JsonNode typeNode = node.get(CreateThngsJob.Input.FIELD_TYPE);
		if (typeNode == null) {
			throw new JsonMappingException("Cannot deserialize create thngs job input without type field");
		}
		String typeRaw = getFieldValue(typeNode);
		Class<? extends CreateThngsJob.Input> subtypeClass = classForType(CreateThngsJob.Input.Type.valueOf(typeRaw.toUpperCase()));
		return mapper.readValue(node.toString(), subtypeClass);
	}

	private Class<? extends CreateThngsJob.Input> classForType(final CreateThngsJob.Input.Type type) {

		switch (type) {
			case FILE_BASED:
				return FileBasedCreateThngsJobInput.class;
			case LIST_BASED:
				return ListBasedCreateThngsJobInput.class;
		}
		throw new IllegalArgumentException("Unsupported create actions job input type: " + type);
	}

	private <T> T getFieldValue(final JsonNode node) {

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
		return (T) value;
	}
}
