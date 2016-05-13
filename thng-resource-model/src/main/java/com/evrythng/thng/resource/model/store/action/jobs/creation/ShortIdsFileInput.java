/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.action.jobs.creation;

public final class ShortIdsFileInput extends FileCreateActionJobInput {

	public static final String FIELD_SHORT_DOMAIN = "shortDomain";
	private String shortDomain;

	public String getShortDomain() {

		return shortDomain;
	}

	public void setShortDomain(final String shortDomain) {

		this.shortDomain = shortDomain;
	}

	@Override
	public Type getType() {

		return Type.FILE;
	}

	@Override
	public ContentType getContentType() {

		return ContentType.SHORT_IDS;
	}
}
