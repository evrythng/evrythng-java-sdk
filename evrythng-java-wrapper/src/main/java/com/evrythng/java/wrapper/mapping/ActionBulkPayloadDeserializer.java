/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.java.wrapper.mapping;

import com.evrythng.thng.resource.model.store.action.bulk.FilePayload;
import com.evrythng.thng.resource.model.store.action.bulk.ListPayload;
import com.evrythng.thng.resource.model.store.action.bulk.Payload;

public class ActionBulkPayloadDeserializer extends TypeMapDeserializer<Payload> {

	private static final long serialVersionUID = -7443066832492403767L;

	public ActionBulkPayloadDeserializer() {

		super(Payload.class, Payload.FIELD_TYPE);
		registerType(Payload.Type.LIST.toString(), ListPayload.class);
		registerType(Payload.Type.FILE.toString(), FilePayload.class);
	}
}
