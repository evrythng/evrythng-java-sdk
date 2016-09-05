/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.core;

/**
 * Abstract model representation for <em>updatable</em> resources.
 */
public abstract class DurableResourceModel extends ResourceModel {

	public static final String FIELD_UPDATED_AT = "updatedAt";
	private static final long serialVersionUID = -7896990713808718154L;
	private Long updatedAt;

	/**
	 * Unix time (milliseconds since Epoch) when resource was updated last time.
	 *
	 * @return instance of {@link Long}.
	 */
	public Long getUpdatedAt() {

		return updatedAt;
	}

	/**
	 * Read-only unix time (milliseconds since Epoch) when resource was updated last time. The value of this property is managed
	 * by platform so you should not to specify it.
	 */
	public void setUpdatedAt(final Long updatedAt) {

		this.updatedAt = updatedAt;
	}
}
