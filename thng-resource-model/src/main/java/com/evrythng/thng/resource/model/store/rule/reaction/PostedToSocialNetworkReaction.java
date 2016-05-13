/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.rule.reaction;

/**
 * Reaction when something was posted to the social network.
 * 
 **/
public class PostedToSocialNetworkReaction extends Reaction {

	private static final long serialVersionUID = -9083388913051716319L;

	public static final String TYPE = "postedToSocialNetwork";

	public PostedToSocialNetworkReaction() {
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
