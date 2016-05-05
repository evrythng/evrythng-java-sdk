package com.evrythng.thng.resource.model.store.action.jobs.creation;

public class ShortIdsPrivateFileInput extends PrivateFileCreateActionJobInput {

	public static final String FIELD_SHORT_DOMAIN = "shortDomain";
	private String shortDomain;

	public String getShortDomain() {

		return shortDomain;
	}

	public void setShortDomain(final String shortDomain) {

		this.shortDomain = shortDomain;
	}

	@Override
	public ContentType getContentType() {

		return ContentType.SHORT_IDS;
	}
}
