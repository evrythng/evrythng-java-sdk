/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store;

/**
 * TODO write javadoc here
 */
public class InternalADIGenerationPolicy extends ADIGenerationPolicy {

	// TODO _MS_ here quantity should probably be an object too
	private Long quantity;
	// absent => default
	private Optional<ShortIdsGenerationPolicy> shortIdsGenerationPolicy;
}
