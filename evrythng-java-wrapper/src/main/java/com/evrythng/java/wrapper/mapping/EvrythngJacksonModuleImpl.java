/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.java.wrapper.mapping;

import com.evrythng.thng.resource.model.store.action.Action;
import com.evrythng.thng.resource.model.store.action.Actions;
import com.evrythng.thng.resource.model.store.action.CheckinAction;
import com.evrythng.thng.resource.model.store.action.CustomAction;
import com.evrythng.thng.resource.model.store.action.ImplicitScanAction;
import com.evrythng.thng.resource.model.store.action.ScanAction;
import com.evrythng.thng.resource.model.store.action.ShareAction;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * EVRYTHNG jackson module.
 */
public class EvrythngJacksonModuleImpl extends SimpleModule implements EvrythngJacksonModule {

	private static final long serialVersionUID = 4396301763122454156L;

	ActionDeserializerImpl actionDeserializer;

	public EvrythngJacksonModuleImpl() {

		this("EVRYTHNG wrapper", new Version(1, 0, 0, "", "", "")); // TODO __MY__
	}

	public EvrythngJacksonModuleImpl(final String name, final Version version) {

		super(name, version);
		actionDeserializer = createActionDeserializer();
		addDeserializer(Action.class, actionDeserializer);
		addDeserializer(Actions.class, new ActionsDeserializer());
	}

	public static ActionDeserializerImpl createActionDeserializer() {

		ActionDeserializerImpl actionDeserializer = new ActionDeserializerImpl(CustomAction.class);
		actionDeserializer.registerType(CheckinAction.TYPE, CheckinAction.class);
		actionDeserializer.registerType(ScanAction.TYPE, ScanAction.class);
		actionDeserializer.registerType(ImplicitScanAction.TYPE, ImplicitScanAction.class);
		actionDeserializer.registerType(ShareAction.TYPE, ShareAction.class);
		return actionDeserializer;
	}

	@Override
	public Module getModule() {

		return this;
	}

	@Override
	public ActionDeserializer getActionDeserializer() {

		return actionDeserializer;
	}
}
