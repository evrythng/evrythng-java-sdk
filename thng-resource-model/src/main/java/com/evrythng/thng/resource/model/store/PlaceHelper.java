/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.store.geojson.GeoJsonPoint;

/**
 * Helper class for places.
 */
public class PlaceHelper {

	public static Double[] getCoordinates(final GeoJsonPoint location) {

		try {
			return new Double[] { location.getLongitude(), location.getLatitude() };
		} catch (NullPointerException e) {
			return null;
		}
	}
}
