/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.util;

/**
 * This class contains utility methods for logging
 */
public class LogUtils {

	/**
	 * Masks api key. Example output {@code 'JbXptr...9Ixpze'}
	 *
	 * @param key api key
	 * @return masked api key
	 */
	public static String maskApiKey(final String key) {

		int visibleChars = (int) (0.1 * key.length());
		int right = visibleChars / 2;
		int left = visibleChars - right; // left >= right
		String beginning = key.substring(0, left);
		String end = key.substring(key.length() - right);
		return beginning + "..." + end;
	}
}
