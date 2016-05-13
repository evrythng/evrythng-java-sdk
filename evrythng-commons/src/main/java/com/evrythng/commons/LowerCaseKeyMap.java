/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.commons;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A map that guarantees its keys to be lower cased.
 **/
public class LowerCaseKeyMap<V> implements Map<String, V>, Serializable {

	private static final long serialVersionUID = -2689177871866000241L;

	private Map<String, V> map;

	/**
	 * Creates a new instance of this class, backed by a {@link HashMap}.
	 */
	public LowerCaseKeyMap() {
		map = new HashMap<String, V>();
	}

	/**
	 * Creates a new instance of this class, backed by the given {@link Map}.
	 * The given map must be empty and not accessed by any other means than this
	 * class, after invoking this constructor, otherwise the behavior is
	 * undefined.
	 */
	public LowerCaseKeyMap(Map<String, V> wrapped) {
		if (!wrapped.isEmpty()) {
			throw new IllegalStateException("The wrapped map must be empty.");
		}
		map = wrapped;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		map.clear();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsKey(Object mixedCaseKey) {
		if (mixedCaseKey instanceof String) {
			return map.containsKey(((String) mixedCaseKey).toLowerCase());
		}
		return map.containsKey(mixedCaseKey);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsValue(Object arg0) {
		return map.containsValue(arg0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<java.util.Map.Entry<String, V>> entrySet() {
		return map.entrySet();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public V get(Object mixedCaseKey) {
		if (mixedCaseKey instanceof String) {
			return map.get(((String) mixedCaseKey).toLowerCase());
		} else {
			return map.get(mixedCaseKey);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<String> keySet() {
		return map.keySet();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public V put(String mixedCaseKey, V value) {
		return map.put(mixedCaseKey.toLowerCase(), value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void putAll(Map<? extends String, ? extends V> m) {
		if (m instanceof LowerCaseKeyMap) {
			map.putAll(m);
		} else {
			for (Entry<? extends String, ? extends V> e : m.entrySet()) {
				map.put(e.getKey().toLowerCase(), e.getValue());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public V remove(Object mixedCaseKey) {
		if (mixedCaseKey instanceof String) {
			return map.remove(((String) mixedCaseKey).toLowerCase());
		}
		return map.remove(mixedCaseKey);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return map.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<V> values() {
		return map.values();
	}

}
