/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store;

/**
 * TODO write javadoc here
 */
public class FixedAmountAdiInputParameters extends BaseAdiInputParameters {

	private ThngTemplate thngTemplate;
	private Boolean generateThngs;
	private Boolean generateRedirections;
	
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

	@Override
	public Boolean getGenerateRedirections() {

		return generateRedirections;
	}

	public void setGenerateRedirections(final Boolean generateRedirections) {

		this.generateRedirections = generateRedirections;
	}

	@Override
	public Boolean getGenerateThngs() {

		return generateThngs;
	}

	public void setGenerateThngs(final Boolean generateThngs) {

		this.generateThngs = generateThngs;
	}

	public ThngTemplate getThngTemplate() {

		return thngTemplate;
	}

	public void setThngTemplate(final ThngTemplate thngTemplate) {

		this.thngTemplate = thngTemplate;
	}
}
