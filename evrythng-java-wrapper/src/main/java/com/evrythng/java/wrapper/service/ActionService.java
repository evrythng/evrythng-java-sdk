/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.service;

import com.evrythng.commons.domain.SortOrder;
import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.java.wrapper.mapping.ActionDeserializer;
import com.evrythng.java.wrapper.mapping.EvrythngJacksonModule;
import com.evrythng.thng.resource.model.store.action.Action;
import com.evrythng.thng.resource.model.store.action.ActionType;
import com.evrythng.thng.resource.model.store.action.Actions;
import com.evrythng.thng.resource.model.store.action.CustomAction;
import com.fasterxml.jackson.core.type.TypeReference;
import org.pcollections.PVector;

import java.util.Iterator;
import java.util.List;

/**
 * Service wrapper for the {@code /actions} endpoint of the EVRYTHNG API.
 */
public class ActionService extends EvrythngServiceBase {

	private static final TypeReference<List<Action>> ACTIONS_TYPE_REFERENCE = new TypeReference<List<Action>>() {};

	public static final String PATH_ACTIONS = "/actions";
	public static final String PATH_ALL_ACTIONS = PATH_ACTIONS + "/all";
	public static final String PATH_ALL_ACTION = PATH_ALL_ACTIONS + "/%s";
	public static final String PATH_TYPED_ACTIONS = PATH_ACTIONS + "/%s";
	public static final String PATH_TYPED_ACTION = PATH_TYPED_ACTIONS + "/%s";
	protected ActionDeserializer deserializer;

	/**
	 * @param apiManager {@link ApiManager} instance
	 */
	public ActionService(final ApiManager apiManager, final EvrythngJacksonModule evrythngJacksonModule) {

		super(apiManager);
		deserializer = evrythngJacksonModule.getActionDeserializer();
	}

	/**
	 * Gets the custom types and the built-in action types.
	 */
	public Builder<List<ActionType>> actionTypesReader() throws EvrythngClientException {

		return get(PATH_ACTIONS, new TypeReference<List<ActionType>>() {

		});
	}

	/**
	 * Creates an action type.
	 */
	public Builder<ActionType> actionTypeCreator(final ActionType type) throws EvrythngClientException {

		return post(PATH_ACTIONS, type, new TypeReference<ActionType>() {

		});
	}

	/**
	 * Deletes an action type and all actions of this type.
	 */
	public Builder<Boolean> actionTypeDeleter(final String type) throws EvrythngClientException {

		return delete(String.format(PATH_TYPED_ACTIONS, type));
	}

	/**
	 * Creates an action.
	 */
	public <T extends Action> Builder<T> actionCreator(final T action) throws EvrythngClientException {

		return (Builder<T>) post(String.format(PATH_TYPED_ACTIONS, action.getType()), action, new TypeReference<Action>() {

		});
	}

	/**
	 * Creates an action.
	 */
	public Builder<Actions> actionsCreator(final Actions actions) throws EvrythngClientException {

		return post(PATH_ALL_ACTIONS, actions, new TypeReference<Actions>() {

		});
	}

	/**
	 * Gets all the actions.
	 * @deprecated use {@link #iterator()} instead.
	 */
	public Builder<List<Action>> actionsReader() throws EvrythngClientException {

		return get(PATH_ALL_ACTIONS, ACTIONS_TYPE_REFERENCE);
	}

	/**
	 * Gets all the action of a type.
	 */
	@SuppressWarnings("unchecked")
	public <T extends Action> Builder<List<T>> actionsReader(final Class<T> actionClass) throws EvrythngClientException {

		String type = getType(actionClass);
		return (Builder<List<T>>) (Builder<?>) get(String.format(PATH_TYPED_ACTIONS, type), ACTIONS_TYPE_REFERENCE);
	}

	/**
	 * Gets all the action of a type.
	 * @deprecated use {@link #iterator(String)} instead.
	 */
	public Builder<List<CustomAction>> actionsReader(final String customType) throws EvrythngClientException {

		checkCustomType(customType);
		return get(String.format(PATH_TYPED_ACTIONS, customType), new TypeReference<List<CustomAction>>() {

		});
	}

	/**
	 * Gets one action by id.
	 */
	public Builder<Action> actionReader(final String id) throws EvrythngClientException {

		return get(String.format(PATH_ALL_ACTION, id), new TypeReference<Action>() {

		});
	}

	/**
	 * Gets one action by id and type.
	 */
	@SuppressWarnings("unchecked")
	public <T extends Action> Builder<T> actionReader(final Class<T> actionClass, final String id) throws EvrythngClientException {

		String type = getType(actionClass);
		return (Builder<T>) get(String.format(PATH_TYPED_ACTION, type, id), new TypeReference<Action>() {

		});
	}

	/**
	 * Gets one action by id and type.
	 */
	public Builder<CustomAction> actionReader(final String customType, final String id) throws EvrythngClientException {

		checkCustomType(customType);
		return get(String.format(PATH_TYPED_ACTION, customType, id), new TypeReference<CustomAction>() {

		});
	}

	/**
	 * Deletes one action by id and type.
	 */
	public <T extends Action> Builder<Boolean> actionDeleter(final Class<T> actionClass, final String id) throws EvrythngClientException {

		String type = getType(actionClass);
		return delete(String.format(PATH_TYPED_ACTION, type, id));
	}

	/**
	 * Deletes one action by id and type.
	 */
	public Builder<Boolean> actionDeleter(final String customType, final String id) throws EvrythngClientException {

		checkCustomType(customType);
		return delete(String.format(PATH_TYPED_ACTION, customType, id));
	}

	/**
	 * Updates an action type.
	 */
	public Builder<ActionType> actionTypeUpdater(final String actionTypeName, final ActionType update) throws EvrythngClientException {

		return put(String.format(PATH_TYPED_ACTIONS, actionTypeName), update, new TypeReference<ActionType>() {

		});
	}

	protected void checkCustomType(final String customType) {

		if (!customType.startsWith("_")) {
			throw new IllegalArgumentException("Custom types must start with '_' (underscore).");
		}
	}

	public <T extends Action> String getType(final Class<T> actionClass) {

		String type = deserializer.getActionType(actionClass);
		if (type == null) {
			throw new IllegalArgumentException("The action type is not recognized.");
		}
		return type;
	}

	/**
	 * Retrieves all actions.
	 */
	public Builder<Iterator<PVector<Action>>> iterator() {
		return iterator(PATH_ALL_ACTIONS, ACTIONS_TYPE_REFERENCE).sortOrder(SortOrder.descending());
	}

	/**
	 * Retrieves all actions of type.
	 * @param actionTypeName action type.
	 */
	public Builder<Iterator<PVector<Action>>> iterator(final String actionTypeName) {
		return iterator(String.format(PATH_TYPED_ACTIONS, actionTypeName), ACTIONS_TYPE_REFERENCE).sortOrder(SortOrder.descending());
	}
}
