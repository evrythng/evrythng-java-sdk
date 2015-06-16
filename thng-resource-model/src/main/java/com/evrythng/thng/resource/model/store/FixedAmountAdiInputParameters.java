/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store;

/**
 * TODO write javadoc here
 */
public class FixedAmountAdiInputParameters extends BaseAdiInputParameters {

	private Long quantity;
	private ShortIdTemplate shortIdTemplate;

	public Long getQuantity() {

		return quantity;
	}

	public void setQuantity(final Long quantity) {

		this.quantity = quantity;
	}

	public ShortIdTemplate getShortIdTemplate() {

		return shortIdTemplate;
	}

	public void setShortIdTemplate(final ShortIdTemplate shortIdTemplate) {

		this.shortIdTemplate = shortIdTemplate;
	}
}
