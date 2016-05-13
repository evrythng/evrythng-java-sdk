/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.action;

import com.evrythng.commons.annotations.csv.CsvTransient;

/**
 * Base class for actions that contain a product and or a thng.
 **/
public abstract class ThngAction extends Action {

	private static final long serialVersionUID = 7873072067511462166L;

	private String thng;
	public static final String FIELD_THNG = "thng";

	private String product;
	public static final String FIELD_PRODUCT = "product";

	private String shortId;
	private String shortDomain;

	@Override
	public String toString() {
		return super.toString() + ",ThngAction[thng=" + thng + ",product=" + product + ",shortId=" + shortId + "]";
	}

	public String getThng() {
		return thng;
	}

	public void setThng(String thng) {
		this.thng = thng;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	@CsvTransient
	public String getShortId() {
		return shortId;
	}

	public void setShortId(String shortId) {
		this.shortId = shortId;
	}

	@CsvTransient
	public String getShortDomain() {
		return shortDomain;
	}

	public void setShortDomain(String shortDomain) {
		this.shortDomain = shortDomain;
	}

}
