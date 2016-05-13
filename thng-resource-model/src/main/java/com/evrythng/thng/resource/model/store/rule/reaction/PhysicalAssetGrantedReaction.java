/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.rule.reaction;

import com.evrythng.thng.resource.model.store.LoyaltyPoints;
import com.evrythng.thng.resource.model.store.content.PhysicalAssetContent;

/**
 * Reaction when loyalty points where granted.
 **/
public class PhysicalAssetGrantedReaction extends Reaction {

	private static final long serialVersionUID = -2286457532445221541L;

	public static final String TYPE = "physicalAssetGranted";

	private PhysicalAssetContent physicalAsset;
	private LoyaltyPoints pointChanges;
	private LoyaltyPoints points;
	private Integer amount;

	public PhysicalAssetGrantedReaction() {
		setType(TYPE);
	}

	public PhysicalAssetContent getPhysicalAsset() {
		return physicalAsset;
	}

	public void setPhysicalAsset(PhysicalAssetContent physicalAsset) {
		this.physicalAsset = physicalAsset;
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void accept(ReactionVisitor visitor) {
		visitor.visit(this);
	}
}
