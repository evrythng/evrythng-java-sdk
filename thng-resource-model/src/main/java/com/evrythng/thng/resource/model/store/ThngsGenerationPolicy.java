/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import java.util.Optional;

/**
 * TODO write javadoc here
 */
public class ThngsGenerationPolicy {

	private boolean generateThngs;
	private boolean generateApiKeys;
	// absent => default
	private Optional<Thng> template;
}
