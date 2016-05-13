/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.rule.reaction;

import java.util.Map;

/**
 * Reaction that will cause a URL redirection.
 **/
public class RedirectionReaction extends Reaction {

	private static final long serialVersionUID = 8799788066512698766L;
	public static final String TYPE = "redirection";

	private final String redirectUrl;
	private Map<String, Object> redirectionContext;

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

	public Map<String, Object> getRedirectionContext() {

		return redirectionContext;
	}

	public void setRedirectionContext(final Map<String, Object> redirectionContext) {

		this.redirectionContext = redirectionContext;
	}

	@Override
	public void accept(final ReactionVisitor visitor) {
		visitor.visit(this);
	}
}
