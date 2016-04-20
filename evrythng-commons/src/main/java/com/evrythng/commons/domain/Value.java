/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.commons.domain;

import com.evrythng.commons.validation.Precondition;
import com.evrythng.commons.validation.preconditions.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import static com.evrythng.commons.validation.EnsureThat.ensureThat;

public abstract class Value<V> {

	private final V value;

	@SafeVarargs
	protected Value(final V value, final Precondition<V>... additionalPreconditions) {

		ensureThat(value, NotNull.<V>notNull(), additionalPreconditions);
		this.value = value;
	}

	protected final V internalValue() {

		return value;
	}

	@Override
	public final boolean equals(final Object other) {

		if (other == null || other.getClass() != getClass()) {
			return false;
		}
		Value<V> that = (Value<V>) other;
		EqualsBuilder equals = new EqualsBuilder();
		equals.appendSuper(super.equals(other));
		equals.append(value, that.value);
		return equals.build();
	}

	@Override
	public final int hashCode() {

		HashCodeBuilder hashCode = new HashCodeBuilder();
		hashCode.append(value);
		return hashCode.build();
	}

	@Override
	public final String toString() {

		ToStringBuilder toString = new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE);
		toString.append("value", value);
		return toString.build();
	}
}