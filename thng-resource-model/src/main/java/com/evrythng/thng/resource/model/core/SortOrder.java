/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.core;

import com.google.common.base.MoreObjects;

public final class SortOrder {

	public enum Direction {
		ASCENDING, DESCENDING
	}

	private static final SortOrder ASCENDING = new SortOrder(Direction.ASCENDING);
	private static final SortOrder DESCENDING = new SortOrder(Direction.DESCENDING);

	private final Direction direction;

	public static SortOrder ascending() {

		return ASCENDING;
	}

	public static SortOrder descending() {

		return DESCENDING;
	}

	private SortOrder(final Direction direction) {

		this.direction = direction;
	}

	public final Direction direction() {

		return direction;
	}

	@Override
	public final boolean equals(final Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SortOrder sortOrder = (SortOrder) o;
		if (direction != sortOrder.direction) {
			return false;
		}
		return true;
	}

	@Override
	public final int hashCode() {

		return direction.hashCode();
	}

	@Override
	public final String toString() {

		MoreObjects.ToStringHelper self = MoreObjects.toStringHelper(this);
		self.add("direction", direction);
		return self.toString();
	}
}