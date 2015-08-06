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
	public static final String FIELD_NAME = "name";
	public static final String FIELD_DESCRIPTION = "description";
	public static final String FIELD_STATUS = "status";
	public static final String FIELD_IDENTIFIERS = "identifiers";

	public enum Status {
		EMPTY, IN_PROGRESS, COMPLETE, SEALED
	}

	public static final class ResourceCount {
		
		public static final String FIELD_THNGS = "thngs";
		private Integer thngs;
		public static final String FIELD_URL_BINDINGS = "urlBindings";
		private Integer urlBindings;

		public Integer getThngs() {

			return thngs;
		}

		public void setThngs(final Integer thngs) {

			this.thngs = thngs;
		}

		public Integer getUrlBindings() {

			return urlBindings;
		}

		public void setUrlBindings(final Integer urlBindings) {

			this.urlBindings = urlBindings;
		}
	}

	private String name;
	private String description;
	private Map<String, String> identifiers;
	private Status status;
	// TODO _MS_ validate here on creation: read-only
	public static final String FIELD_RESOURCE_COUNTS_BY_PRODUCT = "resourceCountsByProduct";
	public static final String NO_PRODUCT_RESOURCES_KEY = "NO_PRODUCT";
	private Map<String, ResourceCount> resourceCountsByProduct;

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

	public Map<String, ResourceCount> getResourceCountsByProduct() {

		return resourceCountsByProduct;
	}
	
	public void setResourceCountsByProduct(final Map<String, ResourceCount> resourceCountsByProduct){
		
		this.resourceCountsByProduct = resourceCountsByProduct;
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
