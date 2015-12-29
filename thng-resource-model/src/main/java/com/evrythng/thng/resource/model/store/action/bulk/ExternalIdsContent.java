/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.action.bulk;

public class ExternalIdsContent extends Content {

	private static final long serialVersionUID = -1974694261626429343L;
	private String externalIdKey;

	public ExternalIdsContent() {

		setType(Type.EXTERNAL_IDS);
	}

	public String getExternalIdKey() {

		return externalIdKey;
	}

	public void setExternalIdKey(final String externalIdKey) {

		this.externalIdKey = externalIdKey;
	}
}
