/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.java.wrapper.mapping;

import com.evrythng.java.wrapper.util.JSONUtils;
import com.evrythng.thng.resource.model.store.thng.jobs.ThngJob;
import com.evrythng.thng.resource.model.store.thng.jobs.creation.CreateThngsJob;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public final class ThngJobDeserializer extends Deserializer<ThngJob<?>> {

	private static final long serialVersionUID = -7706678397691559375L;

	public ThngJobDeserializer() {

		super(ThngJob.class);
	}

	@Override
	public ThngJob<?> deserialize(final JsonParser jp, final DeserializationContext ctx) throws IOException {

		ObjectMapper mapper = JSONUtils.OBJECT_MAPPER;
		JsonNode node = mapper.readTree(jp);
		JsonNode typeNode = node.get(ThngJob.FIELD_TYPE);
		if (typeNode == null) {
			throw new JsonMappingException("Cannot deserialize thng job without type field");
		}
		String typeRaw = getFieldValue(typeNode);
		Class<? extends ThngJob<?>> subtypeClass = classForType(ThngJob.Type.valueOf(typeRaw.toUpperCase()));
		return mapper.readValue(node.toString(), subtypeClass);
	}

	private Class<? extends ThngJob<?>> classForType(final ThngJob.Type type) {

		switch (type) {
			case CREATE_THNGS:
				return CreateThngsJob.class;
		}
		throw new IllegalArgumentException("Unsupported thng job type: " + type);
	}
}
