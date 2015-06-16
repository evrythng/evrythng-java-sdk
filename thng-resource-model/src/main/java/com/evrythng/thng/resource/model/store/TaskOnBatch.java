/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

public abstract class TaskOnBatch extends Task {

	private static final long serialVersionUID = 8091207835379916024L;
	private String batch;
	private Type type;
	public static final String FIELD_TYPE = "type";

	public Type getType() {

		return type;
	}

	public void setType(final Type type) {

		this.type = type;
	}

	public String getBatch() {

		return batch;
	}

	public void setBatch(final String batch) {

		this.batch = batch;
	}

	public static enum Type {

		POPULATING
	}
}
