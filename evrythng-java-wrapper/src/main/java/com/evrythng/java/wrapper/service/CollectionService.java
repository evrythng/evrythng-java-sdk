package com.evrythng.java.wrapper.service;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.java.wrapper.core.http.Status;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.thng.resource.model.store.Collection;
import com.evrythng.thng.resource.model.store.Thng;
import com.evrythng.thng.resource.model.store.action.Action;
import com.evrythng.thng.resource.model.store.action.CustomAction;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Service wrapper for the {@code /collections} endpoint of the EVRYTHNG API.
 */
public class CollectionService extends EvrythngServiceBase {

	public static final String PATH_COLLECTIONS = "/collections";
	public static final String PATH_COLLECTION = PATH_COLLECTIONS + "/%s";
	public static final String PATH_COLLECTION_THNGS = PATH_COLLECTION + "/thngs";
	public static final String PATH_COLLECTION_THNG = PATH_COLLECTION_THNGS + "/%s";
	public static final String PATH_COLLECTIONS_ACTIONS = PATH_COLLECTION + "/actions";
	public static final String PATH_COLLECTIONS_TYPED_ACTIONS = PATH_COLLECTIONS_ACTIONS + "/%s";
	public static final String PATH_COLLECTIONS_TYPED_ACTION = PATH_COLLECTIONS_TYPED_ACTIONS + "/%s";
	public static final String PATH_CHILDREN_COLLECTIONS = PATH_COLLECTION + "/collections";
	public static final String PATH_CHILD_COLLECTION = PATH_CHILDREN_COLLECTIONS + "/%s";

	public CollectionService(final ApiManager apiManager) {

		super(apiManager);
	}

	/* ***** /collections ***** */

	/**
	 * Creates a new {@link Collection}.
	 * <p>
	 * POST {@value #PATH_COLLECTIONS}
	 *
	 * @param collection the instant holding the {@link Collection} resource data
	 * @return a {@link Collection} creator
	 */
	public Builder<Collection> collectionCreator(final Collection collection) throws EvrythngClientException {

		return post(PATH_COLLECTIONS, collection, new TypeReference<Collection>() {

		});
	}

	/**
	 * Retrieves the last updated {@link Collection} resources.
	 * <p>
	 * GET {@value #PATH_COLLECTIONS}
	 *
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<List<Collection>> collectionsReader() throws EvrythngClientException {

		return get(PATH_COLLECTIONS, new TypeReference<List<Collection>>() {

		});
	}

	/* ***** /collections/{id} ***** */

	/**
	 * Retrieves the referenced {@link Collection}.
	 * <p>
	 * GET {@value #PATH_COLLECTION}
	 *
	 * @param collectionId collection id
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<Collection> collectionReader(final String collectionId) throws EvrythngClientException {

		return get(String.format(PATH_COLLECTION, collectionId), new TypeReference<Collection>() {

		});
	}

	/**
	 * Updates the referenced {@link Collection}.
	 * <p>
	 * PUT {@value #PATH_COLLECTION}
	 *
	 * @param collectionId collection id
	 * @param collection   {@link Collection} instance
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<Collection> collectionUpdater(final String collectionId, final Collection collection) throws EvrythngClientException {

		return put(String.format(PATH_COLLECTION, collectionId), collection, new TypeReference<Collection>() {

		});
	}

	/**
	 * Deletes the referenced {@link Collection}.
	 * <p>
	 * DELETE {@value #PATH_COLLECTION}
	 *
	 * @param collectionId collection id
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<Boolean> collectionDeleter(final String collectionId) throws EvrythngClientException {

		return delete(String.format(PATH_COLLECTION, collectionId));
	}

	/* ***** /collections/{id}/thngs ***** */

	/**
	 * Retrieves the last updated {@link Thng} resources linked with the
	 * referenced {@link Collection}.
	 * <p>
	 * GET {@value #PATH_COLLECTION_THNGS}
	 *
	 * @param collectionId collection id
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<List<Thng>> thngsReader(final String collectionId) throws EvrythngClientException {

		return get(String.format(PATH_COLLECTION_THNGS, collectionId), new TypeReference<List<Thng>>() {

		});
	}

	/**
	 * Adds the referenced {@link Thng} to the provided {@link Collection}.
	 * <p>
	 * PUT {@value #PATH_COLLECTION_THNGS}
	 *
	 * @param collectionId collection id
	 * @param thngId       thng id
	 * @return a preconfigured {@link Builder}
	 * @see #thngsAdder(String, List)
	 */
	public Builder<Collection> thngAdder(final String collectionId, final String thngId) throws EvrythngClientException {

		return thngsAdder(collectionId, Collections.singletonList(thngId));
	}

	/**
	 * Adds the referenced {@link Thng} resources to the provided
	 * {@link Collection}.
	 * <p>
	 * PUT {@value #PATH_COLLECTION_THNGS}
	 *
	 * @param collectionId collection id
	 * @param thngs        list of thng ids
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<Collection> thngsAdder(final String collectionId, final List<String> thngs) throws EvrythngClientException {

		return put(String.format(PATH_COLLECTION_THNGS, collectionId), thngs, new TypeReference<Collection>() {

		});
	}

	/* ***** /collections/{id}/thngs/{id} ***** */

