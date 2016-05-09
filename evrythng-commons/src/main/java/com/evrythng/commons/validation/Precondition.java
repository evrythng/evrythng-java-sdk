/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.commons.validation;

public interface Precondition<TARGET> {

	String name();

	void testThrowing(TARGET target) throws ValidationException;

	boolean test(TARGET target);

	Precondition<TARGET> or(Precondition<TARGET> other);
}