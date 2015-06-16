/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

import java.util.List;

/**
 * Model representation for <em>batches</em>.
 */
public abstract class Batch extends DurableResourceModel {

	private static final long serialVersionUID = -4440334109484551488L;
	// TODO _MS_
	private String name;
	private String description;
	private List<TaskOnBatch> tasks;
}
