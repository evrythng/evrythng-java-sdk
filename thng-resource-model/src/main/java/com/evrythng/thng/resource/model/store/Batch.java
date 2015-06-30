/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

import java.util.Map;

/**
 * Model representation for <em>batches</em>.
 */
public class Batch extends DurableResourceModel {

	private static final long serialVersionUID = -4440334109484551488L;

	public enum Status {
		EMPTY, IN_PROGRESS, COMPLETE, SEALED;
	}

	private String name;
	private String description;
	private Map<String, String> identifiers;
	private Status status;

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

	public Map<String, String> getIdentifiers() {

		return identifiers;
	}

	public void setIdentifiers(final Map<String, String> identifiers) {

		this.identifiers = identifiers;
	}

	public Status getStatus() {

		return status;
	}

	public void setStatus(final Status status) {

		this.status = status;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder("Batch{");
		sb.append("name='").append(name).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", identifiers='").append(identifiers).append('\'');
		sb.append(", status=").append(status);
		sb.append('}');
		return sb.toString();
	}
}
