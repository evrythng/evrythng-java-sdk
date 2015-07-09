/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.commons;

/**
 * Class to hold zero-based pagination information.
 * 
 * This class is thread-safe.
 * This class is immutable.
 * 
 * @author Michel Yerly (my)
 **/
public final class Pagination {

	private final int page;
	private final int perPage;
	private final int offset; // denormalized

	public Pagination(int page, int perPage) {
		super();
		this.page = page;
		this.perPage = perPage;
		this.offset = page * perPage;
	}

	public final int getPage() {
		return page;
	}

	public final int getPageOneBased() {
		return page + 1;
	}

	public final int getPerPage() {
		return perPage;
	}

	public final int getOffset() {
		return offset;
	}

	@Override
	public String toString() {
		return String.format("Pagination [page=%s, perPage=%s, offset=%s]", page, perPage, offset);
	}
}
