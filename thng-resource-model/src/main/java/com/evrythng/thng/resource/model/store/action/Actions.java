/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* Rest model for actions.
*/
public class Actions extends ArrayList<Action> {

	private static final long serialVersionUID = -6314117222194081181L;

	public Actions(final List<? extends Action> actions) {

		addAll(actions);
	}

	public Actions(final Action... actions) {

		addAll(Arrays.asList(actions));
	}

	public Actions() {

	}
}
