/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.core;

/**
 * Defining behaviour for classes with {@code ScopeResource}.
 */
public interface WithScopeResource {

	ScopeResource getScopes();

	void setScopes(final ScopeResource scopes);
}
