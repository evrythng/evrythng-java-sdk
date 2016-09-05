/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.core;

import com.evrythng.commons.annotations.csv.CsvTransient;
import com.evrythng.commons.domain.WithId;
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

	public static final String FIELD_ID = "id";
	public static final String FIELD_CREATED_AT = "createdAt";
	public static final String FIELD_CUSTOM_FIELDS = "customFields";
	public static final String FIELD_TAGS = "tags";
	public static final String FIELD_SCOPES = "scopes";
	private static final long serialVersionUID = -2842591513551534611L;
	private String id;
	private Long createdAt;
	private Map<String, Object> customFields;
	private List<String> tags;
	private ScopeResource scopes;

	/**
	 * Retrieves resource id.
	 *
	 * @return instance of {@link String}.
	 */
	@Override
	public String getId() {

		return id;
	}

	/**
	 * Sets resource id.
	 *
	 * @param id id to set.
	 */
	@Override
	public void setId(final String id) {

		this.id = id;
	}

	/**
	 * Retrieves unix time (milliseconds since Epoch) when resource was created.
	 *
	 * @return instance of {@link Long}.
	 */
	public Long getCreatedAt() {

		return createdAt;
	}

	/**
	 * Read-only unix time (milliseconds since Epoch) when resource was created. The value of this property is managed by
	 * platform so you should not to specify it.
	 */
	public void setCreatedAt(final Long createdAt) {

		this.createdAt = createdAt;
	}

	/**
	 * Retrieves resource optional custom fields.
	 *
	 * @return instance of {@link Map}.
	 */
	@CsvTransient
	public Map<String, Object> getCustomFields() {

		return customFields != null ? Collections.unmodifiableMap(customFields) : null;
	}

	/**
	 * Sets resource optional custom fields.
	 *
	 * @param customFields custom fields to set.
	 */
	public void setCustomFields(final Map<String, Object> customFields) {

		if (customFields == null) {
			this.customFields = null;
		} else {
			this.customFields = new HashMap<>();
			this.customFields.putAll(customFields);
		}
	}

	/**
	 * Retrieves resource custom field specified by key.
	 *
	 * @param key custom field key.
	 *
	 * @return custom field value.
	 */
	@JsonIgnore
	public <T> T getCustomField(final String key) {

		return customFields != null ? (T) customFields.get(key) : null;
	}

	/**
	 * Adds resource custom field key-value pair.
	 *
	 * @param key   custom field key to add.
	 * @param value custom field value to add.
	 */
	public void addCustomFields(final String key, final Object value) {

		if (customFields == null) {
			customFields = new HashMap<>();
		}
		customFields.put(key, value);
	}

	/**
	 * Retrieves resource optional tags.
	 *
	 * @return instance of {@link List}.
	 */
	public List<String> getTags() {

		return tags;
	}

	/**
	 * Sets resource optional tags.
	 *
	 * @param tags tags to set.
	 */
	public void setTags(final List<String> tags) {

		this.tags = tags != null ? SetUniqueList.decorate(new ArrayList(tags)) : null;
	}

	/**
	 * Retrieves {@link ScopeResource} defining who can have an access to current resource.
	 *
	 * @return instance of {@link ScopeResource}.
	 */
	@Override
	public ScopeResource getScopes() {

		return scopes;
	}

	/**
	 * Sets {@link ScopeResource} defining who can have an access to current resource.
	 *
	 * @param scopes scope to set.
	 */
	@Override
	public void setScopes(final ScopeResource scopes) {

		this.scopes = scopes;
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 *
	 * @param o the ResourceModel subclass object with which to compare.
	 *
	 * @return {@code true} if this object is the same as the argument or they both have not-null equal id's; {@code false}
	 * otherwise.
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
