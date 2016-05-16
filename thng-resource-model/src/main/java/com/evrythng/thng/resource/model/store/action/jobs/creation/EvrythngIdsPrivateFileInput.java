/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.action.jobs.creation;

public class EvrythngIdsPrivateFileInput extends PrivateFileCreateActionJobInput {

	@Override
	public ContentType getContentType() {

		return ContentType.EVRYTHNG_IDS;
	}
}
