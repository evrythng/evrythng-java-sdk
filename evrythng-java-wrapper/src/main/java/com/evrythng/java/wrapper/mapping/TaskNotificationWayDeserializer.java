/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.java.wrapper.mapping;

import com.evrythng.java.wrapper.util.JSONUtils;
import com.evrythng.thng.resource.model.store.EmailTaskNotificationWay;
import com.evrythng.thng.resource.model.store.Task;
import com.evrythng.thng.resource.model.store.TaskOnBatch;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class TaskNotificationWayDeserializer extends Deserializer<Task.Notification.Way> {

	private static final long serialVersionUID = -4723188864323020225L;

	public TaskNotificationWayDeserializer() {

		super(Task.Notification.Way.class);
	}

	@Override
	public Task.Notification.Way deserialize(final JsonParser jp, final DeserializationContext ctx) throws IOException {

		ObjectMapper mapper = JSONUtils.OBJECT_MAPPER;
		JsonNode node = mapper.readTree(jp);
		JsonNode typeNode = node.get(TaskOnBatch.FIELD_TYPE);
		if (typeNode == null) {
			throw new JsonMappingException("Cannot deserialize task notification way without type field");
		}
		String typeRaw = getFieldValue(typeNode);
		Class<? extends Task.Notification.Way> subtypeClass = classForType(Task.Notification.Way.Type.valueOf(typeRaw.toUpperCase()));
		return mapper.readValue(node.toString(), subtypeClass);
	}

	private Class<? extends Task.Notification.Way> classForType(final Task.Notification.Way.Type type) {

		switch (type) {
			case EMAIL:
				return EmailTaskNotificationWay.class;
		}
		throw new IllegalArgumentException("Unsupported task notification way type: " + type);
	}
}
