package com.evrythng.java.wrapper.service;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.java.wrapper.mapping.ActionDeserializer;
import com.evrythng.java.wrapper.mapping.EvrythngJacksonModule;
import com.evrythng.thng.resource.model.store.BooleanProperty;
import com.evrythng.thng.resource.model.store.NumberProperty;
import com.evrythng.thng.resource.model.store.Product;
import com.evrythng.thng.resource.model.store.Property;
import com.evrythng.thng.resource.model.store.Redirector;
import com.evrythng.thng.resource.model.store.StringProperty;
import com.evrythng.thng.resource.model.store.action.Action;
import com.evrythng.thng.resource.model.store.action.CustomAction;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Collections;
import java.util.List;

/**
 * Service wrapper for the {@code /products} endpoint of the EVRYTHNG Engine API.
 */
public class ProductService extends EvrythngServiceBase {

	public static final String PATH_PRODUCTS = "/products";
	public static final String PATH_PRODUCT = PATH_PRODUCTS + "/%s";
	public static final String PATH_PRODUCT_PROPERTIES = PATH_PRODUCT + "/properties";
	public static final String PATH_PRODUCT_PROPERTY = PATH_PRODUCT_PROPERTIES + "/%s";
	public static final String PATH_PRODUCT_REDIRECTOR = PATH_PRODUCT + "/redirector";
	public static final String PATH_PRODUCT_REDIRECTOR_QR = PATH_PRODUCT_REDIRECTOR + "/qr";
	public static final String PATH_PRODUCT_ACTIONS = PATH_PRODUCT + "/actions";
	public static final String PATH_PRODUCT_ALL_ACTIONS = PATH_PRODUCT_ACTIONS + "/all";
	public static final String PATH_PRODUCT_ALL_ACTION = PATH_PRODUCT_ALL_ACTIONS + "/%s";
	public static final String PATH_PRODUCT_TYPED_ACTIONS = PATH_PRODUCT_ACTIONS + "/%s";
	public static final String PATH_PRODUCT_TYPED_ACTION = PATH_PRODUCT_TYPED_ACTIONS + "/%s";

	protected ActionDeserializer deserializer;

	public ProductService(final ApiManager apiManager, final EvrythngJacksonModule evrythngJacksonModule) {

		super(apiManager);
		deserializer = evrythngJacksonModule.getActionDeserializer();
	}

	/* ***** /products ***** */

	/**
	 * POST {@value #PATH_PRODUCTS}
	 *
	 * @param product {@link Product} instance
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Product> productCreator(final Product product) throws EvrythngClientException {

		return post(PATH_PRODUCTS, product, new TypeReference<Product>() {

		});
	}

	/**
	 * GET {@value #PATH_PRODUCTS}
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<List<Product>> productsReader() throws EvrythngClientException {

		return get(PATH_PRODUCTS, new TypeReference<List<Product>>() {

		});
	}

	/* ***** /products/{id} ***** */

	/**
	 * GET {@value #PATH_PRODUCT}
	 *
	 * @param productId product id
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Product> productReader(final String productId) throws EvrythngClientException {

		return get(String.format(PATH_PRODUCT, productId), new TypeReference<Product>() {

		});
	}

	/**
	 * Batch update a list of {@link Product}. Select the {Product}s to update using query parameters ?ids or ?filter.
	 * <p>
	 * PUT {@value #PATH_PRODUCTS}
	 *
	 * @param product {@link Product} instance
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Long> productsUpdater(final Product product) throws EvrythngClientException {

		return putMultiple(PATH_PRODUCTS, product);
	}

	/**
	 * PUT {@value #PATH_PRODUCT}
	 *
	 * @param productId product id
	 * @param product   {@link Product} instance
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Product> productUpdater(final String productId, final Product product) throws EvrythngClientException {

		return put(String.format(PATH_PRODUCT, productId), product, new TypeReference<Product>() {

		});
	}

	/**
	 * DELETE {@value #PATH_PRODUCT}
	 *
	 * @param productId product id
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Boolean> productDeleter(final String productId) throws EvrythngClientException {

		return delete(String.format(PATH_PRODUCT, productId));
	}

	/**
	 * Bulk delete some products. Select the {Product}s to delete using query parameters ?ids or ?filter.
	 * <p>
	 * DELETE {@value #PATH_PRODUCTS}
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Long> productsDeleter() throws EvrythngClientException {

		return deleteMultiple(PATH_PRODUCTS);
	}

	/* ***** /products/{id}/properties ***** */

