/*
 * (c) Copyright Reserved EVRYTHNG Limited 2018. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.action;

public class DecommissionAction extends ThngAction {

	private static final long serialVersionUID = -4194139597852338570L;

	public static final String TYPE = ActionType.Value.DECOMMISSIONS.value();

	public DecommissionAction() {
		setType(TYPE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void accept(ActionVisitor visitor) {
		visitor.visit(this);
	}
}
