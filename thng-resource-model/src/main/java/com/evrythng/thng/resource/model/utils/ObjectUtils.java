/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.utils;

import java.util.Comparator;

/**
 * Provides utility method for objects.
 */
public class ObjectUtils {

	public static boolean nullSafeEquals(final Object a, final Object b) {

		if (a == null && b == null) {
			return true;
		}
		return !(a == null || b == null) && a.equals(b);
	}

	/**
	 * NOTE: {@code null} is treated as the smallest value.
	 */
	public static <T> int nullSafeCompare(final T a, final T b, final Comparator<? super T> comp) {

		if (a == null && b == null) {
			return 0;
		}
		if (a == null) {
			return -1;
		}
		if (b == null) {
			return 1;
		}
		return normalizeCompareResult(comp.compare(a, b));
	}

	/**
	 * NOTE: {@code null} is treated as the smallest value.
	 */
	public static <T extends Comparable<T>> int nullSafeCompare(final T a, final T b) {

		if (a == null && b == null) {
			return 0;
		}
		if (a == null) {
			return -1;
		}
		if (b == null) {
			return 1;
		}
		return normalizeCompareResult(a.compareTo(b));
	}

	private static int normalizeCompareResult(final int x) {

		return x > 0 ? 1 : x < 0 ? -1 : 0;
	}
}
