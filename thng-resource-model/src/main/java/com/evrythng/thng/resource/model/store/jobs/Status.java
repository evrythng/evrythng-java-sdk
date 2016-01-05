/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.jobs;

public enum Status {
	ENQUEUED, STARTING, EXECUTING, EXECUTED, CANCELING, CANCELLED, FAILED
}
