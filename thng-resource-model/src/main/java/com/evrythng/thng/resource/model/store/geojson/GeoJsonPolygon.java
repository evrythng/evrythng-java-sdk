package com.evrythng.thng.resource.model.store.geojson;

import java.util.List;

public abstract class GeoJsonPolygon extends GeoJson {

	private static final long serialVersionUID = 6352106353615096344L;
	private List<List<GeoJsonLocation>> coordinates;

	protected GeoJsonPolygon() {

		super(GeoJsonType.POLYGON);
	}

	public List<List<GeoJsonLocation>> getCoordinates() {

		return coordinates;
	}

	public void setCoordinates(final List<List<GeoJsonLocation>> coordinates) {

		this.coordinates = coordinates;
	}

	@Override
	public String toString() {

		return "GeoJsonPolygon [coordinates=" + coordinates + ", getGeoJsonType()=" + getGeoJsonType() + "]";
	}
}
