/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

import org.apache.commons.collections.list.SetUniqueList;

import java.util.ArrayList;
import java.util.List;

public class ListBasedAdiInputParameters extends BaseAdiInputParameters {

	public static final String FIELD_INPUT_DATA = "inputData";

	private List<RedirectionResource> inputData;


	public List<RedirectionResource> getInputData() {

		return inputData;
	}

	public void setInputData(final List<RedirectionResource> inputData) {

		this.inputData = inputData != null ? SetUniqueList.decorate(new ArrayList(inputData)) : null;
	}
}
