/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.mapping;

import com.evrythng.java.wrapper.util.JSONUtils;
import com.evrythng.thng.resource.model.store.BatchPopulatingTask;
import com.evrythng.thng.resource.model.store.ShortIdGenerationTask;
import com.evrythng.thng.resource.model.store.TaskOnBatch;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public final class TaskOnBatchDeserializer extends Deserializer<TaskOnBatch> {

	private static final long serialVersionUID = -8949860162832769733L;

	TaskOnBatchDeserializer() {

		super(TaskOnBatch.class);
	}

	@Override
	public TaskOnBatch deserialize(final JsonParser jp, final DeserializationContext ctx) throws IOException {

		ObjectMapper mapper = JSONUtils.OBJECT_MAPPER;
		JsonNode node = mapper.readTree(jp);
		JsonNode typeNode = node.get(TaskOnBatch.FIELD_TYPE);
		if (typeNode == null) {
			throw new JsonMappingException("Cannot deserialize task on batch without type field");
		}
		String typeRaw = getFieldValue(typeNode);
		Class<? extends TaskOnBatch> subtypeClass = classForType(TaskOnBatch.Type.valueOf(typeRaw.toUpperCase()));
		return mapper.readValue(node.toString(), subtypeClass);
	}

	private Class<? extends TaskOnBatch> classForType(final TaskOnBatch.Type type) {

		switch (type) {
			case POPULATING:
				return BatchPopulatingTask.class;
			case SHORT_ID_GENERATION:
				return ShortIdGenerationTask.class;
		}
		throw new IllegalArgumentException("Unsupported task on batch type: " + type);
	}
}
