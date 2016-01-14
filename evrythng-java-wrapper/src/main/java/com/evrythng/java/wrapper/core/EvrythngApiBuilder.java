/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core;

import com.evrythng.api.wrapper.param.FilterQueryParamValue;
import com.evrythng.api.wrapper.param.IdsQueryParamValue;
import com.evrythng.java.wrapper.core.api.AcceptedResourceResponse;
import com.evrythng.java.wrapper.core.api.ApiCommand;
import com.evrythng.java.wrapper.core.api.ApiCommandBuilder;
import com.evrythng.java.wrapper.core.api.TypedResponseWithEntity;
import com.evrythng.java.wrapper.core.api.Utils;
import com.evrythng.java.wrapper.core.api.param.CallbackQueryParamValue;
import com.evrythng.java.wrapper.core.api.param.FromQueryParamValue;
import com.evrythng.java.wrapper.core.api.param.PageQueryParamValue;
import com.evrythng.java.wrapper.core.api.param.PerPageQueryParamValue;
import com.evrythng.java.wrapper.core.api.param.ProjectQueryParamValue;
import com.evrythng.java.wrapper.core.api.param.QSearchQueryParamValue;
import com.evrythng.java.wrapper.core.api.param.ScopeQueryParamValue;
import com.evrythng.java.wrapper.core.api.param.ToQueryParamValue;
import com.evrythng.java.wrapper.core.api.param.UserScopeQueryParamValue;
import com.evrythng.java.wrapper.core.api.param.WithScopesQueryParamValue;
import com.evrythng.java.wrapper.core.http.HttpMethodBuilder;
import com.evrythng.java.wrapper.core.http.HttpMethodBuilder.Method;
import com.evrythng.java.wrapper.core.http.HttpMethodBuilder.MethodBuilder;
import com.evrythng.java.wrapper.core.http.Status;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.java.wrapper.exception.EvrythngException;
import com.evrythng.java.wrapper.util.JSONUtils;
import com.evrythng.thng.commons.config.ApiConfiguration;
import com.evrythng.thng.commons.config.ApiConfiguration.QueryKeyword;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Entry-point command builder for the EVRYTHNG API.
 *
 * @author Pedro De Almeida (almeidap)
 */
public final class EvrythngApiBuilder {

	private static final Logger logger = LoggerFactory.getLogger(EvrythngApiBuilder.class);

	/**
	 * Protected constructor, use one of the static methods to obtain an
	 * instance.
	 */
	private EvrythngApiBuilder() {

	}

	/**
	 * Creates a {@link Builder} for executing a {@code POST} request.
	 *
	 * @param apiKey         the authorization token for accessing the EVRYTHNG API
	 * @param uri            the {@link URI} holding the absolute URL
	 * @param data           the content data that will be associated with the POST request
	 * @param responseStatus the expected {@link HttpResponse} status
	 * @param responseType   the native type to which the {@link HttpResponse} will be
	 *                       mapped to
	 * @return an EVRYTHNG API-ready {@link Builder}
	 */
	public static <T> Builder<T> post(final String apiKey, final URI uri, final Object data, final Status responseStatus, final TypeReference<T> responseType) {

		return new Builder<>(apiKey, HttpMethodBuilder.httpPost(data), uri, responseStatus, responseType);
	}

	/**
	 * Creates a {@link Builder} for executing a file upload via a {@code POST}
	 * request.
	 *
	 * @param apiKey         the authorization token for accessing the EVRYTHNG API
	 * @param uri            the {@link URI} holding the absolute URL
	 * @param file           file
	 * @param responseStatus the expected response {@link Status}
	 * @param responseType   the native type to which the {@link HttpResponse} will be
	 *                       mapped to
	 * @return an EVRYTHNG API-ready {@link Builder}
	 */
	public static <T> Builder<T> postMultipart(final String apiKey, final URI uri, final File file, final Status responseStatus, final TypeReference<T> responseType) {

		return new Builder<>(apiKey, HttpMethodBuilder.httpPostMultipart(file), uri, responseStatus, responseType, null);
	}


