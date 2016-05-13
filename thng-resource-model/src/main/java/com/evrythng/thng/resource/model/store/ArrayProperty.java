/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

import java.util.ArrayList;
import java.util.List;

/**
 * Model representation for <em>number properties</em>.
 */
public final class ArrayProperty extends Property<List<Object>> {

	private static final long serialVersionUID = -806157898317946571L;

	/**
	 * Creates a new empty instance of {@link ArrayProperty}.
	 */
	public ArrayProperty() {
		/* Required by jackson. */
	}

	/**
	 * Creates a new instance of {@link ArrayProperty}.
	 */
	public ArrayProperty(final String key, final List<Object> value) {

		super(key, value != null ? new ArrayList<>(value) : null);
	}

	/**
	 * Creates a new instance of {@link ArrayProperty}.
	 */
	public ArrayProperty(final String key, final List<Object> value, final Long timestamp) {

		super(key, value != null ? new ArrayList<>(value) : null, timestamp);
	}
}
