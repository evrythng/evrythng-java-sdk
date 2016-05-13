/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;
import com.evrythng.thng.resource.model.core.Identifiable;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Model representation for <em>collections</em>.
 */
public class Collection extends DurableResourceModel implements Identifiable {

	private static final long serialVersionUID = -2064399431964890923L;
	private String name;
	private String description;
	private Map<String, String> identifiers;

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
		if (identifiers != null) {
			sb.append(", identifiers=").append(identifiers);
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

	@Override
	public Map<String, String> getIdentifiers() {
		return this.identifiers;
	}

	@Override
	public String firstIdentifier() {
		if (this.identifiers != null) {
			return this.identifiers.values().iterator().next();
		}
		return null;
	}

	@Override
	public void setIdentifiers(final Map<String, String> identifiers) {
		this.identifiers = identifiers;
	}

	@Override
	public void addIdentifier(final String type, final String value) {
		if (this.identifiers == null) {
			this.identifiers = new HashMap<>();
		}
		this.identifiers.put(type, value);
	}
}
