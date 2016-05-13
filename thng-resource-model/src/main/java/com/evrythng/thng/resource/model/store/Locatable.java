/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

import java.util.Map;

/**
 * Interface for a location model with a position, place and custom fields
 */
public interface Locatable extends Positionable {

	String getPlace();

	void setPlace(String place);

	Map<String, Object> getCustomFields();

	void setCustomFields(Map<String, Object> customFields);
}
