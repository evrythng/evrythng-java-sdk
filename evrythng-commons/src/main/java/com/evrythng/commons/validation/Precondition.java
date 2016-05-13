/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.commons.validation;

public interface Precondition<TARGET> {

	String name();

	void testThrowing(TARGET target) throws ValidationException;

	boolean test(TARGET target);

	Precondition<TARGET> or(Precondition<TARGET> other);
}