/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
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

public class TaskResultDeserializer extends Deserializer<TaskOnBatch.BaseTaskResult> {

	public TaskResultDeserializer() {

		super(TaskOnBatch.BaseTaskResult.class);
	}

	@Override
	public TaskOnBatch.BaseTaskResult deserialize(final JsonParser jp, final DeserializationContext ctx) throws IOException {

		ObjectMapper mapper = JSONUtils.OBJECT_MAPPER;
		JsonNode node = mapper.readTree(jp);
		JsonNode typeNode = node.get(TaskOnBatch.FIELD_TYPE);
		if (typeNode == null) {
			throw new JsonMappingException("Cannot deserialize task result without type field");
		}
		String typeRaw = getFieldValue(typeNode);
		Class<? extends TaskOnBatch.BaseTaskResult> subtypeClass = classForType(TaskOnBatch.BaseTaskResult.Type.valueOf(typeRaw.toUpperCase()));
		return mapper.readValue(node.toString(), subtypeClass);
	}

	private Class<? extends TaskOnBatch.BaseTaskResult> classForType(final TaskOnBatch.BaseTaskResult.Type type) {

		switch (type) {
			case POPULATING:
				return BatchPopulatingTask.Result.class;
            case SHORT_ID_GENERATION:
                return ShortIdGenerationTask.Result.class;
            case ERROR:
                return TaskOnBatch.ErrorTaskResult.class;
		}
		throw new IllegalArgumentException("Unsupported task result type: " + type);
	}
}
