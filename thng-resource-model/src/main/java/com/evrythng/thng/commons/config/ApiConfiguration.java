/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.commons.config;

/**
 * Parameters and values for the EVRYTHNG API configuration.
 */
public class ApiConfiguration {

	// EVRYTHNG API

	private String url = "https://api.evrythng.com";
	private String key = null;
	private String mqttUrl = "tcp://mqtt.evrythng.net:1883";

	// HTTP

	/**
	 * @deprecated 1.15
	 */
	@Deprecated
	public static final String HTTP_ACCEPT_TYPE = "application/json";

	/**
	 * @deprecated 1.15
	 */
	@Deprecated
	public static final String HTTP_CONTENT_TYPE = "application/json";

	/**
	 * @deprecated 1.15
	 */
	@Deprecated
	public static final String HTTP_HEADER_RESULT_COUNT = "x-result-count";

	/**
	 * @deprecated 1.15
	 */
	@Deprecated
	public static final String HTTP_HEADER_AUTHORIZATION = "Authorization";

	/**
	 * @deprecated 1.15
	 */
	@Deprecated
	public static final String HTTP_HEADER_ACCEPT = "Accept";

	/**
	 * @deprecated 1.15
	 */
	@Deprecated
	public static final String HTTP_HEADER_CONTENT_TYPE = "Content-Type";

	// Query Parameters

	/**
	 * @deprecated 1.15
	 */
	@Deprecated
	public static final String QUERY_PARAM_ACCESS_TOKEN = "access_token";

	/* Common */

	/**
	 * @deprecated 1.15
	 */
	@Deprecated
	public static final String QUERY_PARAM_SEARCH = "q";

	/**
	 * @deprecated 1.15
	 */
	@Deprecated
	public static final String QUERY_PARAM_PAGE = "page";

	/**
	 * @deprecated 1.15
	 */
	@Deprecated
	public static final String QUERY_PARAM_PER_PAGE = "perPage";

	/**
	 * @deprecated 1.15
	 */
	@Deprecated
	public static final String QUERY_PARAM_FROM = "from";

	/**
	 * @deprecated 1.15
	 */
	@Deprecated
	public static final String QUERY_PARAM_TO = "to";

	/**
	 * @deprecated 1.15
	 */
	@Deprecated
	public static final String QUERY_PARAM_CALLBACK = "callback";

	/**
	 * @deprecated 1.15
	 */
	@Deprecated
	public static final String QUERY_PARAM_APP = "app";

	/**
	 * @deprecated 1.15
	 */
	@Deprecated
	public static final String QUERY_PARAM_USER_SCOPE = "userScope";

	/* thng-li */

	/**
	 * @deprecated 1.15
	 */
	@Deprecated
	public static final String QUERY_PARAM_WIDTH = "w";

	/**
	 * @deprecated 1.15
	 */
	@Deprecated
	public static final String QUERY_PARAM_HEIGHT = "h";

	/**
	 * @deprecated 1.15
	 */
	@Deprecated
	public static final String QUERY_PARAM_ECL = "ecl";

	/**
	 * @deprecated 1.15
	 */
	@Deprecated
	public static final String QUERY_PARAM_FONT = "font";

	/**
	 * @deprecated 1.15
	 */
	@Deprecated
	public static final String QUERY_PARAM_TEMPLATE = "tpl";

	/**
	 * @deprecated 1.15
	 */
	@Deprecated
	public static final String QUERY_PARAM_REVERSE_LOOKUP = "evrythngId";

	/**
	 * @deprecated 1.15
	 */
	@Deprecated
	public static final int SHORT_ID_LENGTH = 8;

	public enum QueryKeyword {
		LATEST("latest"), ME("me"), ALL("all");

		private String keyword;

		QueryKeyword(String keyword) {
			this.keyword = keyword;
		}

		/*
		 * {@inheritDoc}
		 * 
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return keyword;
		}
	}

	/*
	 * Default values
	 */
	private int perPage = 30;

	public ApiConfiguration() {
	}

	public ApiConfiguration(String apiKey) {
		this.key = apiKey;
	}

	public ApiConfiguration(String apiKey, String apiUrl) {
		this.key = apiKey;
		this.url = apiUrl;
	}

	public ApiConfiguration(final String apiKey, final String apiUrl, final String mqttUrl) {

		this.key = apiKey;
		this.url = apiUrl;
		this.mqttUrl = mqttUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public String getMqttUrl() {

		return mqttUrl;
	}

	public void setMqttUrl(final String mqttUrl) {

		this.mqttUrl = mqttUrl;
	}
}
