/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.mapping;

import com.evrythng.java.wrapper.util.JSONUtils;
import com.evrythng.thng.resource.model.store.PseudoRandomShortIdTemplate;
import com.evrythng.thng.resource.model.store.SequentialShortIdTemplate;
import com.evrythng.thng.resource.model.store.ShortIdTemplate;
import com.evrythng.thng.resource.model.store.ThngIdShortIdTemplate;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ShortIdTemplateDeserializer extends Deserializer<ShortIdTemplate> {

	private static final long serialVersionUID = 6416974680823486697L;

	public ShortIdTemplateDeserializer() {

		super(ShortIdTemplate.class);
	}

	@Override
	public ShortIdTemplate deserialize(final JsonParser jp, final DeserializationContext ctx) throws IOException {

		ObjectMapper mapper = JSONUtils.OBJECT_MAPPER;
		JsonNode node = mapper.readTree(jp);
		JsonNode typeNode = node.get(ShortIdTemplate.FIELD_TYPE);
		if (typeNode == null) {
			throw new JsonMappingException("Cannot deserialize task on batch without type field");
		}
		String typeRaw = getFieldValue(typeNode);
		Class<? extends ShortIdTemplate> subtypeClass = classForType(ShortIdTemplate.Type.valueOf(typeRaw.toUpperCase()));
		return mapper.readValue(node.toString(), subtypeClass);
	}

	private Class<? extends ShortIdTemplate> classForType(final ShortIdTemplate.Type type) {

		switch (type) {
			case THNG_ID:
				return ThngIdShortIdTemplate.class;
			case PSEUDO_RANDOM:
				return PseudoRandomShortIdTemplate.class;
			case SEQUENTIAL:
				return SequentialShortIdTemplate.class;
		}
		throw new IllegalArgumentException("Unsupported short id template type: " + type);
	}
}
