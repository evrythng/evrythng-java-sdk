/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.mapping;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Base class for deserializers with a type map.
 *
 **/
public abstract class TypeMapDeserializer<T> extends Deserializer<T> {

	private static final long serialVersionUID = 1L;

	private Map<String, Class<? extends T>> registry = new HashMap<>();
	private Map<Class<? extends T>, String> reverseRegistry = new HashMap<>();
	private String typeFieldName;

	protected TypeMapDeserializer(final Class<?> cls, final String fieldName) {
		super(cls);
		this.typeFieldName = fieldName;
	}

	public void registerType(final String type, final Class<? extends T> objectClass) {

		registry.put(type, objectClass);
		reverseRegistry.put(objectClass, type);
	}

	@Override
	public T deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException {
		
		ObjectCodec codec = jp.getCodec();
		ObjectMapper mapper = (ObjectMapper) codec;
		ObjectNode root = mapper.readTree(jp);
		JsonNode type = root.get(typeFieldName);
		final String sType = type == null ? null : type.textValue();

		Class<? extends T> clazz = resolveClass(sType);

		return codec.treeToValue(root, clazz);
	}

	protected Class<? extends T> resolveClass(final String type) {

		Class<? extends T> clazz = registry.get(type);
		if (clazz == null) {
			throw new IllegalArgumentException(this.getValueClass().getSimpleName() + " type '" + type + "' is not recognized.");
		}
		return clazz;
	}

	public <U extends T> String getObjectType(final Class<U> objectClass) {
		return reverseRegistry.get(objectClass);
	}

	public String getTypeFieldName() {
		return typeFieldName;
	}
}
