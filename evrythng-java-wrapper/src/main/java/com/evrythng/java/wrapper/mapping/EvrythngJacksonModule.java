/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
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