	/**
	 * PUT {@value #PATH_PRODUCT_PROPERTIES}
	 *
	 * @param productId  product id
	 * @param properties list of {@link com.evrythng.thng.resource.model.store.Property}
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<List<Property<?>>> propertiesCreator(final String productId,
	                                                           final List<Property<?>> properties)
			throws EvrythngClientException {

		return put(String.format(PATH_PRODUCT_PROPERTIES, productId), properties, new TypeReference<List<Property<?>>>
				() {

		});
	}

	/**
	 * GET {@value #PATH_PRODUCT_PROPERTIES}
	 *
	 * @param productId product id
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<List<Property<?>>> propertiesReader(final String productId) throws EvrythngClientException {

		return get(String.format(PATH_PRODUCT_PROPERTIES, productId), new TypeReference<List<Property<?>>>() {

		});
	}

	/**
	 * DELETE {@value #PATH_PRODUCT_PROPERTIES}
	 *
	 * @param productId product id
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Boolean> propertiesDeleter(final String productId) throws EvrythngClientException {

		return delete(String.format(PATH_PRODUCT_PROPERTIES, productId));
	}

	/**
	 * GET {@value #PATH_PRODUCT_PROPERTY}
	 *
	 * @param productId product id
	 * @param key       key
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<List<Property<?>>> propertyReader(final String productId, final String key)
			throws EvrythngClientException {

		return get(String.format(PATH_PRODUCT_PROPERTY, productId, key), new TypeReference<List<Property<?>>>() {

		});
	}

	/**
	 * PUT {@value #PATH_PRODUCT_PROPERTY}
	 *
	 * @param productId product id
	 * @param key       key
	 * @param update    property update
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<List<Property<?>>> propertyUpdater(final String productId, final String key,
	                                                         final Property<?> update)
			throws EvrythngClientException {

		return put(String.format(PATH_PRODUCT_PROPERTY, productId, key), Collections.singletonList(update),
		           new TypeReference<List<Property<?>>>() {

		           });
	}

	public Builder<List<Property<?>>> propertyUpdater(final String productId, final String key, final String value, final Long timestamp)
			throws EvrythngClientException {

		return put(String.format(PATH_PRODUCT_PROPERTY, productId, key), Collections.singletonList(new StringProperty(null, value, timestamp)),
		           new TypeReference<List<Property<?>>>() {

		           });
	}

	public Builder<List<Property<?>>> propertyUpdater(final String productId, final String key, final Number value, final Long timestamp)
			throws EvrythngClientException {

		return put(String.format(PATH_PRODUCT_PROPERTY, productId, key),
		           Collections.singletonList(new NumberProperty(null, value.doubleValue(), timestamp)),
		           new TypeReference<List<Property<?>>>() {

		           });
	}

	public Builder<List<Property<?>>> propertyUpdater(final String productId, final String key, final Boolean value, final Long timestamp)
			throws EvrythngClientException {

		return put(String.format(PATH_PRODUCT_PROPERTY, productId, key), Collections.singletonList(new BooleanProperty(null, value, timestamp)),
		           new TypeReference<List<Property<?>>>() {

		           });
	}

	public Builder<List<Property<?>>> propertyUpdater(final String thngId, final String key, final String value)
			throws EvrythngClientException {

		return propertyUpdater(thngId, key, value, null);
	}

	public Builder<List<Property<?>>> propertyUpdater(final String thngId, final String key, final Number value)
			throws EvrythngClientException {

		return propertyUpdater(thngId, key, value, null);
	}

	public Builder<List<Property<?>>> propertyUpdater(final String thngId, final String key, final Boolean value)
			throws EvrythngClientException {

		return propertyUpdater(thngId, key, value, null);
	}

	/**
	 * DELETE {@value #PATH_PRODUCT_PROPERTY}
	 *
	 * @param productId product id
	 * @param key       key
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Boolean> propertyDeleter(final String productId, final String key) throws EvrythngClientException {

		return delete(String.format(PATH_PRODUCT_PROPERTY, productId, key));
	}

	/* ***** /products/{id}/redirector ***** */

