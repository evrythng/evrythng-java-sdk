/*
 * (c) Copyright Reserved EVRYTHNG Limited 2018. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */

package com.evrythng.thng.resource.model.store;

/**
 * Representation of a campaign which is a field on the {@link Project} entity
 */
public class Campaign {
	private String url;
	private Template template;

	/**
	 * Gets the url of the campaign
	 *
	 * @return String
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the url of the campaign
	 *
	 * @param url - url to set
	 */
	public void setUrl(final String url) {
		this.url = url;
	}

	/**
	 * Gets the template of the campaign
	 *
	 * @return Template
	 */
	public Template getTemplate() {
		return template;
	}

	/**
	 * Sets the template of the campaign
	 *
	 * @param template - template to set
	 */
	public void setTemplate(final Template template) {
		this.template = template;
	}
}
