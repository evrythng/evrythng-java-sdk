/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.geojson;

import com.evrythng.thng.resource.model.store.LocationHelper;
import com.evrythng.thng.resource.model.store.Traceable;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Geo JSON location to be used within a parent model.
 */
public class GeoJsonLocation implements Traceable, Serializable {

	private static final long serialVersionUID = -2750554873532665915L;
	private Double[] coordinates;

	public GeoJsonLocation() {

		this(null, null);
	}

	public GeoJsonLocation(final Double latitude, final Double longitude) {

		this.coordinates = new Double[] { longitude, latitude };
	}

	@Override
	public Double getLatitude() {

		return coordinates[GeoJson.LAT_IDX];
	}

	@Override
	public void setLatitude(final Double latitude) {

		coordinates[GeoJson.LAT_IDX] = latitude;
	}

	@Override
	public Double getLongitude() {

		return coordinates[GeoJson.LON_IDX];
	}

	@Override
	public void setLongitude(final Double longitude) {

		coordinates[GeoJson.LON_IDX] = longitude;
	}

	public Double[] getCoordinates() {

		return coordinates;
	}

	public void setCoordinates(final Double... coordinates) {

		this.coordinates = coordinates;
	}

	public static GeoJsonLocation copyFrom(final GeoJsonLocation source) {

		return new GeoJsonLocation(source.getLatitude(), source.getLongitude());
	}

	public static GeoJsonLocation copyFrom(final Traceable source) {

		GeoJsonLocation loc = new GeoJsonLocation();
		LocationHelper.copy(source, loc);
		return loc;
	}

	@Override
	public boolean equals(final Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GeoJsonLocation that = (GeoJsonLocation) o;
		return Arrays.equals(coordinates, that.coordinates);
	}

	@Override
	public int hashCode() {

		return coordinates != null ? Arrays.hashCode(coordinates) : 0;
	}

	@Override
	public String toString() {

		return "GeoJsonLocation [coordinates=" + Arrays.toString(coordinates) + "]";
	}
}
