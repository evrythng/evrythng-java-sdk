/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

import java.util.List;

/**
 * Model representation for <em>redirector rules</em>.
 */
public class RedirectorRules extends DurableResourceModel {

	private static final long serialVersionUID = -5195473337467893558L;
	private List<RedirectorRule> rules;

	public List<RedirectorRule> getRules() {

		return rules;
	}

	public void setRules(final List<RedirectorRule> rules) {

		this.rules = rules;
	}
}
