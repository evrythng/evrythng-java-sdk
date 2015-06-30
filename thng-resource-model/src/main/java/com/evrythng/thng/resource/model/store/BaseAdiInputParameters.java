/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

/**
 * Common base for BatchPopulatingTask ADI generator
 */
public abstract class BaseAdiInputParameters implements BatchPopulatingTask.InputParameters {

	private Boolean generateThngs;
	private Boolean generateRedirections;

	private String shortDomain;

	private String defaultRedirectUrl;

	private ThngTemplate thngTemplate;

	private Type type;

	@Override
	public final Type getType() {

		return type;
	}

	@Override
	public final void setType(final Type type) {

		this.type = type;
	}

	public String getShortDomain() {

		return shortDomain;
	}

	public void setShortDomain(final String shortDomain) {

		this.shortDomain = shortDomain;
	}

	public String getDefaultRedirectUrl() {

		return defaultRedirectUrl;
	}

	public void setDefaultRedirectUrl(final String defaultRedirectUrl) {

		this.defaultRedirectUrl = defaultRedirectUrl;
	}

	public ThngTemplate getThngTemplate() {

		return thngTemplate;
	}

	public void setThngTemplate(final ThngTemplate thngTemplate) {

		this.thngTemplate = thngTemplate;
	}

	public void setGenerateThngs(final Boolean generateThngs) {

		this.generateThngs = generateThngs;
	}

	@Override
	public Boolean getGenerateThngs() {

		return generateThngs;
	}

	public void setGenerateRedirections(final Boolean generateRedirections) {

		this.generateRedirections = generateRedirections;
	}

	@Override
	public Boolean getGenerateRedirections() {

		return generateRedirections;
	}
}
