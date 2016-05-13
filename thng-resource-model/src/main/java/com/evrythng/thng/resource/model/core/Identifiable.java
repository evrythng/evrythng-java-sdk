/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.core;

import java.util.Map;

public interface Identifiable {

	Map<String, String> getIdentifiers();

	String firstIdentifier();

	void setIdentifiers(Map<String, String> identifiers);

	void addIdentifier(String type, String value);
}
