/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

import java.io.Serializable;

/**
 * Model class for a timestamp.
 *
 * @deprecated Since 1.18.1 Use TimeInfo instead.
 */
@Deprecated
public class Timestamp implements Serializable {

	private static final long serialVersionUID = -2460519576290598569L;
	private Long timestamp;

	/**
	 * @return the timestamp
	 */
	public Long getTimestamp() {

		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(final Long timestamp) {

		this.timestamp = timestamp;
	}
}
