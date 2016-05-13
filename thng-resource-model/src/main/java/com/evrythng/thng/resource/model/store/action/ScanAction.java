/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.action;

/**
 * Model for a thng scan
 **/
public class ScanAction extends ThngAction {

	private static final long serialVersionUID = -3694545415219237620L;

	public static final String TYPE = ActionType.Value.SCANS.value();

	public ScanAction() {
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
