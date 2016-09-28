/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.rule.reaction;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Base class for reactions
 **/
public abstract class Reaction implements Serializable {

	private static final long serialVersionUID = -1577961761286375641L;

	public static final String FIELD_TYPE = "type";
	private String type;
	private Map<String, Object> customFields;

	public abstract void accept(ReactionVisitor visitor);

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public Map<String, Object> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(final Map<String, Object> customFields) {

		this.customFields = customFields;
	}

	public void addCustomFields(final String key, final Object value) {

		if (customFields == null) {
			customFields = new HashMap<>();
		}
		customFields.put(key, value);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder("Reaction{");
		sb.append("customFields=").append(customFields);
		sb.append(", type='").append(type).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
