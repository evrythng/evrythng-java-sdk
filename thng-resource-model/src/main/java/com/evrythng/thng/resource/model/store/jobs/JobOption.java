/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.jobs;

public final class JobOption<TYPE extends Enum<TYPE>> {

	private TYPE type;
	public static final String FIELD_TYPE = "type";
	private Boolean enabled;
	public static final String FIELD_ENABLED = "enabled";

	public JobOption() {
		// for JSON deserialization
	}

	public JobOption(final TYPE type, final Boolean enabled) {

		this.type = type;
		this.enabled = enabled;
	}

	public final Boolean getEnabled() {

		return enabled;
	}

	public final void setEnabled(final Boolean enabled) {

		this.enabled = enabled;
	}

	public final TYPE getType() {

		return type;
	}

	public final void setType(final TYPE type) {

		this.type = type;
	}
}