	/**
	 * Creates a {@link Builder} for executing a file upload via a {@code PUT}
	 * request.
	 *
	 * @param apiKey         the authorization token for accessing the EVRYTHNG API
	 * @param uri            the {@link URI} holding the absolute URL
	 * @param file           file
	 * @param responseStatus the expected response {@link Status}
	 * @param responseType   the native type to which the {@link HttpResponse} will be
	 *                       mapped to
	 * @return an EVRYTHNG API-ready {@link Builder}
	 */
	public static <T> Builder<T> putMultipart(final String apiKey, final URI uri, final File file, final Status responseStatus, final TypeReference<T> responseType) {

		return new Builder<>(apiKey, HttpMethodBuilder.httpPutMultipart(file), uri, responseStatus, responseType, null);
	}


	public static Builder<AcceptedResourceResponse> postAsynchronously(final String apiKey, final URI uri, final Object data, final Pattern extractor) {
		return new Builder<AcceptedResourceResponse>(apiKey, HttpMethodBuilder.httpPost(data), uri, Status.ACCEPTED, new TypeReference<AcceptedResourceResponse>() {

		}) {

			@Override
			public AcceptedResourceResponse execute() throws EvrythngException {
				// Perform request (response status code will be automatically checked):
				HttpResponse response = request();
				String location = null;
				Header header = response.getFirstHeader("location");
				String id = null;
				if (header != null) {
					location = header.getValue();
					if (location != null) {
						Matcher match = extractor.matcher(location);
						if (match.matches() && match.groupCount() > 0) {
							id = match.group(1);
						}
					}
				}
				return new AcceptedResourceResponse(id, location);
			}
		};
	}
	/**
	 * Creates a {@link Builder} for executing a {@code GET} request.
	 *
	 * @param apiKey         the authorization token for accessing the EVRYTHNG API
	 * @param uri            the {@link URI} holding the absolute URL
	 * @param responseStatus the expected {@link HttpResponse} status
	 * @param returnType     the native type to which the {@link HttpResponse} will be
	 *                       mapped to
	 * @return an EVRYTHNG API-ready {@link Builder}
	 */
	public static <T> Builder<T> get(final String apiKey, final URI uri, final Status responseStatus, final TypeReference<T> returnType) {

		return new Builder<>(apiKey, HttpMethodBuilder.httpGet(), uri, responseStatus, returnType);
	}

	/**
	 * Creates a {@link Builder} for executing a {@code PUT} request.
	 *
	 * @param apiKey         the authorization token for accessing the EVRYTHNG API
	 * @param uri            the {@link URI} holding the absolute URL
	 * @param data           the content data that will be associated with the POST request
	 * @param responseStatus the expected {@link HttpResponse} status
	 * @param returnType     the native type to which the {@link HttpResponse} will be
	 *                       mapped to
	 * @return an EVRYTHNG API-ready {@link Builder}
	 */
	public static <T> Builder<T> put(final String apiKey, final URI uri, final Object data, final Status responseStatus, final TypeReference<T> returnType) {

		return new Builder<>(apiKey, HttpMethodBuilder.httpPut(data), uri, responseStatus, returnType);
	}

	/**
	 * Create a {@link Builder} for executing a {@code PUT} request, expecting
	 * no result payload. But a
	 * {@link ApiConfiguration#HTTP_HEADER_RESULT_COUNT} header which
	 * contains the amount of document updated.
	 *
	 * @param apiKey         the authorization token for accessing the EVRYTHNG API
	 * @param uri            the {@link URI} holding the absolute URL
	 * @param data           the content data that will be associated with the PUT request
	 * @param responseStatus the expected {@link HttpResponse} status. More likely 204 No
	 *                       Content
	 * @return an EVRYTHNG API-ready {@link Builder}
	 */
	public static Builder<Long> putMultiple(final String apiKey, final URI uri, final Object data, final Status responseStatus) {

		return new Builder<Long>(apiKey, HttpMethodBuilder.httpPut(data), uri, responseStatus, new TypeReference<Long>() {

		}) {

			@Override
			public Long execute() throws EvrythngException {
				// Perform request (response status code will be automatically checked):
				HttpResponse response = request();
				Header header = response.getFirstHeader(ApiConfiguration.HTTP_HEADER_RESULT_COUNT);
				Long result = null;
				if (header != null) {
					try {
						result = Long.parseLong(header.getValue());
					} catch (NumberFormatException ex) {
						logger.warn("Invalid numeric value in header {} : {}", ApiConfiguration.HTTP_HEADER_RESULT_COUNT, header.getValue());
					}
				}
				return result;
			}
		};
	}

