/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.commons.domain;

public final class SortOrder extends Value<SortOrder.Direction> {

	public enum Direction {
		ASCENDING, DESCENDING
	}

	private static final SortOrder ASCENDING = new SortOrder(Direction.ASCENDING);
	private static final SortOrder DESCENDING = new SortOrder(Direction.DESCENDING);

	public static SortOrder ascending() {

		return ASCENDING;
	}

	public static SortOrder descending() {

		return DESCENDING;
	}

	public static SortOrder withDirection(final Direction direction) {

		return new SortOrder(direction);
	}

	private SortOrder(final Direction direction) {

		super(direction);
	}

	public final Direction direction() {

		return internalValue();
	}
}