/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.action.jobs.creation;

public final class ExternalIdsFileInput extends FileCreateActionJobInput {

	public static final String FIELD_KEY = "key";
	private String key;

	public String getKey() {

		return key;
	}

	public void setKey(final String key) {

		this.key = key;
	}

	@Override
	public Type getType() {

		return Type.FILE;
	}

	@Override
	public ContentType getContentType() {

		return ContentType.EXTERNAL_IDS;
	}
}
