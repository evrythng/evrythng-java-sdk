/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

/**
 * TODO write javadoc here
 */
public abstract class ShortIdTemplate {

	private Type type;

	public static enum Type {

		SEQUENTIAL, PSEUDO_RANDOM
	}

	public Type getType() {

		return type;
	}

	public void setType(final Type type) {

		this.type = type;
	}
}
