/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store;

public final class GenericProperty extends Property<Object> {

	private static final long serialVersionUID = -1609654184805836183L;

	public GenericProperty(final String key, final Object value) {

		super(key, value);
	}

	public GenericProperty(final String key, final Object value, final Long timestamp) {

		super(key, value, timestamp);
	}

	GenericProperty() {

	}
}
