/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.rule.reaction;

/**
 * Reaction when text has been added to the action response.
 **/
public class TierLevelChangedReaction extends Reaction {

	private static final long serialVersionUID = 4259099929012220973L;

	public static final String TYPE = "tierLevelChanged";

	private String tierLevel;
	private String scheme;

	public TierLevelChangedReaction() {
		setType(TYPE);
	}

	public String getTierLevel() {
		return tierLevel;
	}

	public void setTierLevel(String tierLevel) {
		this.tierLevel = tierLevel;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void accept(ReactionVisitor visitor) {
		visitor.visit(this);
	}

}
