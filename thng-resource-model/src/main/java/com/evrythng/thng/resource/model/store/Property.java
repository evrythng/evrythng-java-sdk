/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.TemporalResourceModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Model representation for <em>properties</em>.
 */
public abstract class Property<VALUE_TYPE> extends TemporalResourceModel {

	public enum Type {

		BOOLEAN, STRING, NUMBER, ARRAY, OBJECT;

		public static Type forPropertyValue(final Object value) {

			if (value == null) {
				throw new IllegalArgumentException("Cannot determine the type of null property value");
			}
			if (value instanceof Boolean) {
				return BOOLEAN;
			}
			if (value instanceof Number) {
				return NUMBER;
			}
			if (value instanceof String) {
				return STRING;
			}
			if (value instanceof List<?>) {
				return ARRAY;
			}
			if (value instanceof Map<?, ?>) {
				return OBJECT;
			}
			throw new IllegalArgumentException("Unsupported property type: " + value.getClass().getSimpleName());
		}
	}

	public static List<Property<?>> normalize(final List<Property<?>> denormalized){

		List<Property<?>> normalized = new ArrayList<>();
		Map<String, Integer> indexes = new HashMap<>();
		int index = 0;
		for (Property<?> property : denormalized) {
			String key = property.getKey() + "-" + (property.getTimestamp() != null ? property.getTimestamp() : "");
			if (indexes.get(key) == null) {
				indexes.put(key, index);
				normalized.add(index, property);
				index++;
			} else {
				normalized.set(indexes.get(key), property);
			}
		}
		return normalized;
	}

	private static final long serialVersionUID = 4241830003414536087L;
	private String key;
	private VALUE_TYPE value;
	public static final String FIELD_VALUE = "value";

	/**
	 * Creates a new empty instance of {@link Property}.
	 */
	Property() {
		/* Required by jackson. */
	}

	/**
	 * Creates a new instance of {@link Property}.
	 */
	Property(final String key, final VALUE_TYPE value) {

		this.key = key;
		this.value = value;
	}

	/**
	 * Creates a new instance of {@link Property}.
	 */
	Property(final String key, final VALUE_TYPE value, final Long timestamp) {

		super(timestamp);
		this.key = key;
		this.value = value;
	}

	public String getKey() {

		return key;
	}

	public void setKey(final String key) {

		this.key = key;
	}

	public VALUE_TYPE getValue() {

		return value;
	}

	public void setValue(final VALUE_TYPE value) {

		this.value = value;
	}

	@Override
	public boolean equals(final Object other) {

		if (other == null || other.getClass() != getClass()) {
			return false;
		}
		Property<VALUE_TYPE> that = (Property<VALUE_TYPE>) other;
		EqualsBuilder equals = new EqualsBuilder();
		equals.append(key, that.key);
		equals.append(value, that.value);
		equals.append(getTimestamp(), that.getTimestamp());
		return equals.build();
	}

	@Override
	public int hashCode() {

		HashCodeBuilder hashCode = new HashCodeBuilder();
		hashCode.append(key);
		hashCode.append(value);
		hashCode.append(getTimestamp());
		return hashCode.build();
	}

	@Override
	public String toString() {

		ToStringBuilder toString = new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE);
		toString.append("key", key);
		toString.append("value", value);
		toString.append("timestamp", getTimestamp());
		return toString.build();
	}
}
