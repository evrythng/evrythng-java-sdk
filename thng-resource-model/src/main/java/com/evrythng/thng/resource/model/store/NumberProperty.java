/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

/**
 * Model representation for <em>number properties</em>.
 */
public final class NumberProperty extends Property<Double> {

	private static final long serialVersionUID = -806157898317946571L;

	/**
	 * Creates a new empty instance of {@link NumberProperty}.
	 */
	public NumberProperty() {
		/* Required by jackson. */
	}

	/**
	 * Creates a new instance of {@link NumberProperty}.
	 */
	public NumberProperty(final String key, final Double value) {

		super(key, value);
	}

	/**
	 * Creates a new instance of {@link NumberProperty}.
	 */
	public NumberProperty(final String key, final Double value, final Long timestamp) {

		super(key, value, timestamp);
	}
}
