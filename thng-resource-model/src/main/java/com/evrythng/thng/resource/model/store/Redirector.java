/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

/**
 * Represents a redirector.
 */
public class Redirector extends DurableResourceModel {

	private static final long serialVersionUID = 1118559810507335113L;
	public static final String PRODUCTID_TAG = "{productId}";
	public static final String THNGID_TAG = "{thngId}";
	public static final String SHORTID_TAG = "{shortId}";
	private String defaultRedirectUrl; //http://example.com/{thngId}
	private String evrythngUrl;
	private String shortDomain; //tn.gg
	private String shortId; // 10 digits
	private Integer hits;

	/**
	 * @return the defaultRedirectUrl
	 */
	public String getDefaultRedirectUrl() {

		return defaultRedirectUrl;
	}

	/**
	 * @param defaultRedirectUrl the defaultRedirectUrl to set
	 */
	public void setDefaultRedirectUrl(final String defaultRedirectUrl) {

		this.defaultRedirectUrl = defaultRedirectUrl;
	}

	/**
	 * @return the shortDomain
	 */
	public String getShortDomain() {

		return shortDomain;
	}

	/**
	 * @param shortDomain the shortDomain to set
	 */
	public void setShortDomain(final String shortDomain) {

		this.shortDomain = shortDomain;
	}

	/**
	 * @return the shortId
	 */
	public String getShortId() {

		return shortId;
	}

	/**
	 * @param shortId the shortId to set
	 */
	public void setShortId(final String shortId) {

		this.shortId = shortId;
	}

	/**
	 * @return the hits
	 */
	public Integer getHits() {

		return hits;
	}

	/**
	 * @param hits the hits to set
	 */
	public void setHits(final Integer hits) {

		this.hits = hits;
	}

	/**
	 * @return the evrythngUrl
	 */
	public String getEvrythngUrl() {

		return evrythngUrl;
	}

	/**
	 * @param evrythngUrl the evrythngUrl to set
	 */
	public void setEvrythngUrl(final String evrythngUrl) {

		this.evrythngUrl = evrythngUrl;
	}
}
