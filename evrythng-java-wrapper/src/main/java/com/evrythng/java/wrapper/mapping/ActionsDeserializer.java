/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.mapping;

import com.evrythng.java.wrapper.util.JSONUtils;
import com.evrythng.thng.resource.model.store.action.Actions;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;

/**
* Base class for deserializers with a type map.
*/
public final class ActionsDeserializer extends Deserializer<Actions> {

	private static final long serialVersionUID = 154194441824112030L;

	private static final class ActionsImpl extends Actions {

		private static final long serialVersionUID = 7999055054999253850L;
	}

	public ActionsDeserializer() {

		super(Actions.class);
	}

	@Override
	public Actions deserialize(final JsonParser jp, final DeserializationContext ctxt)
			throws IOException {

		ObjectMapper mapper = JSONUtils.OBJECT_MAPPER;
		JsonNode node = mapper.readTree(jp);
		if (node.isArray()) {
			return mapper.readValue(node.toString(), ActionsImpl.class);
		} else {
			ArrayNode array = mapper.createArrayNode();
			array.add(node);
			return mapper.readValue(array.toString(), ActionsImpl.class);
		}
	}
}
