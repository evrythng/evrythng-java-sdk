/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.mapping;

import com.evrythng.thng.resource.model.store.action.Action;
import com.evrythng.thng.resource.model.store.action.ActionType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

/**
 * Action deserializer.
 *
 **/
public final class ActionDeserializerImpl extends TypeMapDeserializer<Action> implements ActionDeserializer {

	private static final long serialVersionUID = 1L;

	private Class<? extends Action> customClass;

	public ActionDeserializerImpl(final Class<? extends Action> customClass) {

		super(Action.class, Action.FIELD_TYPE);
		this.customClass = customClass;
	}

	@Override
	public Action deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException {

		ObjectCodec codec = jp.getCodec();
		ObjectMapper mapper = (ObjectMapper) codec;
		ObjectNode root = mapper.readTree(jp);
		JsonNode type = root.get(getTypeFieldName());
		if (type == null) {
			throw new IllegalArgumentException(this.getValueClass().getSimpleName() + " type cannot be empty.");
		}
		String sType = type.textValue();
		if (sType == null || sType.isEmpty()) {
			throw new IllegalArgumentException(this.getValueClass().getSimpleName() + " type cannot be empty.");
		}

		return codec.treeToValue(root, resolveClass(sType));
	}

	@Override
	protected Class<? extends Action> resolveClass(final String type) {

		return ActionType.Value.isCustom(type) ? customClass : super.resolveClass(type);
	}

	@Override
	public <T extends Action> String getActionType(final Class<T> actionClass) {
		return getObjectType(actionClass);
	}
}