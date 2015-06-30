/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper;

import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.mapping.EvrythngJacksonModule;
import com.evrythng.java.wrapper.mapping.EvrythngJacksonModuleImpl;
import com.evrythng.java.wrapper.service.ActionService;
import com.evrythng.java.wrapper.service.ApplicationService;
import com.evrythng.java.wrapper.service.AuthService;
import com.evrythng.java.wrapper.service.BatchService;
import com.evrythng.java.wrapper.service.CollectionService;
import com.evrythng.java.wrapper.service.PlaceService;
import com.evrythng.java.wrapper.service.ProductService;
import com.evrythng.java.wrapper.service.ProjectService;
import com.evrythng.java.wrapper.service.RedirectorService;
import com.evrythng.java.wrapper.service.ScanService;
import com.evrythng.java.wrapper.service.SearchService;
import com.evrythng.java.wrapper.service.ThngService;
import com.evrythng.java.wrapper.service.TimeService;
import com.evrythng.java.wrapper.util.JSONUtils;
import com.evrythng.thng.commons.config.ApiConfiguration;
import org.apache.commons.lang3.StringUtils;

/**
 * Manager for the EVRYTHNG API.
 *
 */
public class ApiManager {

	private static EvrythngJacksonModule evrythngJacksonModule = null;
	private static volatile boolean classInit = false;

	private final ApiConfiguration config;

	private ThngService thngService;
	private final CollectionService collectionService;
	private ProductService productService;
	private final SearchService searchService;
	private final ApplicationService applicationService;
	private final AuthService authService;
	private final ScanService scanThngService;
	private PlaceService placeService;
	private final ProjectService projectService;
	private ActionService actionService;
	private final TimeService timeService;
	private final RedirectorService redirectorService;
	private final BatchService batchService;

	/**
	 * Creates a new {@link ApiManager} instance using the provided
	 * {@link ApiConfiguration}.
	 */
	public ApiManager(final ApiConfiguration config) {
		checkConfiguration(config);

		if (!classInit) {
			synchronized (ApiManager.class) {
				if (!classInit) {
					createEvrythngJacksonModule();
					JSONUtils.OBJECT_MAPPER.registerModule(getEvrythngJacksonModule().getModule());
					classInit = true;
				}
			}
		}
		this.config = config;
		this.collectionService = new CollectionService(this);
		this.searchService = new SearchService(this);
		this.applicationService = new ApplicationService(this);
		this.authService = new AuthService(this);
		this.scanThngService = new ScanService(this);
		this.projectService = new ProjectService(this);
		this.timeService = new TimeService(this);
		this.redirectorService = new RedirectorService(this);
		this.batchService = new BatchService(this);
		createThngService();
		createProductService();
		createPlaceService();
		createActionService();
	}

	/**
	 * Creates a new {@link ApiManager} instance using the provided
	 * {@code apiKey} for building an {@link ApiConfiguration} with default
	 * values.
	 *
	 * @param apiKey
	 *            the API key for authorization
	 */
	public ApiManager(final String apiKey) {
		this(new ApiConfiguration(apiKey));
	}

	/**
	 * Checks that the provided {@link ApiConfiguration} is valid.
	 *
	 * @param apiConfiguration
	 *            the {@link ApiConfiguration} to be verified
	 */
	protected void checkConfiguration(final ApiConfiguration apiConfiguration) {
		if (StringUtils.isBlank(apiConfiguration.getUrl())) {
			throw new IllegalStateException(String.format("URL of provided API configuration is invalid: [url=%s]", apiConfiguration.getUrl()));
		}

		if (StringUtils.isBlank(apiConfiguration.getKey())) {
			throw new IllegalStateException(String.format("API key of provided API configuration is invalid: [key=%s]", apiConfiguration.getKey()));
		}
	}

	protected void createThngService() {

		thngService = new ThngService(this, getEvrythngJacksonModule());
	}

	/**
	 * Returns a preconfigured EVRYTHNG service for accessing the <a
	 * href="https://dashboard.evrythng.com/developers/apidoc#thngs">Thngs</a> API.
	 *
	 * @see ThngService
	 */
	public ThngService thngService() {
		return this.thngService;
	}

	/**
	 * Returns a preconfigured EVRYTHNG service for accessing the <a
	 * href="https://dashboard.evrythng.com/developers/apidoc#collections">Collections
	 * </a> API.
	 *
	 * @see CollectionService
	 */
	public CollectionService collectionService() {
		return this.collectionService;
	}

	protected void createProductService() {

		productService = new ProductService(this, getEvrythngJacksonModule());
	}

	/**
	 * Returns a preconfigured EVRYTHNG service for accessing the <a
	 * href="https://dashboard.evrythng.com/developers/apidoc#products">Products</a>
	 * API.
	 *
	 * @see ProductService
	 */
	public ProductService productService() {
		return productService;
	}

	/**
	 * Returns a preconfigured EVRYTHNG service for accessing the <a
	 * href="https://dashboard.evrythng.com/developers/apidoc#search">Search</a>
	 * API.
	 *
	 * @see SearchService
	 */
	public SearchService searchService() {
		return searchService;
	}

	/**
	 * Returns a preconfigures EVRYTHNG service for accessing the <a
	 * href="https://dashboard.evrythng.com/developers/apidoc#applications">
	 * Applications</a>
	 * API.
	 *
	 * @see ApplicationService
	 */
	public ApplicationService applicationService() {
		return applicationService;
	}

	public AuthService authService() {
		return authService;
	}

	public TimeService timeService() { return timeService; }

	public RedirectorService redirectorService() {

		return redirectorService;
	}

	public BatchService batchService() {
		return batchService;
	}

	public ApiConfiguration getConfig() {
		return config;
	}

	/**
	 * Returns a preconfigured EVRYTHNG service for accessing the ScanThng API.
	 *
	 * @see ScanService
	 */
	public ScanService scanThngService(){
		return scanThngService;
	}

	public void onBuilderCreated(final Builder<?> builder) {
	}

	protected void createPlaceService() {

		placeService = new PlaceService(this);
	}

	public PlaceService placeService() {

		return placeService;
	}

	public ProjectService projectService() {

		return projectService;
	}

	protected void createActionService() {

		actionService = new ActionService(this, getEvrythngJacksonModule());
	}

	public ActionService actionService() {

		return this.actionService;
	}

	protected void createEvrythngJacksonModule() {

		setEvrythngJacksonModule(new EvrythngJacksonModuleImpl());
	}

	protected void setEvrythngJacksonModule(final EvrythngJacksonModule evrythngJacksonModule) {

		ApiManager.evrythngJacksonModule = evrythngJacksonModule;
	}

	protected EvrythngJacksonModule getEvrythngJacksonModule() {

		return evrythngJacksonModule;
	}
}
