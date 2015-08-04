/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.li;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * URL binding model.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
public class UrlBinding extends AbstractUrlBinding {

	private static final long serialVersionUID = -9060081991227729859L;
	private String shortId;
	private String evrythngId;
	private Integer hits;
	public static final String FIELD_BATCH = "batch";
	private String batch;

	// Input used for scanthng
	/**
	 * Used in scanthng services. Contains the image encoded in Base64
	 */
	private String image;
	private String format;
	private String data;

	public UrlBinding() {

	}

	public UrlBinding(final String shortId) {

		this.shortId = shortId;
	}

	public UrlBinding(final String shortId, final String shortDomain) {

		super(shortDomain);
		this.shortId = shortId;
	}

	public UrlBinding(final UrlBinding that) {

		super(that);
		this.setEvrythngId(that.getEvrythngId());
		this.setShortId(that.getShortId());
		this.setHits(that.getHits());
	}

	public String getShortId() {

		return shortId;
	}

	public void setShortId(final String shortId) {

		this.shortId = shortId;
	}

	public String getEvrythngId() {

		return evrythngId;
	}

	public void setEvrythngId(final String evrythngId) {

		this.evrythngId = evrythngId;
	}

	public Integer getHits() {

		return hits;
	}

	public void setHits(final Integer hits) {

		this.hits = hits;
	}

	public String getImage() {

		return image;
	}

	public void setImage(final String image) {

		this.image = image;
	}

	public String getFormat() {

		return format;
	}

	public void setFormat(final String format) {

		this.format = format;
	}

	public String getData() {

		return data;
	}

	public void setData(final String data) {

		this.data = data;
	}

	public String getBatch() {

		return batch;
	}

	public void setBatch(final String batch) {

		this.batch = batch;
	}

	/**
	 * @return the short URI (e.g., http://tn.gg/Gar554U78)
	 */
	public String toShortUri() {

		if (shortId == null || getShortDomain() == null) {
			return null;
		}

		try {
			return new URI("http://" + getShortDomain() + "/" + shortId).toString();
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("Could not build an URI out of " + getShortDomain() + " and " + shortId);
		}
	}

	/**
	 * <p>
	 * Formats and returns the current {@link UrlBinding#shortId}. Formatter
	 * splits the value every 4th character and joins the resulting parts with
	 * dashes (-).
	 * </p>
	 * <p>
	 * <strong>Examples:</strong>
	 * <ul>
	 * <li>{@code DxprepcR} becomes {@code Dxpr-epcR}</li>
	 * <li>{@code hZ74nMh1Pl35} becomes {@code hZ74-nMh1-Pl35}</li>
	 * <li>{@code fVg0TCOlws} becomes {@code fVg0-TCOl-ws}</li>
	 * </ul>
	 */
	public String toFormattedShortId() {

		if (shortId == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder(shortId.length() * 5 / 4 + 1);
		for (int i = 0; i < shortId.length(); ++i) {
			if ((i & 0x03) == 0 && i != 0) { // (i & 0x03) is the same as (i % 4)
				sb.append('-');
			}
			sb.append(shortId.charAt(i));
		}
		return sb.toString();
	}
}
