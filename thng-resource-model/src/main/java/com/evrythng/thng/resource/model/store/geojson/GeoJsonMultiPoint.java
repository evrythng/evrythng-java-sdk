/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.geojson;

/**
 * Multi point geojson.
 */
public class GeoJsonMultiPoint extends GeoJsonMultiCoordinate {

	private static final long serialVersionUID = 4413728162577148877L;

	public GeoJsonMultiPoint() {

		super(GeoJsonType.MULTI_POINT);
	}
}
