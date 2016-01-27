/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.java.wrapper.mapping;

import com.evrythng.java.wrapper.util.JSONUtils;
import com.evrythng.thng.resource.model.store.BatchPopulatingTask;
import com.evrythng.thng.resource.model.store.CsvAdiOutputParameters;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BatchPopulatingTaskOutputParametersDeserializer extends StdDeserializer<BatchPopulatingTask.OutputParameters> {

	private static final long serialVersionUID = -1219958118814469079L;

	public BatchPopulatingTaskOutputParametersDeserializer() {

		super(BatchPopulatingTask.InputParameters.class);
	}

	@Override
	public BatchPopulatingTask.OutputParameters deserialize(final JsonParser jp, final DeserializationContext ctx) throws IOException {

		ObjectMapper mapper = JSONUtils.OBJECT_MAPPER;
		JsonNode node = mapper.readTree(jp);
		JsonNode typeNode = node.get(BatchPopulatingTask.OutputParameters.FIELD_TYPE);
		if (typeNode == null) {
			throw new JsonMappingException("Cannot deserialize adi generation output parameters without type field");
		}
		String typeRaw = getFieldValue(typeNode);
		Class<? extends BatchPopulatingTask.OutputParameters> subtypeClass = classForType(BatchPopulatingTask.OutputParameters.Type.valueOf(typeRaw.toUpperCase()));
		return mapper.readValue(node.toString(), subtypeClass);
	}

	private Class<? extends BatchPopulatingTask.OutputParameters> classForType(final BatchPopulatingTask.OutputParameters.Type type) {

		switch (type) {
			case CSV:
				return CsvAdiOutputParameters.class;
		}
		throw new IllegalArgumentException("Unsupported adi generation output parameters type: " + type);
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
