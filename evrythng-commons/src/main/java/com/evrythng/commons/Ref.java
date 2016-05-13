/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.commons;

/**
 * Class that holds a value. Useful for passing arguments by reference.
 * 
 * @author Michel Yerly (my)
 **/
public final class Ref<T> {

	private T obj = null;

	/**
	 * Creates an instance that holds the null value.
	 */
	public Ref() {
	}

	/**
	 * Creates an instance that holds the specified value.
	 */
	public Ref(final T obj) {
		this.obj = obj;
	}

	/**
	 * Gets the value held.
	 */
	public final T get() {
		return obj;
	}

	/**
	 * Sets the value to hold.
	 */
	public final void set(final T obj) {
		this.obj = obj;
	}

	/**
	 * Null-safe setter.
	 */
	public static <T> void set(Ref<T> ref, T obj) {
		if (ref != null) {
			ref.set(obj);
		}
	}
}
