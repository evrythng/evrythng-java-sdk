/*
 * (c) Copyright 2014 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

/**
 * Model representation for <em>accounts</em>.
 */
public class Account extends DurableResourceModel {

	private static final long serialVersionUID = -3583008548329971679L;
	/**
	 * The account's name.
	 */
	private String name;
	/**
	 * The account's image url.
	 */
	private String imageUrl;
	private Boolean tfaRequired;

	public String getName() {

		return name;
	}

	public void setName(final String name) {

		this.name = name;
	}

	public String getImageUrl() {

		return imageUrl;
	}

	public void setImageUrl(final String imageUrl) {

		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder("Account{");
		sb.append("id='").append(getId()).append("\', ");
		sb.append("name='").append(name).append("\', ");
		sb.append("imageUrl='").append(imageUrl).append("\'");
		sb.append('}');
		return sb.toString();
	}

	public Boolean getTfaRequired() {
		return tfaRequired;
	}

	public void setTfaRequired(final Boolean tfaRequired) {
		this.tfaRequired = tfaRequired;
	}
}
