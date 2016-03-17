/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.jobs;

public interface WithCompletionDate {

	void setCompletedAt(final Long completedAt);

	Long getCompletedAt();
}
