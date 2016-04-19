/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.core;

import com.evrythng.commons.annotations.csv.CsvTransient;
import com.evrythng.thng.resource.model.template.WithId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.collections.list.SetUniqueList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract model representation for resources.
 */
public abstract class ResourceModel implements Serializable, WithScopeResource, WithId.Mutable {

	private static final long serialVersionUID = -2842591513551534611L;
	public static final String FIELD_ID = "id";
	private String id;
	public static final String FIELD_CREATED_AT = "createdAt";
	private Long createdAt;
	public static final String FIELD_CUSTOM_FIELDS = "customFields";
	private Map<String, Object> customFields;
	public static final String FIELD_TAGS = "tags";
	private List<String> tags;
	public static final String FIELD_SCOPES = "scopes";
	private ScopeResource scopes;

	@Override
	public String getId() {

		return id;
	}

	@Override
	public void setId(final String id) {

		this.id = id;
	}

	public Long getCreatedAt() {

		return createdAt;
	}

	public void setCreatedAt(final Long createdAt) {

		this.createdAt = createdAt;
	}

	@CsvTransient
	public Map<String, Object> getCustomFields() {
		
		return customFields != null ? Collections.unmodifiableMap(customFields) : null;
	}
	
	@JsonIgnore
	public <T> T getCustomField(final String key){

		return customFields != null ? (T) customFields.get(key) : null;
	}

	public void setCustomFields(final Map<String, Object> customFields) {

		if (customFields == null){
			this.customFields = null;
		}
		else {
			this.customFields = new HashMap<>();
			this.customFields.putAll(customFields);
		}
	}

	public void addCustomFields(final String key, final Object value) {

		if (customFields == null) {
			customFields = new HashMap<>();
		}
		customFields.put(key, value);
	}

	public List<String> getTags() {

		return tags;
	}

	public void setTags(final List<String> tags) {

		this.tags = tags != null ? SetUniqueList.decorate(new ArrayList(tags)) : null;
	}

	@Override
	public ScopeResource getScopes() {

		return scopes;
	}

	@Override
	public void setScopes(final ScopeResource scopes) {

		this.scopes = scopes;
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 *
	 * @param o the ResourceModel subclass object with which to compare.
	 * @return {@code true} if this object is the same as the argument
	 * or they both have not-null equal id's; {@code false} otherwise.
	 */
	@Override
	public boolean equals(final Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ResourceModel that = (ResourceModel) o;

		return id != null && that.id != null && id.equals(that.id);
	}

	@Override
	public int hashCode() {

		return id != null ? id.hashCode() : 0;
	}
}
