/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.core;

import java.util.Map;

public interface Identifiable {

	Map<String, String> getIdentifiers();

	String firstIdentifier();

	void setIdentifiers(Map<String, String> identifiers);

	void addIdentifier(String type, String value);
}
