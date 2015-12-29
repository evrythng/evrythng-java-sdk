/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.java.wrapper.mapping;

import com.evrythng.thng.resource.model.store.Property;
import com.evrythng.thng.resource.model.store.action.Action;
import com.evrythng.thng.resource.model.store.action.Actions;
import com.evrythng.thng.resource.model.store.action.CheckinAction;
import com.evrythng.thng.resource.model.store.action.CustomAction;
import com.evrythng.thng.resource.model.store.action.ImplicitScanAction;
import com.evrythng.thng.resource.model.store.action.ScanAction;
import com.evrythng.thng.resource.model.store.action.ShareAction;
import com.evrythng.thng.resource.model.store.action.bulk.Content;
import com.evrythng.thng.resource.model.store.action.bulk.Payload;
import com.evrythng.thng.resource.model.store.geojson.GeoJson;
import com.evrythng.thng.resource.model.store.geojson.GeoJsonMultiPoint;
import com.evrythng.thng.resource.model.store.geojson.GeoJsonPoint;
import com.evrythng.thng.resource.model.store.geojson.GeoJsonPolygon;
import com.evrythng.thng.resource.model.store.geojson.GeoJsonType;
import com.evrythng.thng.resource.model.store.rule.reaction.ActionTagsSetReaction;
import com.evrythng.thng.resource.model.store.rule.reaction.DataAddedReaction;
import com.evrythng.thng.resource.model.store.rule.reaction.LoyaltyPointsChangedReaction;
import com.evrythng.thng.resource.model.store.rule.reaction.MultimediaContentGrantedReaction;
import com.evrythng.thng.resource.model.store.rule.reaction.PhysicalAssetGrantedReaction;
import com.evrythng.thng.resource.model.store.rule.reaction.PostedToSocialNetworkReaction;
import com.evrythng.thng.resource.model.store.rule.reaction.Reaction;
import com.evrythng.thng.resource.model.store.rule.reaction.RedirectionReaction;
import com.evrythng.thng.resource.model.store.rule.reaction.TextAddedReaction;
import com.evrythng.thng.resource.model.store.rule.reaction.TierLevelChangedReaction;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * EVRYTHNG jackson module.
 */
public class EvrythngJacksonModuleImpl extends SimpleModule implements EvrythngJacksonModule {

	private static final long serialVersionUID = 4396301763122454156L;

	ActionDeserializerImpl actionDeserializer;

	ReactionDeserializerImpl reactionDeserializer;

	GeoJsonDeserializerImpl geoJSONDeserializer;

	public EvrythngJacksonModuleImpl() {

		this("EVRYTHNG wrapper", new Version(1, 0, 0, "", "", "")); // TODO __MY__
	}

	public EvrythngJacksonModuleImpl(final String name, final Version version) {

		super(name, version);
		actionDeserializer = createActionDeserializer();
		addDeserializer(Action.class, actionDeserializer);
		addDeserializer(Actions.class, new ActionsDeserializer());
		reactionDeserializer = createReactionDeserializer();
		geoJSONDeserializer = createGeoJSONDeserializer();
		PropertyDeserializer propertyDeserializer = new PropertyDeserializer();
		addDeserializer(Reaction.class, reactionDeserializer);
		addDeserializer(GeoJson.class, geoJSONDeserializer);
		addDeserializer(Property.class, propertyDeserializer);

		addDeserializer(Content.class, new ActionBulkContentDeserializer());
		addDeserializer(Payload.class, new ActionBulkPayloadDeserializer());
	}

	public static ActionDeserializerImpl createActionDeserializer() {

		ActionDeserializerImpl actionDeserializer = new ActionDeserializerImpl(CustomAction.class);
		actionDeserializer.registerType(CheckinAction.TYPE, CheckinAction.class);
		actionDeserializer.registerType(ScanAction.TYPE, ScanAction.class);
		actionDeserializer.registerType(ImplicitScanAction.TYPE, ImplicitScanAction.class);
		actionDeserializer.registerType(ShareAction.TYPE, ShareAction.class);
		return actionDeserializer;
	}

	public static ReactionDeserializerImpl createReactionDeserializer() {

		ReactionDeserializerImpl reactionDeserializer = new ReactionDeserializerImpl();
		reactionDeserializer.registerType(PhysicalAssetGrantedReaction.TYPE, PhysicalAssetGrantedReaction.class);
		reactionDeserializer.registerType(MultimediaContentGrantedReaction.TYPE, MultimediaContentGrantedReaction.class);
		reactionDeserializer.registerType(LoyaltyPointsChangedReaction.TYPE, LoyaltyPointsChangedReaction.class);
		reactionDeserializer.registerType(TextAddedReaction.TYPE, TextAddedReaction.class);
		reactionDeserializer.registerType(TierLevelChangedReaction.TYPE, TierLevelChangedReaction.class);
		reactionDeserializer.registerType(PostedToSocialNetworkReaction.TYPE, PostedToSocialNetworkReaction.class);
		reactionDeserializer.registerType(ActionTagsSetReaction.TYPE, ActionTagsSetReaction.class);
		reactionDeserializer.registerType(DataAddedReaction.TYPE, DataAddedReaction.class);
		reactionDeserializer.registerType(RedirectionReaction.TYPE, RedirectionReaction.class);
		return reactionDeserializer;
	}

	public static GeoJsonDeserializerImpl createGeoJSONDeserializer() {

		GeoJsonDeserializerImpl geoJSONDeserializer = new GeoJsonDeserializerImpl();
		geoJSONDeserializer.registerType(GeoJsonType.POINT.toString(), GeoJsonPoint.class);
		geoJSONDeserializer.registerType(GeoJsonType.MULTI_POINT.toString(), GeoJsonMultiPoint.class);
		geoJSONDeserializer.registerType(GeoJsonType.POLYGON.toString(), GeoJsonPolygon.class);
		return geoJSONDeserializer;
	}

	@Override
	public ReactionDeserializerImpl getReactionDeserializer() {
		return reactionDeserializer;
	}

	@Override
	public GeoJsonDeserializerImpl getGeoJSONDeserializer() {
		return geoJSONDeserializer;
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
