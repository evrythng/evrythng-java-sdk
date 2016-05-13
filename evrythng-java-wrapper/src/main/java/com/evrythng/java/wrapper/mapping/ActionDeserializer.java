/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.mapping;

import com.evrythng.thng.resource.model.store.action.Action;
import com.evrythng.thng.resource.model.store.action.CustomAction;

/**
 * Action deserializer interface.
 *
 **/
public interface ActionDeserializer {

	/**
	 * Gets the action type that correspond to the class.
	 * 
	 * @return The action type, or null if the class is not recognized or is
	 *         {@link CustomAction}.
	 */
	<T extends Action> String getActionType(Class<T> actionClass);
}
