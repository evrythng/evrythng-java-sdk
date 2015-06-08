/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store;

import java.util.Optional;

/**
 * TODO write javadoc here
 */
public abstract class ADIGenerationPolicy {

	private String shortDomain;
	// absent => default
	private Optional<ThngsGenerationPolicy> thngsGenerationPolicy;
}
