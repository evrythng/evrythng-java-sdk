/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.java.wrapper.mapping;

import com.evrythng.thng.resource.model.store.action.bulk.Content;
import com.evrythng.thng.resource.model.store.action.bulk.EvrythngIdsContent;
import com.evrythng.thng.resource.model.store.action.bulk.ExternalIdsContent;
import com.evrythng.thng.resource.model.store.action.bulk.ShortIdsContent;

public class ActionBulkContentDeserializer extends TypeMapDeserializer<Content> {

	private static final long serialVersionUID = -7278838754087724798L;

	public ActionBulkContentDeserializer() {

		super(Content.class, Content.FIELD_TYPE);
		registerType(Content.Type.EVRYTHNG_IDS.toString(), EvrythngIdsContent.class);
		registerType(Content.Type.EXTERNAL_IDS.toString(), ExternalIdsContent.class);
		registerType(Content.Type.SHORT_IDS.toString(), ShortIdsContent.class);
	}
}
