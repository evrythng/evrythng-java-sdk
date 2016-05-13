/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.rule.reaction;

/**
 * Adapter class for {@link ReactionVisitor}.
 **/
public class ReactionVisitorAdapter implements ReactionVisitor {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(MultimediaContentGrantedReaction reaction) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(LoyaltyPointsChangedReaction reaction) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(PhysicalAssetGrantedReaction reaction) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(TextAddedReaction reaction) {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(TierLevelChangedReaction reaction) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(PostedToSocialNetworkReaction reaction) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(ActionTagsSetReaction reaction) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(DataAddedReaction reaction) {
	}

	@Override
	public void visit(RedirectionReaction reaction) {

	}

}
