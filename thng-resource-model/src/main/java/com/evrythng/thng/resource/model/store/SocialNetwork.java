/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum SocialNetwork {
	FACEBOOK("facebook", "fb"), EVRYONE("evryone", "evo");
	private static final Map<String, SocialNetwork> serNames = new HashMap<>();
	private static final Map<String, SocialNetwork> prefixes = new HashMap<>();
	private final String serName;
	private final String prefix;

	SocialNetwork(final String serName, final String prefix) {

		this.serName = serName;
		this.prefix = prefix;
	}

	static {
		for (SocialNetwork t : values()) {
			serNames.put(t.serName, t);
			prefixes.put(t.prefix, t);
		}
	}

	@JsonValue
	public String toString() {

		return serName;
	}

	@JsonCreator
	public static SocialNetwork fromString(final String serName) {

		if (serName == null) {
			return null;
		}
		SocialNetwork t = serNames.get(serName);
		if (t == null) {
			throw new IllegalArgumentException("Invalid enum value.");
		}
		return t;
	}

	public String getPrefix() {

		return prefix;
	}

	public static SocialNetwork fromPrefix(final String prefix) {

		SocialNetwork t = prefixes.get(prefix);
		if (t == null) {
			throw new IllegalArgumentException("Invalid prefix.");
		}
		return t;
	}
}
