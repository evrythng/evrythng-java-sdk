/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

import java.util.List;

public abstract class BaseAdiOutputParameters implements BatchPopulatingTask.OutputParameters {

	private Type type;
	private List<Column> columns;

	public BaseAdiOutputParameters() {

		this(null);
	}

	protected BaseAdiOutputParameters(final List<Column> columns) {

		this.columns = columns;
	}

	@Override
	public final Type getType() {

		return type;
	}

	@Override
	public final void setType(final Type type) {

		this.type = type;
	}

	@Override
	public List<Column> getColumns() {

		return columns;
	}

	public void setColumns(final List<Column> columns) {

		this.columns = columns;
	}
}
