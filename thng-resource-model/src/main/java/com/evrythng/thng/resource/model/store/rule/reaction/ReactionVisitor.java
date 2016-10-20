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

	void visit(RedirectionReaction reaction);
}
