/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store;

import java.util.Optional;

/**
 * Model representation for <em>batches</em> of ADIs.
 */
public class ADIBatch extends Batch {

	private static final long serialVersionUID = 6256944783439729499L;
	// TODO _MS_ here

	// if absent => default
	private Optional<ADIGenerationPolicy> adiGenerationPolicy;
}
