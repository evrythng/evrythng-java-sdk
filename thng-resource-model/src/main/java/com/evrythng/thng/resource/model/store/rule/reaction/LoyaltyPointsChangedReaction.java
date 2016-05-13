/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.rule.reaction;

import com.evrythng.thng.resource.model.store.LoyaltyPoints;

/**
 * Reaction when loyalty points were granted.
 **/
public class LoyaltyPointsChangedReaction extends Reaction {

	private static final long serialVersionUID = -343569540115074755L;

	public static final String TYPE = "loyaltyPointsChanged";

	private LoyaltyPoints pointChanges;
	private LoyaltyPoints points;

	public LoyaltyPointsChangedReaction() {
		setType(TYPE);
	}

	public LoyaltyPoints getPointChanges() {
		return pointChanges;
	}

	public void setPointChanges(LoyaltyPoints pointChanges) {
		this.pointChanges = pointChanges;
	}

	public LoyaltyPoints getPoints() {
		return points;
	}

	public void setPoints(LoyaltyPoints points) {
		this.points = points;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void accept(ReactionVisitor visitor) {
		visitor.visit(this);
	}

}
