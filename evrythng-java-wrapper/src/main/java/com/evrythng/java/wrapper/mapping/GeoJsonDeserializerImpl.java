/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.mapping;

import com.evrythng.thng.resource.model.store.geojson.GeoJson;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

/**
 * GeoJSON deserializer.
 * 
 **/
public class GeoJsonDeserializerImpl extends TypeMapDeserializer<GeoJson> implements GeoJsonDeserializer {

	private static final long serialVersionUID = 1L;

	GeoJsonDeserializerImpl() {
		super(GeoJson.class, GeoJson.FIELD_TYPE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends GeoJson> String getGeoJsonType(Class<T> geoJsonClass) {
		return getObjectType(geoJsonClass);
	}
	
	@Override
	public GeoJson deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		ObjectCodec codec = jp.getCodec();
		ObjectMapper mapper = (ObjectMapper) codec;
		ObjectNode root = (ObjectNode) mapper.readTree(jp);
		JsonNode type = root.get(getTypeFieldName());
		if (type == null) {
			throw new IllegalArgumentException(this.getValueClass().getSimpleName() + " type cannot be empty.");
		}
		String sType = type.textValue();
		if (sType == null || sType.isEmpty()) {
			throw new IllegalArgumentException(this.getValueClass().getSimpleName() + " type cannot be empty.");
		}
		
		Class<GeoJson> clazz = (Class<GeoJson>) resolveClass(sType);

		GeoJson obj = codec.treeToValue(root, clazz);
		if (obj == null) {
			throw new IllegalArgumentException(this.getValueClass().getSimpleName() + " type deserialised as null: " + root.toString());
		}
		return obj;
	}
}