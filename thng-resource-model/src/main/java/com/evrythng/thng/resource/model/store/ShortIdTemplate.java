/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

/**
 * Abstract structure to represent the possible shortId templates to be used in BatchPopulatingTask
 */
public abstract class ShortIdTemplate {

	private Type type;
	public static final String FIELD_TYPE = "type";

	public static enum Type {

		SEQUENTIAL, PSEUDO_RANDOM, THNG_ID
	}

	public Type getType() {

		return type;
	}

	public void setType(final Type type) {

		this.type = type;
	}
}
