/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.store.geojson.GeoJsonPoint;

import java.util.HashMap;
import java.util.Random;

/**
 * Helper class for locations.
 */
public class LocationHelper {

	public static Random random = new Random();

	public static void copy(final Locatable from, final Locatable to) {

		copy(from, to);
		to.setPlace(from.getPlace());
		to.setCustomFields(new HashMap<>(from.getCustomFields()));
	}

	public static void copy(final Positionable from, final Positionable to) {

		copy(from, to);
		to.setPosition(new GeoJsonPoint(from.getLatitude(), from.getLongitude()));
	}

	public static void copy(final Traceable from, final Traceable to) {

		to.setLatitude(from.getLatitude());
		to.setLongitude(from.getLongitude());
	}

	/**
	 * Determines if the coordinates are valid.
	 *
	 * @return true if loc is null or if both coordinates are null or if the
	 * location is valid; false otherwise.
	 */
	public static boolean isGeoValid(final Traceable loc) {

		if (loc == null) {
			return true;
		}
		if (loc.getLatitude() == null && loc.getLongitude() == null) {
			return true;
		} else {
			return loc.getLatitude() != null && loc.getLongitude() != null ? loc.getLatitude() >= -90 && loc.getLatitude() <= 90 && loc.getLongitude() >= -180 && loc.getLongitude() <= 180 : false;
		}
	}

	public static boolean coordinatesEqual(final Traceable a, final Traceable b) {

		if (a == b) {
			return true;
		}
		if (a == null || b == null) {
			return false;
		}
		final double TOLERANCE = 0.0000001;
		boolean lat;
		boolean lon;
		if (a.getLatitude() == null && b.getLatitude() == null) {
			lat = true;
		} else {
			lat = a.getLatitude() == null || b.getLatitude() == null ? false : Math.abs(a.getLatitude() - b.getLatitude()) < TOLERANCE;
		}
		if (!lat) {
			return false;
		}
		if (a.getLongitude() == null && b.getLongitude() == null) {
			lon = true;
		} else {
			lon = a.getLongitude() == null || b.getLongitude() == null ? false : Math.abs(a.getLongitude() - b.getLongitude()) < TOLERANCE;
		}
		return lon;
	}

	public static double randomLatitude() {

		return -90 + random.nextDouble() * (90 - -90);
	}

	public static double randomLongitude() {

		return -180 + random.nextDouble() * (180 - -180);
	}
}
