/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store;

import java.util.List;

public class ListBasedAdiInputParameters extends BaseAdiInputParameters {

	public static final String FIELD_REDIRECTIONS = "inputData";

	private List<RedirectionResource> inputData;


	public List<RedirectionResource> getInputData() {

		return inputData;
	}

	public void setInputData(final List<RedirectionResource> inputData) {

		this.inputData = inputData;
	}
}
