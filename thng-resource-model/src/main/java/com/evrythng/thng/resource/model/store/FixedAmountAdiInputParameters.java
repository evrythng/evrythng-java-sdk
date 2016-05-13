/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

/**
 * Input for the BatchPopulatingTask. Defines a shortId template, and a quantity.
 */
public class FixedAmountAdiInputParameters extends BaseAdiInputParameters {

	private Number quantity;
	public static final String FIELD_QUANTITY = "quantity";

	private ShortIdTemplate shortIdTemplate;
	public static final String FIELD_SHORT_ID_TEMPLATE = "shortIdTemplate";

	public Number getQuantity() {

		return quantity;
	}

	public void setQuantity(final Number quantity) {

		this.quantity = quantity;
	}

	public ShortIdTemplate getShortIdTemplate() {

		return shortIdTemplate;
	}

	public void setShortIdTemplate(final ShortIdTemplate shortIdTemplate) {

		this.shortIdTemplate = shortIdTemplate;
	}
}
