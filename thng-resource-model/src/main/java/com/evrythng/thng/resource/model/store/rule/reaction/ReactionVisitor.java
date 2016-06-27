/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.rule.reaction;

/**
 * Defines the visit methods for the visitor pattern in the reactions.
 **/
public interface ReactionVisitor {

	void visit(MultimediaContentGrantedReaction reaction);

	void visit(TextAddedReaction reaction);

	void visit(PostedToSocialNetworkReaction reaction);

	void visit(ActionTagsSetReaction reaction);

	void visit(DataAddedReaction reaction);

	void visit(RedirectionReaction reaction);
}
