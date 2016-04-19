/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.commons.pagination;

import com.evrythng.commons.domain.Value;

import static com.evrythng.commons.validation.preconditions.NotEmptyString.notEmptyString;

public final class LastPageElementId extends Value<String> {

	public static LastPageElementId of(final String value) {

		return new LastPageElementId(value);
	}

	private LastPageElementId(final String value) {

		super(value, notEmptyString());
	}
}