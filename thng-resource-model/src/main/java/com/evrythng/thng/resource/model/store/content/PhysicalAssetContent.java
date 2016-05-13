/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.content;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

/**
 * Model for a physical asset.
 */
public class PhysicalAssetContent extends Content {

	private static final long serialVersionUID = -1264841415344769295L;

	private Map<String, Media> media;
	private Integer stock;

	private String product;

	private Integer stockChange;

	private Integer cost;

	private Integer granted;

	public PhysicalAssetContent() {
	}

	public Map<String, Media> getMedia() {
		return media;
	}

	public void setMedia(Map<String, Media> media) {
		this.media = media;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Integer getStockChange() {
		return stockChange;
	}

	public void setStockChange(Integer stockChange) {
		this.stockChange = stockChange;
	}

	public Integer getCost() {
		return cost;
	}

	@JsonIgnore
	public int getCostOrZero() {
		return cost != null ? cost : 0;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Integer getGranted() {
		return granted;
	}

	public void setGranted(Integer granted) {
		this.granted = granted;
	}
}
