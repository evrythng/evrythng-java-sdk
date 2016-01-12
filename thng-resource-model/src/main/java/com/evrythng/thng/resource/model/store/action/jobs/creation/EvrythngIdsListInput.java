/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.action.jobs.creation;

public final class EvrythngIdsListInput extends ListCreateActionJobInput {

	@Override
	public Type getType() {

		return Type.LIST;
	}

	@Override
	public ContentType getContentType() {

		return ContentType.EVRYTHNG_IDS;
	}
}
