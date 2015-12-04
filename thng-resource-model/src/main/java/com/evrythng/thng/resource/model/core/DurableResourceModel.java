/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.core;

/**
 * Abstract model representation for <em>updatable</em> resources.
 */
public abstract class DurableResourceModel extends ResourceModel {

	private static final long serialVersionUID = -7896990713808718154L;
	public static final String FIELD_UPDATED_AT = "updatedAt";
	private Long updatedAt;

	public Long getUpdatedAt() {

		return updatedAt;
	}

	public void setUpdatedAt(final Long updatedAt) {

		this.updatedAt = updatedAt;
	}
}
