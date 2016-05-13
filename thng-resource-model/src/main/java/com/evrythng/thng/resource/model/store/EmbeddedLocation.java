/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.commons.annotations.csv.CsvSerializer;
import com.evrythng.thng.resource.model.store.geojson.GeoJsonPoint;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Map;

/**
 * Location to be used within a parent model.
 */
@CsvSerializer("toCsvString")
public class EmbeddedLocation implements Locatable, Serializable {

	private static final long serialVersionUID = 3095613094764949496L;
	private String place;
	private Double latitude;
	private Double longitude;
	private GeoJsonPoint position;
	private Map<String, Object> customFields;

	public EmbeddedLocation() {

	}

	public EmbeddedLocation(final String place, final Double latitude, final Double longitude) {

		this.latitude = latitude;
		this.longitude = longitude;
		this.setPosition(new GeoJsonPoint(latitude, longitude));
		this.place = place;
	}

	@Override
	public String getPlace() {

		return place;
	}

	@Override
	public void setPlace(final String place) {

		this.place = place;
	}

	/**
	 * TODO For API backward compatibility. Once compatibility is not needed
	 * anymore, use {@link JsonIgnore}.
	 */
	@Override
	public Double getLatitude() {

		return latitude;
	}

	/**
	 * TODO For API backward compatibility. Once compatibility is not needed
	 * anymore, use {@link JsonIgnore}.
	 */
	@Override
	public void setLatitude(final Double latitude) {

		this.latitude = latitude;
	}

	/**
	 * TODO For API backward compatibility. Once compatibility is not needed
	 * anymore, use {@link JsonIgnore}.
	 */
	@Override
	public Double getLongitude() {

		return longitude;
	}

	/**
	 * TODO For API backward compatibility. Once compatibility is not needed
	 * anymore, use {@link JsonIgnore}.
	 */
	@Override
	public void setLongitude(final Double longitude) {

		this.longitude = longitude;
	}

	@Override
	public GeoJsonPoint getPosition() {

		return position;
	}

	@JsonIgnore
	public boolean hasPosition() {

		return position != null && position.getLatitude() != null && position.getLongitude() != null;
	}

	@Override
	public void setPosition(final GeoJsonPoint position) {

		this.position = position;
	}

	@Override
	public Map<String, Object> getCustomFields() {

		return customFields;
	}

	@Override
	public void setCustomFields(final Map<String, Object> customFields) {

		this.customFields = customFields;
	}

	public static EmbeddedLocation copyFrom(final Locatable source) {

		EmbeddedLocation loc = new EmbeddedLocation();
		LocationHelper.copy(source, loc);
		return loc;
	}

	@Override
	public String toString() {

		return "EmbeddedLocation [place=" + place + ", latitude=" + latitude + ", longitude=" + longitude + ", position=" + position + ", customFields=" + customFields + ", getPlace()=" + getPlace() + ", getLatitude()=" + getLatitude()
				+ ", getLongitude()=" + getLongitude() + ", getPosition()=" + getPosition() + ", getCustomFields()=" + getCustomFields() + "]";
	}

	public String toCsvString() {

		return String.format("(%.4f;%.4f)", getLatitude(), getLongitude());
	}
}