	/**
	 * Creates a {@link Builder} for executing a {@code DELETE} request.
	 *
	 * @param apiKey         the authorization token for accessing the EVRYTHNG API
	 * @param uri            the {@link URI} holding the absolute URL
	 * @param responseStatus the expected {@link HttpResponse} status
	 * @return an EVRYTHNG API-ready {@link Builder}
	 */
	public static Builder<Boolean> delete(final String apiKey, final URI uri, final Status responseStatus) {

		return new Builder<Boolean>(apiKey, HttpMethodBuilder.httpDelete(), uri, responseStatus, new TypeReference<Boolean>() {

		}) {

			/**
			 * {@code true} if the request has been successfully executed (i.e.
			 * returned response status code equals {@link Status#OK}),
			 * {@code false} otherwise
			 */
			@Override
			public Boolean execute() throws EvrythngException {
				// Perform request (response status code will be automatically checked):
				return request() != null;
			}
		};
	}

	/**
	 * Creates a {@link Builder} for executing a bulk {@code DELETE} request,
	 * and wrap the X-result-count header as a integer response.
	 *
	 * @param apiKey         the authorization token for accessing the EVRYTHNG API
	 * @param uri            the {@link URI} holding the absolute URL
	 * @param responseStatus the expected {@link HttpResponse} status
	 * @return an EVRYTHNG API-ready {@link Builder}
	 */
	public static Builder<Long> deleteMultiple(final String apiKey, final URI uri, final Status responseStatus) {

		return new Builder<Long>(apiKey, HttpMethodBuilder.httpDelete(), uri, responseStatus, new TypeReference<Long>() {

		}) {

			@Override
			public Long execute() throws EvrythngException {
				// Perform request (response status code will be automatically checked):
				HttpResponse response = request();
				Header header = response.getFirstHeader(ApiConfiguration.HTTP_HEADER_RESULT_COUNT);
				Long result = null;
				if (header != null) {
					try {
						result = Long.parseLong(header.getValue());
					} catch (NumberFormatException ex) {
						logger.warn("Invalid numeric value in header {} : {}", ApiConfiguration.HTTP_HEADER_RESULT_COUNT, header.getValue());
					}
				}
				return result;
			}
		};
	}

	static <T> Builder<AcceptedResourceResponse> postAsynchronously(final String apiKey, final URI uri, final Object data, final Pattern pattern, final String mqttUrl, final String topic, final Class<T> notificationClass, final MqttCallback<T> callback) {

		return new MqttListenerBuilder<>(apiKey, HttpMethodBuilder.httpPost(data), uri, Status.ACCEPTED, pattern, mqttUrl, topic, notificationClass, callback);
	}

	public interface MqttCallback<T> {

		void apply(T notified);
	}

	private static final class MqttListenerBuilder<T> extends Builder<AcceptedResourceResponse> {

		private final Pattern idLocationExtractor;
		private final String topic;
		private final String mqttUrl;
		private final String apiKey;
		private final Class<T> notificationClass;
		private final MqttCallback<T> callback;

		private MqttListenerBuilder(final String apiKey, final MethodBuilder<?> methodBuilder, final URI uri, final Status responseStatus, final Pattern idLocationExtractor, final String mqttUrl, final String topic, final Class<T> notificationClass, final MqttCallback<T> callback) {

			super(apiKey, methodBuilder, uri, responseStatus, new TypeReference<AcceptedResourceResponse>() {});
			this.idLocationExtractor = idLocationExtractor;
			this.topic = topic;
			this.mqttUrl = mqttUrl;
			this.apiKey = apiKey;
			this.notificationClass = notificationClass;
			this.callback = callback;
		}

