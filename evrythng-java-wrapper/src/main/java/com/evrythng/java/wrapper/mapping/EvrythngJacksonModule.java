/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.java.wrapper.mapping;

import com.fasterxml.jackson.databind.Module;

/**
 * Interface for internal EVRYTHNG jackson module.
 */
public interface EvrythngJacksonModule {

	Module getModule();
	ActionDeserializer getActionDeserializer();
	ReactionDeserializer getReactionDeserializer();
	GeoJsonDeserializer getGeoJSONDeserializer();
}
