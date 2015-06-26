/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Model representation for <em>collections</em>.
 */
public class Collection extends DurableResourceModel {

	private static final long serialVersionUID = -2064399431964890923L;
	private String name;
	private String description;
	/**
	 * Collection of {@link Thng#id} references.
	 */
	private LinkedHashSet<String> thngs;
	/**
	 * Collection ids this collection is part of.
	 */
	private Set<String> collections;

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

	public LinkedHashSet<String> getThngs() {

		return thngs;
	}

	public void setThngs(final LinkedHashSet<String> thngs) {

		this.thngs = thngs;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder("Collection{");
		if (name != null) {
			sb.append("name='").append(name).append('\'');
		}
		if (description != null) {
			sb.append(", description='").append(description).append('\'');
		}
		if (thngs != null) {
			sb.append(", thngs=").append(thngs);
		}
		if (collections != null) {
			sb.append(", collections=").append(collections);
				}
		sb.append('}');
		return sb.toString();
	}

	public void setCollections(final Set<String> collections) {

		this.collections = collections;
	}

	public Set<String> getCollections() {

		return collections;
	}
}
