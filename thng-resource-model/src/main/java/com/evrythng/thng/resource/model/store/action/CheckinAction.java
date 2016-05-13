/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.action;

/**
 * Model for a thng checkin
 **/
public class CheckinAction extends ThngAction {

	private static final long serialVersionUID = 6047871889291457932L;

	public static final String TYPE = ActionType.Value.CHECKINS.value();

	public CheckinAction() {
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
