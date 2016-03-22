/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.rule.reaction;

/**
 * Reaction that will cause a URL redirection.
 **/
public class RedirectionReaction extends Reaction {

	private static final long serialVersionUID = 8799788066512698766L;
	public static final String TYPE = "redirection";

	private final String redirectUrl;

	public RedirectionReaction(final String redirectUrl) {

		setType(TYPE);
		this.redirectUrl = redirectUrl;
	}

	RedirectionReaction() {

		this(null);
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	@Override
	public void accept(final ReactionVisitor visitor) {
		visitor.visit(this);
	}
}
