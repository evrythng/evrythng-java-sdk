/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.rule.reaction;

/**
 * Reaction when text has been added to the action response.
 **/
public class ActionTagsSetReaction extends Reaction {

	private static final long serialVersionUID = 6399409348278706772L;

	public static final String TYPE = "actionTagsSet";

	public ActionTagsSetReaction() {
		setType(TYPE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void accept(ReactionVisitor visitor) {
		visitor.visit(this);
	}
}
