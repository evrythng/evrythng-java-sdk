/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.mapping;

import com.evrythng.java.wrapper.util.JSONUtils;
import com.evrythng.thng.resource.model.store.BatchPopulatingTask;
import com.evrythng.thng.resource.model.store.FileBasedAdiInputParameters;
import com.evrythng.thng.resource.model.store.FixedAmountAdiInputParameters;
import com.evrythng.thng.resource.model.store.ListBasedAdiInputParameters;
import com.evrythng.thng.resource.model.store.PrivateFileBasedAdiInputParameters;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BatchPopulatingTaskInputParametersDeserializer extends StdDeserializer<BatchPopulatingTask.InputParameters> {

	private static final long serialVersionUID = -1219958118814469079L;

	public BatchPopulatingTaskInputParametersDeserializer() {

		super(BatchPopulatingTask.InputParameters.class);
	}

	@Override
	public BatchPopulatingTask.InputParameters deserialize(final JsonParser jp, final DeserializationContext ctx)
			throws IOException {

		ObjectMapper mapper = JSONUtils.OBJECT_MAPPER;
		JsonNode node = mapper.readTree(jp);
		JsonNode typeNode = node.get(BatchPopulatingTask.InputParameters.FIELD_TYPE);
		if (typeNode == null) {
			throw new JsonMappingException("Cannot deserialize adi generation input parameters without type field");
		}
		String typeRaw = getFieldValue(typeNode);
		Class<? extends BatchPopulatingTask.InputParameters> subtypeClass = classForType(
				BatchPopulatingTask.InputParameters.Type.valueOf(typeRaw.toUpperCase()));
		return mapper.readValue(node.toString(), subtypeClass);
	}

	private Class<? extends BatchPopulatingTask.InputParameters> classForType(
			final BatchPopulatingTask.InputParameters.Type type) {

		switch (type) {
			case FIXED_AMOUNT:
				return FixedAmountAdiInputParameters.class;
			case FILE_BASED:
				return FileBasedAdiInputParameters.class;
			case PRIVATE_FILE_BASED:
				return PrivateFileBasedAdiInputParameters.class;
			case LIST_BASED:
				return ListBasedAdiInputParameters.class;
		}
		throw new IllegalArgumentException("Unsupported adi generation quantifier type: " + type);
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