		@Override
		public AcceptedResourceResponse execute() throws EvrythngException {

			// Perform request (response status code will be automatically checked):
			// TODO _MS_ get project
			String projectId = null;
			HttpResponse response = request();
			String location = null;
			Header header = response.getFirstHeader("location");
			String id = null;
			if (header != null) {
				location = header.getValue();
				if (location != null) {
					Matcher match = idLocationExtractor.matcher(location);
					if (match.matches() && match.groupCount() > 0) {
						id = match.group(1);
					}
				}
			}
			try {
				final MqttClient client = mqttClient(mqttUrl, apiKey, projectId);
				client.subscribe(topic);
				// TODO _MS_ introduce type
				client.setCallback(new org.eclipse.paho.client.mqttv3.MqttCallback() {

					@Override
					public void connectionLost(final Throwable cause) {

						throw new RuntimeException("MQTT connection lost", cause);
					}

					@Override
					public void messageArrived(final String topic, final MqttMessage message) throws Exception {

						T notified = JSONUtils.OBJECT_MAPPER.readValue(message.getPayload(), notificationClass);
						client.unsubscribe(topic);
						client.disconnect();
						callback.apply(notified);
					}

					@Override
					public void deliveryComplete(final IMqttDeliveryToken token) {

					}
				});
			} catch (MqttException e) {
				// TODO _MS_ create exception type
				throw new RuntimeException("Failed to subscribe through MQTT", e);
			}
			return new AcceptedResourceResponse(id, location);
		}
	}

	private static MqttClient mqttClient(final String url, final String apiKey, final String projectId) throws MqttException {

		String clientId = MqttClient.generateClientId();
		String username = "authorization";
		MqttClient client = new EVTMqttClient(url, clientId, projectId);
		MqttConnectOptions options = new MqttConnectOptions();
		options.setUserName(username);
		options.setPassword(apiKey.toCharArray());
		options.setCleanSession(true);
		client.connect(options);
		return client;
	}

	private static final class EVTMqttClient extends MqttClient {

		private final String projectId;

		private EVTMqttClient(final String serverURI, final String clientId, final String projectId) throws MqttException {

			super(serverURI, clientId);
			this.projectId = projectId;
		}

		@Override
		public void subscribe(final String[] topicFilters, final int[] qos) throws MqttException {

			if (projectId != null) {
				for (int i = 0; i < topicFilters.length; i++) {
					topicFilters[i] += "?project=" + projectId;
				}
			}
			super.subscribe(topicFilters, qos);
		}
	}

	/**
	 * Default command builder for the EVRYTHNG API.
	 *
	 * @author Pedro De Almeida (almeidap)
	 */
	public static class Builder<T> extends ApiCommandBuilder<T, Builder<T>> {

		/**
		 * Private constructor, use {@link EvrythngApiBuilder} static methods
		 * for creating a {@link Builder}.
		 *
		 * @param apiKey         the authorization token for accessing the EVRYTHNG API
		 * @param methodBuilder  the {@link MethodBuilder} used for creating the
		 *                       request
		 * @param uri            the {@link URI} holding the absolute URL
		 * @param responseStatus the expected response {@link Status}
		 * @param responseType   the native type to which the {@link HttpResponse} will be
		 *                       mapped to
		 */
		private Builder(final String apiKey, final MethodBuilder<?> methodBuilder, final URI uri, final Status responseStatus, final TypeReference<T> responseType) {

			this(apiKey, methodBuilder, uri, responseStatus, responseType, ApiConfiguration.HTTP_CONTENT_TYPE);
		}

		/**
		 * Private constructor, use {@link EvrythngApiBuilder} static methods
		 * for creating a {@link Builder}.
		 *
		 * @param apiKey         the authorization token for accessing the EVRYTHNG API
		 * @param methodBuilder  the {@link MethodBuilder} used for creating the
		 *                       request
		 * @param uri            the {@link URI} holding the absolute URL
		 * @param responseStatus the expected response {@link Status}
		 * @param responseType   the native type to which the {@link HttpResponse} will be
		 *                       mapped to
		 * @param contentType    content type
		 */
		private Builder(final String apiKey, final MethodBuilder<?> methodBuilder, final URI uri, final Status responseStatus, final TypeReference<T> responseType, final String contentType) {

			super(methodBuilder, uri, responseStatus, responseType);

			// Define required API metainformation:
			// header("Content-Type", ApiConfiguration.HTTP_CONTENT_TYPE);
			if (contentType != null) {
				header("Content-Type", contentType);
			}
			header("Accept", ApiConfiguration.HTTP_ACCEPT_TYPE);
			apiKey(apiKey);
		}

