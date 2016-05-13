/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;
import com.evrythng.thng.resource.model.core.Identifiable;
import com.google.common.base.MoreObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Model representation for <em>thngs</em>.
 */
public class Thng extends DurableResourceModel implements ResourceWithProperties, Identifiable {

	private static final long serialVersionUID = -5495600871904690325L;
	public static final String FIELD_DESCRIPTION = "description";
	public static final String FIELD_PRODUCT = "product";
	public static final String FIELD_COLLECTIONS = "collections";
	public static final String FIELD_PROPERTIES = "properties";
	public static final String FIELD_IDENTIFIERS = "identifiers";
	private String name;
	public static final String FIELD_NAME = "name";
	private String description;
	private EmbeddedLocation location;
	/**
	 * Reference to {@link Product#id}.
	 */
	private String product;
	private Map<String, Object> properties;
	/**
	 * An array of global identifiers for this thng
	 */
	private Map<String, String> identifiers;

	/**
	 * Collection ids this thng is part of.
	 */
	private Set<String> collections;

	public static final String FIELD_BATCH = "batch";
	private String batch;

	public static final String FIELD_CREATED_BY_TASK = "createdByTask";
	private String createdByTask;

	public Thng() {

	}

	public Thng(final Thng thng) {
		
		this.name = thng.name;
		this.description = thng.description;
		this.location = thng.location;
		this.product = thng.product;
		this.properties = thng.properties != null ? new HashMap<>(thng.properties) : null;
		this.identifiers = thng.identifiers != null ? new HashMap<>(thng.identifiers) : null;
		this.collections = thng.collections != null ? new HashSet<>(thng.collections) : null;
		this.batch = thng.batch;
		this.createdByTask = thng.createdByTask;
		setUpdatedAt(thng.getUpdatedAt());
		setId(thng.getId());
		setCreatedAt(thng.getCreatedAt());
		setCustomFields(thng.getCustomFields() != null ? new HashMap<>(thng.getCustomFields()) : null);
		setTags(thng.getTags() != null ? new ArrayList<>(thng.getTags()) : null);
		setScopes(thng.getScopes());
	}

	public String getName() {

		return name;
	}

	public void setName(final String name) {

		this.name = name;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(final String description) {

		this.description = description;
	}

	public EmbeddedLocation getLocation() {

		return location;
	}

	public void setLocation(final EmbeddedLocation location) {

		this.location = location;
	}

	public String getProduct() {

		return product;
	}

	public void setProduct(final String product) {

		this.product = product;
	}

	@Override
	public Map<String, Object> getProperties() {

		return properties != null ? Collections.unmodifiableMap(properties) : null;
	}

	@Override
	public void setProperties(final Map<String, Object> properties) {

		this.properties = properties != null ? new HashMap<>(properties) : null;
	}

	@Override
	public void addIdentifier(final String type, final String value) {

		if (identifiers == null) {
			identifiers = new HashMap<>();
		}
		identifiers.put(type, value);
	}

	@Override
	public Map<String, String> getIdentifiers() {

		return identifiers;
	}

	@Override
	public void setIdentifiers(final Map<String, String> identifiers) {

		this.identifiers = identifiers;
	}

	@Override
	public String firstIdentifier() {

		return identifiers.values().iterator().next();
	}

	public Set<String> getCollections() {

		return collections;
	}

	public void setCollections(final Set<String> collections) {

		this.collections = collections;
	}

	public String getBatch() {

		return batch;
	}

	public void setBatch(final String batch) {

		this.batch = batch;
	}

	public String getCreatedByTask() {

		return createdByTask;
	}

	public void setCreatedByTask(final String createdByTask) {

		this.createdByTask = createdByTask;
	}

	@Override
	public String toString() {

		MoreObjects.ToStringHelper toString = MoreObjects.toStringHelper(this).omitNullValues();

		toString.add("id", getId());
		toString.add("name", getName());
		toString.add("description", getDescription());
		toString.add("createdAt", getCreatedAt());
		toString.add("updatedAt", getUpdatedAt());
		toString.add("product", getProduct());
		toString.add("batch", getBatch());
		toString.add("createdByTask", getCreatedByTask());
		toString.add("collections", getCollections());
		toString.add("identifiers", getIdentifiers());
		toString.add("tags", getTags());
		toString.add("customFields", getCustomFields());
		toString.add("properties", getProperties());
		toString.add("location", getLocation());
		toString.add("scopes", getScopes());

		return toString.toString();
	}
}