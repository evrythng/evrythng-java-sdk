/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

/**
 * TODO write javadoc here
 */
class BaseAdiInputParameters implements AdiGenerationPolicy.InputParameters {

	private Type type;

	@Override
	public final Type getType() {

		return type;
	}

	@Override
	public final void setType(final Type type) {

		this.type = type;
	}
}
