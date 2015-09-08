/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.core;

import com.evrythng.thng.resource.model.core.validation.annotations.NotNullStandalone;

/**
 * Abstract model representation for <em>updatable</em> resources.
 */
public class DurationModel extends ResourceModel {
	
	private static final long serialVersionUID = 1382139954717129455L;

	// TODO _MS_ externalise
	@NotNullStandalone(message = "startsAt may not be null")
	private Long startsAt;
	// TODO _MS_ externalise
	@NotNullStandalone(message = "endsAt may not be null")
	private Long endsAt;

	public Long getEndsAt() {

		return endsAt;
	}

	public void setEndsAt(final Long endsAt) {

		this.endsAt = endsAt;
	}

	public Long getStartsAt() {

		return startsAt;
	}

	public void setStartsAt(final Long startsAt) {

		this.startsAt = startsAt;
	}
}
