/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

/**
 * Model representation for <em>boolean properties</em>.
 */
public final class BooleanProperty extends Property<Boolean> {

	private static final long serialVersionUID = -806157898317946571L;

	/**
	 * Creates a new empty instance of {@link BooleanProperty}.
	 */
	public BooleanProperty() {
		/* Required by jackson. */
	}

	/**
	 * Creates a new instance of {@link BooleanProperty}.
	 */
	public BooleanProperty(final String key, final Boolean value) {

		super(key, value);
	}

	/**
	 * Creates a new instance of {@link BooleanProperty}.
	 */
	public BooleanProperty(final String key, final Boolean value, final Long timestamp) {

		super(key, value, timestamp);
	}
}
