/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.jobs;

public interface WithProgress<PROGRESS> {

	PROGRESS getProgress();

	void setProgress(PROGRESS progress);
}
