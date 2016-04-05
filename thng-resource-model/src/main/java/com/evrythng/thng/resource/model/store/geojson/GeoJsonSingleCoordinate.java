package com.evrythng.thng.resource.model.store.geojson;

import com.evrythng.thng.resource.model.store.LocationHelper;
import com.evrythng.thng.resource.model.store.Traceable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GeoJsonSingleCoordinate extends GeoJson implements Traceable {

	private static final long serialVersionUID = -1353734859418227063L;
	private GeoJsonLocation coordinates;

	protected GeoJsonSingleCoordinate(final GeoJsonType type, final GeoJsonLocation coordinates) {

		super(type);
		this.coordinates = new GeoJsonLocation();
		this.coordinates.setCoordinates(coordinates.getCoordinates());
	}

	protected GeoJsonSingleCoordinate(final GeoJsonType type, final Double latitude, final Double longitude) {

		super(type);
		this.coordinates = new GeoJsonLocation(latitude, longitude);
	}

	@Override
	public boolean equals(final Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		GeoJsonSingleCoordinate that = (GeoJsonSingleCoordinate) o;

		return !(coordinates != null ? !coordinates.equals(that.coordinates) : that.coordinates != null);
	}

	@Override
	public int hashCode() {

		return coordinates != null ? coordinates.hashCode() : 0;
	}

	@JsonIgnore
	public Traceable getCoordinates() {

		return coordinates;
	}

	@JsonProperty(FIELD_COORDINATES)
	public Double[] getCoordinatesArr() {

		return coordinates.getCoordinates();
	}

	@JsonIgnore
	@JsonProperty(FIELD_COORDINATES)
	public void setCoordinates(final GeoJsonLocation coordinates) {

		this.coordinates = coordinates;
	}

	public void setCoordinatesArr(final Double... coordinates) {

		this.coordinates.setCoordinates(coordinates);
	}

	public void copy(final Traceable obj) {

		LocationHelper.copy(obj, this);
	}

	@Override
	@JsonIgnore
	public Double getLatitude() {

		return coordinates.getLatitude();
	}

	@Override
	public void setLatitude(final Double latitude) {

		this.coordinates.setLatitude(latitude);
	}

	@Override
	@JsonIgnore
	public Double getLongitude() {

		return coordinates.getLongitude();
	}

	@Override
	public void setLongitude(final Double longitude) {

		this.coordinates.setLongitude(longitude);
	}
}
