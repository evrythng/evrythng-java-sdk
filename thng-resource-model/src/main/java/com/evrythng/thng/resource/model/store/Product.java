/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for a product.
 */
public class Product extends DurableResourceModel implements ResourceWithProperties {

	private static final long serialVersionUID = -6201425043153000867L;
	private String brand;
	private List<String> categories;
	private Map<String, Object> properties;
	private String description;
	/**
	 * Product name or title
	 * @deprecated use {@link #name} instead.
	 */
	@Deprecated
	private String fn;
	@Deprecated
	public static final String FIELD_FN = "fn";
	/**
	 * Product name or title
	 */
	private String name;
	/**
	 * URLs to the photos, not the photos themselves
	 */
	private List<String> photos;
	/**
	 * Authoritative URL for this product (where more info can be found)
	 */
	private String url;
	/**
	 * An array of global identifiers for this product
	 */
	private Map<String, String> identifiers;

	public Product() {

	}

	public Product(final String brand, final List<String> categories, final String description, final String fn, final List<String> photos, final String url, final Map<String, String> identifiers, final Map<String, Object> customFields) {

		this.brand = brand;
		this.categories = categories;
		this.description = description;
		this.fn = fn;
		this.photos = photos;
		this.url = url;
		this.identifiers = identifiers;
		this.setCustomFields(customFields);
	}

	public String getBrand() {

		return brand;
	}

	public void setBrand(final String brand) {

		this.brand = brand;
	}

	public List<String> getCategories() {

		return categories;
	}

	public void setCategories(final List<String> categories) {

		this.categories = categories;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(final String description) {

		this.description = description;
	}

	/**
	 * Get product name or title
	 * @deprecated use {@link #getName()} instead.
	 */
	@Deprecated
	public String getFn() {

		return fn;
	}

	/**
	 * Set product name or title
	 * @deprecated use {@link #setName(String)} instead.
	 */
	@Deprecated
	public void setFn(final String fn) {

		this.fn = fn;
	}

	/**
	 * Get product name or title
	 */
	public String getName() {

		return name;
	}

	/**
	 * Set product name or title
	 */
	public void setName(final String name) {

		this.name = name;
	}

	public List<String> getPhotos() {

		return photos;
	}

	public void setPhotos(final List<String> photos) {

		this.photos = photos;
	}

	public String getUrl() {

		return url;
	}

	public void setUrl(final String url) {

		this.url = url;
	}

	public Map<String, String> getIdentifiers() {

		return identifiers;
	}

	public void setIdentifiers(final Map<String, String> identifiers) {

		this.identifiers = identifiers;
	}

	public void addIdentifier(final String type, final String value) {

		if (identifiers == null) {
			identifiers = new HashMap<>();
		}
		identifiers.put(type, value);
	}

	public String firstIdentifier() {

		return identifiers.values().iterator().next();
	}

	@Override
	public Map<String, Object> getProperties() {

		return properties != null ? Collections.unmodifiableMap(properties) : null;
	}

	@Override
	public void setProperties(final Map<String, Object> properties) {

		this.properties = properties != null ? new HashMap<>(properties) : null;
	}
}
