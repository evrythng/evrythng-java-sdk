/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.action.bulk;

import java.util.List;

public class ListPayload extends Payload {

	private static final long serialVersionUID = 1022301895791232599L;
	private List<String> list;

	public ListPayload() {

		setType(Type.LIST);
	}

	public List<String> getList() {

		return list;
	}

	public void setList(final List<String> list) {

		this.list = list;
	}
}
