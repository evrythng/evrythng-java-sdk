/*
 * (c) Copyright 2014 EVRYTHNG Ltd London / Olten
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.core;

/**
 * Return type of a bulk update operation for the different service. Contains
 * only the number of update results.
 * 
 **/
public class UpdatedResourcesCount {

	public final long updatedCount;

	public UpdatedResourcesCount(long updatedCount) {
		if (updatedCount < 0) {
			throw new IllegalArgumentException("Negative : " + updatedCount);
		}
		this.updatedCount = updatedCount;
	}
}