	/**
	 * Removes the referenced {@link Thng} from the provided {@link Collection}.
	 * <p>
	 * DELETE {@value #PATH_COLLECTION_THNG}
	 *
	 * @param collectionId collection id
	 * @param thngId       thng id
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<Boolean> thngRemover(final String collectionId, final String thngId) throws EvrythngClientException {

		return delete(String.format(PATH_COLLECTION_THNG, collectionId, thngId));
	}

	/**
	 * Removes all {@link Thng} resources from the provided {@link Collection}.
	 * <p>
	 * DELETE {@value #PATH_COLLECTION_THNGS}
	 *
	 * @param collectionId collection id
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<Boolean> thngsRemover(final String collectionId) throws EvrythngClientException {

		return delete(String.format(PATH_COLLECTION_THNGS, collectionId));
	}

	protected void checkCustomType(final String customType) {

		if (!customType.startsWith("_")) {
			throw new IllegalArgumentException("Custom types must start with '_' (underscore).");
		}
	}

	public <T extends Action> Builder<T> actionCreator(final String collectionId, final T actionToCreate)
			throws EvrythngClientException {

		return (Builder<T>) post(String.format(PATH_COLLECTIONS_TYPED_ACTIONS, collectionId, actionToCreate.getType()),
		                                actionToCreate, new TypeReference<Action>() {

				});
	}

	public Builder<CustomAction> actionReader(final String collectionId, final String customType, final String actionId) throws EvrythngClientException {

		checkCustomType(customType);
		return get(String.format(PATH_COLLECTIONS_TYPED_ACTION, collectionId, customType, actionId), new TypeReference<CustomAction>() {

		});
	}

	public Builder<List<CustomAction>> actionsReader(final String collectionId, final String customType) throws EvrythngClientException {

		checkCustomType(customType);
		return get(String.format(PATH_COLLECTIONS_TYPED_ACTIONS, collectionId, customType), new TypeReference<List<CustomAction>>() {

		});
	}

	private static void notBlank(final String target, final String targetName) {

		if (target == null || target.isEmpty()) {
			throw new IllegalArgumentException(targetName + " cannot be blank");
		}
	}

	private static void notNull(final Object target, final String targetName) {

		if (target == null) {
			throw new IllegalArgumentException(targetName + " cannot be null");
		}
	}

	/**
	 * Removes all children {@link Collection}s from the provided {@link Collection}.
	 * <p>
	 * DELETE {@value #PATH_CHILDREN_COLLECTIONS}
	 */
	public Builder<Boolean> childrenCollectionsRemover(final String collectionId) throws EvrythngClientException {

		notBlank(collectionId, "collectionId");
		return delete(String.format(PATH_CHILDREN_COLLECTIONS, collectionId));
	}

	/**
	 * Removes the provided child {@link Collection} from the provided {@link Collection}.
	 * <p>
	 * DELETE {@value #PATH_CHILD_COLLECTION}
	 */
	public Builder<Boolean> childCollectionRemover(final String collectionId, final String childCollectionId)
			throws EvrythngClientException {

		notBlank(collectionId, "collectionId");
		notBlank(childCollectionId, "childCollectionId");
		return delete(String.format(PATH_CHILD_COLLECTION, collectionId, childCollectionId));
	}

	/**
	 * Adds children {@link Collection}s matching the provided ids to the provided {@link Collection}.
	 * <p>
	 * POST {@value #PATH_CHILDREN_COLLECTIONS}
	 */
	public Builder<Void> childrenCollectionsAdder(final String collectionId, final Set<String> childrenCollectionsId)
			throws EvrythngClientException {

		notBlank(collectionId, "collectionId");
		notNull(childrenCollectionsId, "childrenCollectionsId");
		for (String id : childrenCollectionsId) {
			notBlank(id, "childCollectionId");
		}
		return post(String.format(PATH_CHILDREN_COLLECTIONS, collectionId), childrenCollectionsId, Status.OK, new TypeReference<Void>() {

		});
	}

	/**
	 * Adds child {@link Collection} matching the provided id to the provided {@link Collection}.
	 * <p>
	 * POST {@value #PATH_CHILDREN_COLLECTIONS}
	 */
	public Builder<Void> childCollectionAdder(final String collectionId, final String childCollectionId)
			throws EvrythngClientException {

		notBlank(collectionId, "collectionId");
		notBlank(childCollectionId, "childCollectionId");
		return childrenCollectionsAdder(collectionId, Collections.singleton(childCollectionId));
	}

	/**
	 * Retrieves all children {@link Collection}s for the provided {@link Collection}.
	 * <p>
	 * GET {@value #PATH_CHILDREN_COLLECTIONS}
	 */
	public Builder<List<Collection>> childrenCollectionsReader(final String collectionId) throws EvrythngClientException {

		notBlank(collectionId, "collectionId");
		return get(String.format(PATH_CHILDREN_COLLECTIONS, collectionId), new TypeReference<List<Collection>>() {

		});
	}
}