		/**
		 * @param apiKey the authorization token for accessing the EVRYTHNG API
		 * @return an EVRYTHNG API-ready {@link Builder}
		 */
		public Builder<T> apiKey(final String apiKey) {

			return header(ApiConfiguration.HTTP_HEADER_AUTHORIZATION, apiKey);
		}

		/**
		 * @param pattern "{@value QSearchQueryParamValue#NAME}" query parameter value
		 * @return an EVRYTHNG API-ready {@link Builder}
		 * @see QSearchQueryParamValue
		 */
		public Builder<T> search(final String pattern) {

			return queryParam(QSearchQueryParamValue.pattern(pattern));
		}

		/**
		 * @param withScopes "{@value WithScopesQueryParamValue#NAME}" query parameter value
		 * @return an EVRYTHNG API-ready {@link Builder}
		 * @see WithScopesQueryParamValue
		 */
		public Builder<T> withScopes(final boolean withScopes) {

			return queryParam(WithScopesQueryParamValue.NAME, String.valueOf(withScopes));
		}

		/**
		 * Combines page and perPage methods.
		 * @return an EVRYTHNG API-ready {@link Builder}
		 */
		public Builder<T> withPagination(final int page, final int perPage) {

			return page(page).perPage(perPage);
		}

		/**
		 * @param page "{@value PageQueryParamValue#NAME}" query parameter value
		 * @return an EVRYTHNG API-ready {@link Builder}
		 */
		public Builder<T> page(final int page) {

			return queryParam(PageQueryParamValue.page(page));
		}

		/**
		 * @param perPage "{@value PerPageQueryParamValue#NAME}" query parameter value
		 * @return an EVRYTHNG API-ready {@link Builder}
		 */
		public Builder<T> perPage(final int perPage) {

			return queryParam(PerPageQueryParamValue.perPage(perPage));
		}

		/**
		 * @param from "{@value FromQueryParamValue#NAME}" query parameter value
		 * @return an EVRYTHNG API-ready {@link Builder}
		 */
		public Builder<T> from(final long from) {

			return queryParam(FromQueryParamValue.from(String.valueOf(from)));
		}

		/**
		 * @param from "{@value FromQueryParamValue#NAME}" query parameter value
		 * @return an EVRYTHNG API-ready {@link Builder}
		 */
		public Builder<T> from(final String from) {

			return queryParam(FromQueryParamValue.from(from));
		}

		/**
		 * @param queryKeyword "{@value FromQueryParamValue#NAME}" query parameter value
		 * @return an EVRYTHNG API-ready {@link Builder}
		 */
		public Builder<T> from(final QueryKeyword queryKeyword) {

			return queryParam(FromQueryParamValue.from(queryKeyword.toString()));
		}

		/**
		 * @param to "{@value ToQueryParamValue#NAME}" query parameter value
		 * @return an EVRYTHNG API-ready {@link Builder}
		 */
		public Builder<T> to(final long to) {

			return queryParam(ToQueryParamValue.to(String.valueOf(to)));
		}

		/**
		 * @param to "{@value ToQueryParamValue#NAME}" query parameter value
		 * @return an EVRYTHNG API-ready {@link Builder}
		 */
		public Builder<T> to(final String to) {

			return queryParam(ToQueryParamValue.to(to));
		}

		/**
		 * @param queryKeyword "{@value ToQueryParamValue#NAME}" query parameter value
		 * @return an EVRYTHNG API-ready {@link Builder}
		 */
		public Builder<T> to(final QueryKeyword queryKeyword) {

			return queryParam(ToQueryParamValue.to(queryKeyword.toString()));
		}

		/**
		 * Does nothing. App query parameter is not used anymore.
		 * @param appId
		 * @return an EVRYTHNG API-ready {@link Builder}
		 * @deprecated Use {@value ProjectQueryParamValue#NAME} project to scope a request on a project.
		 */
		@Deprecated
		public Builder<T> app(final String appId) {

			return this;
		}

		/**
		 * @param scope "{@value UserScopeQueryParamValue#NAME}" query parameter value
		 * @return an EVRYTHNG API-ready {@link Builder}
		 */
		public Builder<T> userScope(final Iterable<String> scope) {

			return queryParam(UserScopeQueryParamValue.valueOf(StringUtils.join(scope, ',')));
		}

