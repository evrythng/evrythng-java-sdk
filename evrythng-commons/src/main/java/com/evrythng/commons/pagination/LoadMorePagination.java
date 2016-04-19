/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.commons.pagination;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import static com.evrythng.commons.validation.EnsureThat.ensureThat;
import static com.evrythng.commons.validation.preconditions.NotNull.notNull;

public final class LoadMorePagination implements IPagination<LastPageElementId> {

	private final LastPageElementId lastPageElementId;
	private final PageSize size;

	public static LoadMorePagination of(final LastPageElementId lastPageElementId, final PageSize size) {

		return new LoadMorePagination(lastPageElementId, size);
	}

	private LoadMorePagination(final LastPageElementId lastPageElementId, final PageSize size) {

		ensureThat(lastPageElementId, notNull());
		ensureThat(size, notNull());
		this.lastPageElementId = lastPageElementId;
		this.size = size;
	}

	@Override
	public final LastPageElementId token() {

		return lastPageElementId;
	}

	@Override
	public final PageSize size() {

		return size;
	}

	@Override
	public final boolean equals(final Object other) {

		if (other == null || other.getClass() != getClass()) {
			return false;
		}
		LoadMorePagination that = (LoadMorePagination) other;
		EqualsBuilder equals = new EqualsBuilder();
		equals.appendSuper(super.equals(other));
		equals.append(lastPageElementId, that.lastPageElementId);
		equals.append(size, that.size);
		return equals.build();
	}

	@Override
	public final int hashCode() {

		HashCodeBuilder hashCode = new HashCodeBuilder();
		hashCode.append(lastPageElementId);
		hashCode.append(size);
		return hashCode.build();
	}

	@Override
	public final String toString() {

		ToStringBuilder toString = new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE);
		toString.append("lastPageElementId", lastPageElementId);
		toString.append("size", size);
		return toString.build();
	}
}
