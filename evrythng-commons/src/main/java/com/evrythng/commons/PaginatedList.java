/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * List that represent only a portion of the full list. The items are not
 * copied, so any change to the list supplied will be reflected to this list. If
 * the supplied list is changed, the behavior of the instance of this class is
 * undefined.
 * 
 * @author Michel Yerly (my)
 **/
public class PaginatedList<E> {

	private List<E> items;
	private long totalCount;
	private long itemStart;
	private long itemsPerPage;

	public final <T> PaginatedList<T> withElements(final List<T> elements) {

		return new PaginatedList<>(elements, totalCount, itemStart, itemsPerPage);
	}

	/**
	 * Creates an empty list.
	 */
	public PaginatedList() {
		this((E) null);
	}

	/**
	 * Creates a list with the items specified. {@code items} represent the
	 * entire list.
	 */
	public PaginatedList(final List<E> items) {
		this(items, items.size(), 0, items.size());
	}

	/**
	 * Creates a list with the items specified.
	 */
	public PaginatedList(List<E> items, long totalCount, Pagination pagination) {
		this(items, totalCount, pagination.getOffset(), pagination.getPerPage());
	}

	public PaginatedList(List<E> items, Pagination pagination, Long totalCount) {
		this(items, totalCount != null ? totalCount : -1, pagination.getOffset(), pagination.getPerPage());
	}

	public PaginatedList(List<E> items, Ref<Long> totalCount, Pagination pagination) {

		this(items, totalCount != null ? totalCount.get() : -1, pagination.getOffset(), pagination.getPerPage());
	}

	/**
	 * Creates a list with the items specified.
	 */
	public PaginatedList(List<E> items, long totalCount, long itemStart, long itemsPerPage) {

		if ((items.size() != 0 || totalCount != 0) && itemsPerPage == 0) {
			throw new IllegalArgumentException("perPage cannot be 0 if the list contains elements.");
		}

		this.items = Collections.unmodifiableList(items);
		this.totalCount = totalCount;
		this.itemStart = itemStart;
		this.itemsPerPage = itemsPerPage;
	}

	/**
	 * Constructs a paginated list with zero or one item.
	 * 
	 * @param item
	 *            The item. If null, it is not added.
	 */
	public PaginatedList(E item) {
		itemStart = 0;
		this.items = new ArrayList<E>();
		if (item != null) {
			items.add(item);
			totalCount = 1;
		} else {
			totalCount = 0;
		}
		items = Collections.unmodifiableList(items);
	}

	/**
	 * Gets the items as an unmodifiable list.
	 * NOTE: the object is not the same as the one passed in the constructor (if
	 * any).
	 */
	public List<E> getItems() {
		return items;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public long getItemStart() {
		return itemStart;
	}

	public long getItemsPerPage() {
		return itemsPerPage;
	}

	public boolean isFirstPage() {
		return itemStart == 0;
	}

	public boolean isLastPage() {
		return itemStart + items.size() >= totalCount;
	}

	public long getPage() {

		if (itemsPerPage == 0) {
			return 0;
		}

		return itemStart / itemsPerPage;
	}

	public long getPages() {

		if (itemsPerPage == 0) {
			return 0;
		}

		return (totalCount + itemsPerPage - 1) / itemsPerPage;
	}
}