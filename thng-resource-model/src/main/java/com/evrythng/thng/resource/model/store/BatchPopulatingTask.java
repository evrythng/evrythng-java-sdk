/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

public class BatchPopulatingTask extends TaskOnBatch {

	private static final long serialVersionUID = 2514640549756857875L;
	private AdiGenerationPolicy adiGenerationPolicy;

	public AdiGenerationPolicy getAdiGenerationPolicy() {

		return adiGenerationPolicy;
	}

	public void setAdiGenerationPolicy(final AdiGenerationPolicy adiGenerationPolicy) {

		this.adiGenerationPolicy = adiGenerationPolicy;
	}
}
