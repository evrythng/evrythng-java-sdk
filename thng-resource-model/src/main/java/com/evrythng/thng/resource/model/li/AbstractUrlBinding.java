/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.li;

import com.evrythng.thng.resource.model.core.DurableResourceModel;
import com.evrythng.thng.resource.model.core.EvrythngType;

import java.util.Map;

/**
 * URL binding model.
 */
public abstract class AbstractUrlBinding extends DurableResourceModel {

	private static final long serialVersionUID = 8831270162102743074L;

	public static final String EVRYTHNGID_TAG = "{evrythngId}";
	public static final String SHORTID_TAG = "{shortId}";
	private String shortDomain;
	private String defaultRedirectUrl;
	private String redirectUrl;
	private Map<String, Object> redirectionContext;
	private EvrythngType type;
	private String evrythngUrl;

	protected AbstractUrlBinding() {
	}

	protected AbstractUrlBinding(final String shortDomain) {
		this.shortDomain = shortDomain;
	}

	protected AbstractUrlBinding(final AbstractUrlBinding that) {

		this(that.shortDomain);
		this.setDefaultRedirectUrl(that.getDefaultRedirectUrl());
		this.setEvrythngUrl(that.getEvrythngUrl());
		this.setType(that.getType());
		this.setId(that.getId());
		this.setCreatedAt(that.getCreatedAt());
		this.setUpdatedAt(that.getUpdatedAt());
		this.setCustomFields(that.getCustomFields());
		this.setTags(that.getTags());
	}

	public String getDefaultRedirectUrl() {
		return defaultRedirectUrl;
	}

	public void setDefaultRedirectUrl(final String defaultRedirectUrl) {
		this.defaultRedirectUrl = defaultRedirectUrl;
	}

	public void setRedirectUrl(final String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getRedirectUrl() {

		return (redirectUrl != null) ? redirectUrl : defaultRedirectUrl;
	}

	public String getShortDomain() {
		return shortDomain;
	}

	public void setShortDomain(final String shortDomain) {
		this.shortDomain = shortDomain;
	}

	/**
	 * @return the type
	 */
	public EvrythngType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(final EvrythngType type) {
		this.type = type;
	}

	/**
	 * @return the evrythngUrl
	 */
	public String getEvrythngUrl() {
		return evrythngUrl;
	}

	/**
	 * @param evrythngUrl
	 *            the evrythngUrl to set
	 */
	public void setEvrythngUrl(final String evrythngUrl) {
		this.evrythngUrl = evrythngUrl;
	}

	public Map<String, Object> getRedirectionContext() {

		return redirectionContext;
	}

	public void setRedirectionContext(final Map<String, Object> redirectionContext) {

		this.redirectionContext = redirectionContext;
	}
}
