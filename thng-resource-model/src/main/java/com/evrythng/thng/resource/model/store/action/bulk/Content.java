/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.action.bulk;

import java.io.Serializable;

public abstract class Content implements Serializable {

	private static final long serialVersionUID = -285835754693959396L;
	public static final String FIELD_TYPE = "type";
	private Type type;

	public Type getType() {

		return type;
	}

	public void setType(final Type type) {

		this.type = type;
	}

	public enum Type {
		EVRYTHNG_IDS, EXTERNAL_IDS, SHORT_IDS
	}
}
