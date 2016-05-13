/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

/**
 * Model representation for <em>properties</em>.
 */
public class StringProperty extends Property<String> {

	private static final long serialVersionUID = 795449469216913053L;

	/**
	 * Creates a new empty instance of {@link StringProperty}.
	 */
	public StringProperty() {
		/* Required by jackson. */
	}

	/**
	 * Creates a new instance of {@link StringProperty}.
	 */
	public StringProperty(final String key, final String value) {

		super(key, value);
	}

	/**
	 * Creates a new instance of {@link StringProperty}.
	 */
	public StringProperty(final String key, final String value, final Long timestamp) {

		super(key, value, timestamp);
	}
}
