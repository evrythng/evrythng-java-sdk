/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.mapping;

import com.evrythng.thng.resource.model.store.rule.reaction.Reaction;

/**
 * Reaction deserializer interface.
 * 
 **/
public interface ReactionDeserializer {

	/**
	 * Gets the reaction type that correspond to the class.
	 * 
	 * @return The reaction type, or null if the class is not recognized.
	 */
	<T extends Reaction> String getReactionType(Class<T> reactionClass);
}