		/**
		 * Adds "{@value ScopeQueryParamValue#NAME}" query param with value {@link QueryKeyword#ALL}
		 *
		 * @return an EVRYTHNG API-ready {@link Builder}
		 */
		public Builder<T> userScopeAll() {

			return queryParam(ScopeQueryParamValue.valueOf(QueryKeyword.ALL.toString()));
		}

		/**
		 * @param ids "{@value IdsQueryParamValue#NAME}" query parameter value
		 * @return an EVRYTHNG API-ready {@link Builder}
		 */
		public Builder<T> ids(final List<String> ids) {

			return queryParamList(IdsQueryParamValue.NAME, ids);
		}

		/**
		 * @param filter "{@value FilterQueryParamValue#NAME}" query parameter value
		 * @return an EVRYTHNG API-ready {@link Builder}
		 */
		public Builder<T> filter(final String filter) {

			return queryParam(FilterQueryParamValue.NAME, filter);
		}

		/**
		 * @param projectId "{@value ProjectQueryParamValue#NAME}" query parameter value
		 * @return an EVRYTHNG API-ready {@link Builder}
		 */
		public Builder<T> project(final String projectId) {

			return queryParam(ProjectQueryParamValue.project(projectId));
		}

		/**
		 * Counts the <strong>total</strong> number of elements if the current
		 * command was executed as a {@code HEAD} request.
		 *
		 * @return the <strong>total</strong> number of elements matching the
		 * current request
		 * @see ApiCommand#head(String)
		 */
		@Deprecated
		public int count() throws EvrythngException {

			logger.debug("Counting total number of elements: [header={}]", ApiConfiguration.HTTP_HEADER_RESULT_COUNT);
			Header xResultCountHeader = getCommand().head(ApiConfiguration.HTTP_HEADER_RESULT_COUNT);
			return Integer.valueOf(xResultCountHeader.getValue());
		}

		/**
		 * Executes the requests and returns a result object. The result objects
		 * contains the actual object and the total count for the paginated
		 * list. This method can only be used in the context of a paginated
		 * result set, otherwise an exception is thrown.
		 *
		 * @return result object
		 */
		public Result<T> list() throws EvrythngException {

			if (getCommand().getMethod() != Method.GET) {
				throw new EvrythngClientException("The list() method is only available for GET requests.");
			}

			logger.debug("Call list. For type : {}", getCommand().getResponseType().getType());

			// Issue the command "manually" to get the response object.
			TypedResponseWithEntity<T> bundle = getCommand().bundle();
			HttpResponse response = bundle.httpResponse;
			Utils.assertStatus(response, getCommand().getExpectedResponseStatus());
			T ret = bundle.entity;

			// Parse the total result count.
			Header header = response.getFirstHeader(ApiConfiguration.HTTP_HEADER_RESULT_COUNT);
			long n;
			if (header == null) {
				throw new EvrythngClientException("The response contains no " + ApiConfiguration.HTTP_HEADER_RESULT_COUNT + " header.");
			}
			try {
				n = Long.parseLong(header.getValue());
			} catch (NumberFormatException e) {
				throw new EvrythngClientException("The response's " + ApiConfiguration.HTTP_HEADER_RESULT_COUNT + " header could not be parsed.");
			}
			logger.debug("Total number of items: {}", n);

			return new Result<>(ret, n);
		}

		/**
		 * Executes the current command by requesting JSONP in the
		 * {@link HttpResponse} entity.
		 * <p>
		 *
		 * @param callback the name of the callback function
		 * @return the {@link HttpResponse} entity in the form of JSONP content
		 */
		// TODO: check usefulness & validity of this!
		public String jsonp(final String callback) throws EvrythngException {
			// Add JSONP callback to query parameters list:
			queryParam(CallbackQueryParamValue.callback(callback));

			// Retrieve response entity content:
			String jsonp = getCommand().content();

			// Remove callback to avoid conflicts on future requests:
			queryParam(CallbackQueryParamValue.empty());

			return jsonp;
		}

		/**
		 * Class to hold the results and the total count.
		 */
		public static class Result<R> {

			private R result;
			private long totalCount;

			public Result(final R result, final long totalCount) {

				this.result = result;
				this.totalCount = totalCount;
			}

			public R getResult() {

				return result;
			}

			public long getTotalCount() {

				return totalCount;
			}
		}
	}
}
