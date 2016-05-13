/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

import java.util.Map;

/**
 * Interface for a resource model with properties.
 */
public interface ResourceWithProperties {

	/**
	 * @return The properties.
	 */
	Map<String, Object> getProperties();
	
	void setProperties(Map<String, Object> properties);
}
