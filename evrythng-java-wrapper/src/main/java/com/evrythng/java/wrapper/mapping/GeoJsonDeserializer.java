/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.mapping;

import com.evrythng.thng.resource.model.store.geojson.GeoJson;

/**
 * GeoJson deserializer interface.
 * 
 **/
public interface GeoJsonDeserializer {

	/**
	 * Gets the GeoJson type that correspond to the class.
	 * 
	 * @return The GeoJson type, or null if the class is not recognized.
	 */
	<T extends GeoJson> String getGeoJsonType(Class<T> geoJsonClass);
}
