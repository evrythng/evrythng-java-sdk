/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.mapping;

import com.evrythng.thng.resource.model.store.rule.reaction.Reaction;

/**
 * Reaction deserializer interface.
 * 
 * @author Michel Yerly (my)
 **/
public interface ReactionDeserializer {

	/**
	 * Gets the reaction type that correspond to the class.
	 * 
	 * @return The reaction type, or null if the class is not recognized.
	 */
	<T extends Reaction> String getReactionType(Class<T> reactionClass);
}
