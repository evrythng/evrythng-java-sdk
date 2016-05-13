/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
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
