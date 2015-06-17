/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.mapping;

import com.evrythng.thng.resource.model.store.rule.reaction.Reaction;

/**
 * Reaction deserializer.
 **/
public class ReactionDeserializerImpl extends TypeMapDeserializer<Reaction> implements ReactionDeserializer {

	private static final long serialVersionUID = 1L;

	public ReactionDeserializerImpl() {
		super(Reaction.class, Reaction.FIELD_TYPE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends Reaction> String getReactionType(Class<T> reactionClass) {
		return getObjectType(reactionClass);
	}

}