/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.commons;

/**
 * Thread-safe version of {@link Ref}.
 * 
 * @author Michel Yerly (my)
 **/
public final class RefTS<T> {

	private volatile T obj = null;

	/**
	 * Creates an instance that holds the null value.
	 */
	public RefTS() {
	}

	/**
	 * Creates an instance that holds the specified value.
	 */
	public RefTS(final T obj) {
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
	public final synchronized void set(final T obj) {
		this.obj = obj;
	}

	/**
	 * Null-safe setter.
	 */
	public static <T> void set(RefTS<T> ref, T obj) {
		if (ref != null) {
			ref.set(obj);
		}
	}
}
