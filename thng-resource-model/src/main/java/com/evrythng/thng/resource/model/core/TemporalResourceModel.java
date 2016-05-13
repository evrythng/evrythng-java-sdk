/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.core;

import java.util.Comparator;

/**
 * Abstract model representation for temporal resources.
 */
public abstract class TemporalResourceModel extends ResourceModel {

	private static final long serialVersionUID = 7353110026977836985L;
	public static final Comparator<TemporalResourceModel> REVERSE_TIME_COMPARATOR = new Comparator<TemporalResourceModel>() {

		@Override
		public int compare(final TemporalResourceModel arg0, final TemporalResourceModel arg1) {

			if (arg0.getTimestamp() > arg1.getTimestamp()) {
				return -1;
			} else {
				return arg0.getTimestamp() < arg1.getTimestamp() ? 1 : 0;
			}
		}
	};
	private Long timestamp;
	public static String FIELD_TIMESTAMP = "timestamp";

	protected TemporalResourceModel() {

	}

	protected TemporalResourceModel(final Long timestamp) {

		this.timestamp = timestamp;
	}

	public Long getTimestamp() {

		return timestamp;
	}

	public void setTimestamp(final Long timestamp) {

		this.timestamp = timestamp;
	}
}