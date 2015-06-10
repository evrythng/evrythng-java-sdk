/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.mapping;

import com.evrythng.thng.resource.model.store.geojson.GeoJson;

/**
 * GeoJson deserializer interface.
 * 
 * @author colin
 **/
public interface GeoJsonDeserializer {

	/**
	 * Gets the GeoJson type that correspond to the class.
	 * 
	 * @return The GeoJson type, or null if the class is not recognized.
	 */
	<T extends GeoJson> String getGeoJsonType(Class<T> geoJsonClass);
}
