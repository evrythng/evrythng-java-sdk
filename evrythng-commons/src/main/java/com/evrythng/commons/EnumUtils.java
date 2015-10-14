/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.commons;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for the enums.
 */
public class EnumUtils {

	/**
	 * Creates an enum value from a string using a lookup map (usually created by {@link #createNames(Object[])}.
	 * <p>
	 * Typical implementation:
	 * <p>
	 * <pre>
	 * public static MyEnum fromString(String name) {
	 * 	return EnumUtils.fromString(names, name);
	 * }
	 * </pre>
	 *
	 * @return The enum value corresponding to the name. If the name is {@code null}, returns {@code null}.
	 *
	 * @throws IllegalArgumentException if the name is not {@code null} and does not match any enum value.
	 */
	public static <E> E fromString(final Map<String, E> names, final String name) {

		if (name == null) {
			return null;
		}
		E t = names.get(name);
		if (t == null) {
			throw new IllegalArgumentException("Invalid enum value.");
		}
		return t;
	}

	/**
	 * Creates a lookup map for the enum names, intended for use with the {@link #fromString(Map, String)} method. It uses the
	 * {@link #toString()} method to build the lookup map. This is intended to be used in a static initializer.
	 * <p>
	 * <pre>
	 * private static Map&lt;String, MyEnum&gt; names;
	 * static {
	 * 	names = EnumUtils.createNames(values());
	 * }
	 * </pre>
	 *
	 * @param values The enum values, as provided by {@code values()}.
	 */
	public static <E> Map<String, E> createNames(final E[] values) {

		Map<String, E> names = new HashMap<String, E>();
		for (E t : values) {
			names.put(t.toString(), t);
		}
		return names;
	}

	/**
	 * Returns an {@link EnumSet} representing the values of the array.
	 * @param values The values. This can be {@code null} or empty.
	 * @param <E> The enum type.
	 * @return The {@link EnumSet}.
	 */
	public static <E extends Enum<E>> EnumSet<E> setOf(final E[] values) {

		if (values != null && values.length > 0) {
			return EnumSet.of(values[0], values);
		} else {
			return EnumSet.noneOf((Class<E>) values.getClass().getComponentType());
		}
	}
}