	/**
	 * POST {@value #PATH_PRODUCT_REDIRECTOR}
	 * <p>
	 * Creates a redirector for the Product.
	 *
	 * @param productId   product id
	 * @param redirection {@link Redirector} instance
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Redirector> redirectorCreator(final String productId, final Redirector redirection)
			throws EvrythngClientException {

		return post(String.format(PATH_PRODUCT_REDIRECTOR, productId), redirection, new TypeReference<Redirector>() {

		});
	}

	/**
	 * GET {@value #PATH_PRODUCT_REDIRECTOR}
	 * <p>
	 * Retrieves the redirector for the Product.
	 *
	 * @param productId product id
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Redirector> redirectorReader(final String productId) throws EvrythngClientException {

		return get(String.format(PATH_PRODUCT_REDIRECTOR, productId), new TypeReference<Redirector>() {

		});
	}

	/**
	 * DELETE {@value #PATH_PRODUCT_REDIRECTOR}
	 * <p>
	 * Deletes the redirector for the Product.
	 *
	 * @param productId product id
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Boolean> redirectorDeleter(final String productId) throws EvrythngClientException {

		return delete(String.format(PATH_PRODUCT_REDIRECTOR, productId));
	}

	/**
	 * PUT {@value #PATH_PRODUCT_REDIRECTOR}
	 * <p>
	 * Updates the redirector for the Product.
	 *
	 * @param productId   product id
	 * @param redirection {@link Redirector} instance
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Redirector> redirectorUpdater(final String productId, final Redirector redirection)
			throws EvrythngClientException {

		return put(String.format(PATH_PRODUCT_REDIRECTOR, productId), redirection, new TypeReference<Redirector>() {

		});
	}

	/**
	 * Creates an action.
	 */
	public <T extends Action> Builder<T> actionCreator(final String productId, final T action)
			throws EvrythngClientException {

		return (Builder<T>) post(String.format(PATH_PRODUCT_TYPED_ACTIONS, productId, action.getType()), action,
		                                new TypeReference<Action>() {

		                         });
	}

	/**
	 * Creates an action using /products/../actions/all endpoint.
	 */
	public Builder<Action> actionAllCreator(final String productId, final Action action)
			throws EvrythngClientException {

		return post(String.format(PATH_PRODUCT_ALL_ACTIONS, productId), action,
		                         new TypeReference<Action>() {

		                         });
	}

	/**
	 * Gets one action by actionId and type.
	 */
	@SuppressWarnings("unchecked")
	public <T extends Action> Builder<T> actionReader(final String productId, final Class<T> actionClass, final String actionId) throws EvrythngClientException {

		String type = getType(actionClass);
		return (Builder<T>) get(String.format(PATH_PRODUCT_TYPED_ACTION, productId, type, actionId), new TypeReference<Action>() {

		});
	}

	/**
	 * Gets one action by actionId and type.
	 */
	public Builder<CustomAction> actionReader(final String productId, final String customType, final String actionId) throws EvrythngClientException {

		checkCustomType(customType);
		return get(String.format(PATH_PRODUCT_TYPED_ACTION, productId, customType, actionId), new TypeReference<CustomAction>() {

		});
	}

	/**
	 * Gets one action by actionId.
	 */
	public Builder<Action> actionReader(final String productId, final String actionId) throws EvrythngClientException {

		return get(String.format(PATH_PRODUCT_ALL_ACTION, productId, actionId), new TypeReference<Action>() {

		});
	}

	/**
	 * Gets all the actions.
	 */
	public Builder<List<Action>> actionsReader(final String productId) throws EvrythngClientException {

		return get(String.format(PATH_PRODUCT_ALL_ACTIONS, productId), new TypeReference<List<Action>>() {

		});
	}

	/**
	 * Gets all the action of a type.
	 */
	@SuppressWarnings("unchecked")
	public <T extends Action> Builder<List<T>> actionsReader(final String productId, final Class<T> actionClass) throws EvrythngClientException {

		String type = getType(actionClass);
		return (Builder<List<T>>) (Builder<?>) get(String.format(PATH_PRODUCT_TYPED_ACTIONS, productId, type), new TypeReference<List<Action>>() {

		});
	}

	/**
	 * Gets all the action of a type.
	 */
	public Builder<List<CustomAction>> actionsReader(final String productId, final String customType) throws EvrythngClientException {

		checkCustomType(customType);
		return get(String.format(PATH_PRODUCT_TYPED_ACTIONS, productId, customType), new TypeReference<List<CustomAction>>() {

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

}
