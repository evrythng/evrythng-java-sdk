/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.mapping;

import com.evrythng.java.wrapper.util.JSONUtils;
import com.evrythng.thng.resource.model.store.action.jobs.creation.CreateActionJob;
import com.evrythng.thng.resource.model.store.action.jobs.creation.EvrythngIdsFileInput;
import com.evrythng.thng.resource.model.store.action.jobs.creation.EvrythngIdsListInput;
import com.evrythng.thng.resource.model.store.action.jobs.creation.EvrythngIdsPrivateFileInput;
import com.evrythng.thng.resource.model.store.action.jobs.creation.ExternalIdsFileInput;
import com.evrythng.thng.resource.model.store.action.jobs.creation.ExternalIdsListInput;
import com.evrythng.thng.resource.model.store.action.jobs.creation.ExternalIdsPrivateFileInput;
import com.evrythng.thng.resource.model.store.action.jobs.creation.ShortIdsFileInput;
import com.evrythng.thng.resource.model.store.action.jobs.creation.ShortIdsListInput;
import com.evrythng.thng.resource.model.store.action.jobs.creation.ShortIdsPrivateFileInput;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public final class CreateActionsJobInputDeserializer extends StdDeserializer<CreateActionJob.Input> {

	private static final long serialVersionUID = -5972944034677022833L;

	public CreateActionsJobInputDeserializer() {

		super(CreateActionJob.Input.class);
	}

	@Override
	public CreateActionJob.Input deserialize(final JsonParser jp, final DeserializationContext ctx) throws IOException {

		ObjectMapper mapper = JSONUtils.OBJECT_MAPPER;
		JsonNode node = mapper.readTree(jp);
		JsonNode typeNode = node.get(CreateActionJob.Input.FIELD_TYPE);
		if (typeNode == null) {
			throw new JsonMappingException("Cannot deserialize create actions job input without type field");
		}
		String typeRaw = getFieldValue(typeNode);
		JsonNode contentTypeNode = node.get(CreateActionJob.Input.FIELD_CONTENT_TYPE);
		if (contentTypeNode == null) {
			throw new JsonMappingException("Cannot deserialize create actions job input without contentType field");
		}
		String contentTypeRaw = getFieldValue(contentTypeNode);
		Class<? extends CreateActionJob.Input> subtypeClass = classForType(CreateActionJob.Input.Type.valueOf(typeRaw.toUpperCase()), CreateActionJob.Input.ContentType.valueOf(contentTypeRaw.toUpperCase()));
		return mapper.readValue(node.toString(), subtypeClass);
	}

	private Class<? extends CreateActionJob.Input> classForType(final CreateActionJob.Input.Type type, final CreateActionJob.Input.ContentType contentType) {

		switch (type) {
			case FILE:
				switch (contentType) {
					case EVRYTHNG_IDS:
						return EvrythngIdsFileInput.class;
					case EXTERNAL_IDS:
						return ExternalIdsFileInput.class;
					case SHORT_IDS:
						return ShortIdsFileInput.class;
				}
			case PRIVATE_FILE:
				switch (contentType) {
					case EVRYTHNG_IDS:
						return EvrythngIdsPrivateFileInput.class;
					case EXTERNAL_IDS:
						return ExternalIdsPrivateFileInput.class;
					case SHORT_IDS:
						return ShortIdsPrivateFileInput.class;
				}
			case LIST:
				switch (contentType) {
					case EVRYTHNG_IDS:
						return EvrythngIdsListInput.class;
					case EXTERNAL_IDS:
						return ExternalIdsListInput.class;
					case SHORT_IDS:
						return ShortIdsListInput.class;
				}
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
