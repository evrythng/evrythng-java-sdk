/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.mapping;

import com.evrythng.java.wrapper.util.JSONUtils;
import com.evrythng.thng.resource.model.store.action.jobs.creation.ActionJob;
import com.evrythng.thng.resource.model.store.action.jobs.creation.CreateActionJob;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public final class ActionJobDeserializer extends Deserializer<ActionJob> {

	private static final long serialVersionUID = -8949860162832769733L;

	public ActionJobDeserializer() {

		super(ActionJob.class);
	}

	@Override
	public ActionJob deserialize(final JsonParser jp, final DeserializationContext ctx) throws IOException {

		ObjectMapper mapper = JSONUtils.OBJECT_MAPPER;
		JsonNode node = mapper.readTree(jp);
		JsonNode typeNode = node.get(ActionJob.FIELD_TYPE);
		if (typeNode == null) {
			throw new JsonMappingException("Cannot deserialize actions job without type field");
		}
		String typeRaw = getFieldValue(typeNode);
		Class<? extends ActionJob> subtypeClass = classForType(ActionJob.Type.valueOf(typeRaw.toUpperCase()));
		return mapper.readValue(node.toString(), subtypeClass);
	}

	private Class<? extends ActionJob> classForType(final ActionJob.Type type) {

		switch (type) {
			case ACTION_CREATION:
				return CreateActionJob.class;
		}
		throw new IllegalArgumentException("Unsupported actions job type: " + type);
	}
}
