/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.content;

/**
 * Rest model for a MultimediaContent.
 */
public class MultimediaContent extends Content {

	private static final long serialVersionUID = -1826858368423203504L;

	private String htmlFragment;

	public MultimediaContent() {
	}

	public String getHtmlFragment() {
		return htmlFragment;
	}

	public void setHtmlFragment(String htmlFragment) {
		this.htmlFragment = htmlFragment;
	}

}
