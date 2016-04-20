/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.api.AcceptedResourceResponse;
import com.evrythng.java.wrapper.core.api.ApiCommand;
import com.evrythng.java.wrapper.core.api.QueryParamValue;
import com.evrythng.java.wrapper.core.api.TypedResponseWithEntity;
import com.evrythng.java.wrapper.core.http.Status;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.java.wrapper.exception.EvrythngException;
import com.evrythng.java.wrapper.util.URIBuilder;
import com.evrythng.thng.commons.config.ApiConfiguration;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.codec.binary.Base64InputStream;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.pcollections.PVector;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Base definition for EVRYTHNG API services.
 */
public class EvrythngServiceBase {

	private static final boolean BASE64_ENCODE = true;
	private static final int     BASE64_ENCODE_NO_LINE_LENGTH = 0;
	private static final byte[]  BASE64_ENCODE_NO_LINE_SEPARATOR = new byte[0];

	private final ApiManager api;
	private final ApiConfiguration config;

	/**
	 * Creates a new instance of {@link EvrythngServiceBase} using the provided
	 * {@link ApiManager}.
	 *
	 * @param api the {@link ApiManager} for accessing the API service.
	 */
	public EvrythngServiceBase(final ApiManager api) {

		this.api = api;
		this.config = api.getConfig();
	}

	protected final <T> Builder<Iterator<PVector<T>>> iterator(final String relativePath, final TypeReference<List<T>> pageType) throws EvrythngClientException {

		EvrythngApiBuilder.UncheckedBuilder<Iterator<PVector<T>>> builder = EvrythngApiBuilder.iterate(config.getKey(), absoluteUri(relativePath), Status.OK, pageType);

		Builder<Iterator<PVector<T>>> adapter = new BuilderUncheckedAdapter<>(builder);
		onBuilderCreated(adapter);
		return adapter;
	}

	private static final class BuilderUncheckedAdapter<TYPE> implements Builder<TYPE> {

		private final EvrythngApiBuilder.UncheckedBuilder<TYPE> internal;

		private BuilderUncheckedAdapter(final EvrythngApiBuilder.UncheckedBuilder<TYPE> internal) {

			this.internal = internal;
		}

		@Override
		public final TypedResponseWithEntity<TYPE> executeWithResponse() throws EvrythngException {

			return internal.executeWithResponse();
		}

		@Override
		public final Builder<TYPE> queryParam(final String name, final String value) {

			internal.queryParam(name, value);
			return this;
		}

		@Override
		public final Builder<TYPE> placeHolder(final Boolean placeHolder) {

			internal.placeHolder(placeHolder);
			return this;
		}

		@Override
		public final Builder<TYPE> queryParam(final QueryParamValue qpv) {

			internal.queryParam(qpv);
			return this;
		}

		@Override
		public final Builder<TYPE> queryParam(final String name, final List<String> values) {

			internal.queryParam(name, values);
			return this;
		}

		@Override
		public final Builder<TYPE> queryParamList(final String name, final List<String> values) {

			internal.queryParamList(name, values);
			return this;
		}

		@Override
		public final Builder<TYPE> queryParamList(final String name, final String... values) {

			internal.queryParamList(name, values);
			return this;
		}

		@Override
		public final Builder<TYPE> queryParams(final Map<String, String> params) {

			internal.queryParams(params);
			return this;
		}

		@Override
		public final Builder<TYPE> header(final String name, final String value) {

			internal.header(name, value);
			return this;
		}

		@Override
		public final Builder<TYPE> accept(final String mediaType) {

			internal.accept(mediaType);
			return this;
		}

		@Override
		public final TYPE execute() throws EvrythngException {

			return internal.execute();
		}

		@Override
		public final String content() throws EvrythngException {

			return internal.content();
		}

		@Override
		public final HttpResponse request() throws EvrythngException {

			return internal.request();
		}

		@Override
		public final InputStream stream() throws EvrythngException {

			return internal.stream();
		}

		@Override
		public final ApiCommand<TYPE> getCommand() {

			return internal.getCommand();
		}

		@Override
		public final Builder<TYPE> apiKey(final String apiKey) {

			internal.apiKey(apiKey);
			return this;
		}

		@Override
		public final Builder<TYPE> search(final String pattern) {

			internal.search(pattern);
			return this;
		}

		@Override
		public final Builder<TYPE> withScopes(final boolean withScopes) {

			internal.withScopes(withScopes);
			return this;
		}

		@Override
		public final Builder<TYPE> withPagination(final int page, final int perPage) {

			internal.withPagination(page, perPage);
			return this;
		}

		// TODO _MS_ remove it!
		@Override
		@Deprecated
		public final Builder<TYPE> page(final int page) {

			internal.page(page);
			return this;
		}

		@Override
		public final Builder<TYPE> perPage(final int perPage) {

			internal.perPage(perPage);
			return this;
		}

		@Override
		public final Builder<TYPE> from(final long from) {

			internal.from(from);
			return this;
		}

		@Override
		public final Builder<TYPE> from(final String from) {

			internal.from(from);
			return this;
		}

		@Override
		public final Builder<TYPE> from(final ApiConfiguration.QueryKeyword queryKeyword) {

			internal.from(queryKeyword);
			return this;
		}

		@Override
		public final Builder<TYPE> to(final long to) {

			internal.to(to);
			return this;
		}

		@Override
		public final Builder<TYPE> to(final String to) {

			internal.to(to);
			return this;
		}

		@Override
		public final Builder<TYPE> to(final ApiConfiguration.QueryKeyword queryKeyword) {

			internal.to(queryKeyword);
			return this;
		}

		@Override
		@Deprecated
		public final Builder<TYPE> app(final String appId) {

			internal.app(appId);
			return this;
		}

		@Override
		public final Builder<TYPE> userScope(final Iterable<String> scope) {

			internal.userScope(scope);
			return this;
		}

		@Override
		public final Builder<TYPE> userScopeAll() {

			internal.userScopeAll();
			return this;
		}

		@Override
		public final Builder<TYPE> ids(final List<String> ids) {

			internal.ids(ids);
			return this;
		}

		@Override
		public final Builder<TYPE> filter(final String filter) {

			internal.filter(filter);
			return this;
		}

		@Override
		public final Builder<TYPE> project(final String projectId) {

			internal.project(projectId);
			return this;
		}

		@Override
		@Deprecated
		public final int count() throws EvrythngException {

			return internal.count();
		}

		@Override
		public final Result<TYPE> list() throws EvrythngException {

			return internal.list();
		}

		@Override
		public final String jsonp(final String callback) throws EvrythngException {

			return internal.jsonp(callback);
		}
	}

	/**
	 * Returns a preconfigured {@link Builder} for executing POST requests.
	 * Expects
	 * 201 (created) return code
	 *
	 * @param relativePath the relative path of the API endpoint. It will be appended to
	 *                     {@link ApiConfiguration#getUrl()} in
	 *                     order to build the absolute endpoint URL.
	 * @param data         the content data that will be associated with the POST request
	 * @param type         the {@link TypeReference} for mapping the {@link HttpResponse}
	 *                     entity.
	 * @return a preconfigured {@link Builder} for executing POST requests
	 * @see EvrythngApiBuilder#post(String, URI, Object, Status, TypeReference)
	 */
	public <T> Builder<T> post(final String relativePath, final Object data, final TypeReference<T> type) throws EvrythngClientException {

		return post(relativePath, data, Status.CREATED, type);
	}

	/**
	 * Returns a preconfigured {@link Builder} for executing POST requests.
	 *
	 * @param relativePath the relative path of the API endpoint. It will be appended to
	 *                     {@link ApiConfiguration#getUrl()} in
	 *                     order to build the absolute endpoint URL.
	 * @param data         the content data that will be associated with the POST request
	 * @param expected     The expected return status
	 * @param type         the {@link TypeReference} for mapping the {@link HttpResponse}
	 *                     entity.
	 * @return a preconfigured {@link Builder} for executing POST requests
	 * @see EvrythngApiBuilder#post(String, URI, Object, Status, TypeReference)
	 */
	public <T> Builder<T> post(final String relativePath, final Object data, final Status expected, final TypeReference<T> type) throws EvrythngClientException {

		Builder<T> builder = EvrythngApiBuilder.post(config.getKey(), absoluteUri(relativePath), data, expected, type);
		onBuilderCreated(builder);
		return builder;
	}

	/**
	 * Returns a preconfigured {@link Builder} for uploading file via POST requests
	 *
	 * @param relativePath the relative path of the API endpoint. It will be appended to
	 *                     {@link ApiConfiguration#getUrl()} in
	 *                     order to build the absolute endpoint URL.
	 * @param file         file
	 * @param type         the {@link TypeReference} for mapping the {@link HttpResponse} entity
	 * @return a preconfigured {@link Builder} for executing POST requests
	 * @see #postMultipart(String, File, Status, TypeReference)
	 */
	public <T> Builder<T> postMultipart(final String relativePath, final File file, final TypeReference<T> type) throws EvrythngClientException {

		return postMultipart(relativePath, file, Status.CREATED, type);
	}

	/**
	 * Returns a preconfigured {@link Builder} for uploading file via POST
	 * requests
	 *
	 * @param relativePath the relative path of the API endpoint. It will be appended to
	 *                     {@link ApiConfiguration#getUrl()} in
	 *                     order to build the absolute endpoint URL.
	 * @param file         file
	 * @param expected     expected return {@link Status}
	 * @param type         the {@link TypeReference} for mapping the {@link HttpResponse} entity
	 * @return a preconfigured {@link Builder} for executing POST requests
	 */
	public <T> Builder<T> postMultipart(final String relativePath, final File file, final Status expected, final TypeReference<T> type) throws EvrythngClientException {

		Builder<T> builder = EvrythngApiBuilder.postMultipart(config.getKey(), absoluteUri(relativePath), file, expected, type);
		onBuilderCreated(builder);
		return builder;
	}


	/**
	 * Returns a preconfigured {@link Builder} for uploading file via PUT requests
	 *
	 * @param relativePath the relative path of the API endpoint. It will be appended to
	 *                     {@link ApiConfiguration#getUrl()} in
	 *                     order to build the absolute endpoint URL.
	 * @param file         file
	 * @param type         the {@link TypeReference} for mapping the {@link HttpResponse} entity
	 * @return a preconfigured {@link Builder} for executing PUT requests
	 * @see #postMultipart(String, File, Status, TypeReference)
	 */
	public <T> Builder<T> putMultipart(final String relativePath, final File file, final TypeReference<T> type) throws EvrythngClientException {

		return putMultipart(relativePath, file, Status.OK, type);
	}

	/**
	 * Returns a preconfigured {@link Builder} for uploading file via PUT
	 * requests
	 *
	 * @param relativePath the relative path of the API endpoint. It will be appended to
	 *                     {@link ApiConfiguration#getUrl()} in
	 *                     order to build the absolute endpoint URL.
	 * @param file         file
	 * @param expected     expected return {@link Status}
	 * @param type         the {@link TypeReference} for mapping the {@link HttpResponse} entity
	 * @return a preconfigured {@link Builder} for executing PUT requests
	 */
	public <T> Builder<T> putMultipart(final String relativePath, final File file, final Status expected, final TypeReference<T> type) throws EvrythngClientException {

		Builder<T> builder = EvrythngApiBuilder.putMultipart(config.getKey(), absoluteUri(relativePath), file, expected, type);
		onBuilderCreated(builder);
		return builder;
	}


	public Builder<AcceptedResourceResponse> postAsynchronously(final String relativePath, Object data, Pattern pattern) throws EvrythngClientException {
		Builder<AcceptedResourceResponse> builder = EvrythngApiBuilder.postAsynchronously(config.getKey(), absoluteUri(relativePath), data, pattern);
		onBuilderCreated(builder);
		return builder;
	}

//	public <T> Builder<AcceptedResourceResponse> postAsynchronouslyWithCallback(final String relativePath, final Object data, final Pattern pattern, final String topic, final Class<T> notificationClass, final EvrythngApiBuilder.MqttCallback<T> callback) throws EvrythngClientException {
//
//		Builder<AcceptedResourceResponse> builder = EvrythngApiBuilder.postAsynchronously(config.getKey(), absoluteUri(relativePath), data, pattern, config.getMqttUrl(), topic, notificationClass, callback);
//		onBuilderCreated(builder);
//		return builder;
//	}

	/**
	 * Returns a preconfigured {@link Builder} for executing GET requests.
	 *
	 * @param relativePath the relative path of the API endpoint. It will be appended to
	 *                     {@link ApiConfiguration#getUrl()} in
	 *                     order to build the absolute endpoint URL.
	 * @param type         the {@link TypeReference} for mapping the {@link HttpResponse}
	 *                     entity.
	 * @return a preconfigured {@link Builder} for executing GET requests
	 * @see EvrythngApiBuilder#get(String, URI, Status, TypeReference)
	 */
	public <T> Builder<T> get(final String relativePath, final TypeReference<T> type) throws EvrythngClientException {

		Builder<T> builder = EvrythngApiBuilder.get(config.getKey(), absoluteUri(relativePath), Status.OK, type);
		onBuilderCreated(builder);
		return builder;
	}

	/**
	 * Returns a preconfigured {@link Builder} for executing PUT requests.
	 *
	 * @param relativePath the relative path of the API endpoint. It will be appended to
	 *                     {@link ApiConfiguration#getUrl()} in
	 *                     order to build the absolute endpoint URL.
	 * @param data         the content data that will be associated with the PUT request
	 * @param expected     the expected Status code of the future request. Will be
	 *                     checked.
	 * @param type         the {@link TypeReference} for mapping the {@link HttpResponse}
	 *                     entity.
	 * @return a preconfigured {@link Builder} for executing PUT requests
	 * @see EvrythngApiBuilder#post(String, URI, Object, Status, TypeReference)
	 */
	public <T> Builder<T> put(final String relativePath, final Object data, final Status expected, final TypeReference<T> type) throws EvrythngClientException {

		Builder<T> builder = EvrythngApiBuilder.put(config.getKey(), absoluteUri(relativePath), data, expected, type);
		onBuilderCreated(builder);
		return builder;
	}

	/**
	 * Returns a preconfigured {@link Builder} for executing PUT requests.
	 *
	 * @param relativePath the relative path of the API endpoint. It will be appended to
	 *                     {@link ApiConfiguration#getUrl()} in
	 *                     order to build the absolute endpoint URL.
	 * @param data         the content data that will be associated with the PUT request
	 * @param type         the {@link TypeReference} for mapping the {@link HttpResponse}
	 *                     entity.
	 * @return a preconfigured {@link Builder} for executing PUT requests
	 * @see EvrythngApiBuilder#put(String, URI, Object, Status, TypeReference)
	 */
	public <T> Builder<T> put(final String relativePath, final Object data, final TypeReference<T> type) throws EvrythngClientException {

		return put(relativePath, data, Status.OK, type);
	}

	/**
	 * Returns a preconfigured {@link Builder} for executing PUT requests.
	 * The reference return type is Long, and will contain the amount of updated
	 * documents.
	 *
	 * @param relativePath the relative path of the API endpoint. It will be appended to
	 *                     {@link ApiConfiguration#getUrl()} in
	 *                     order to build the absolute endpoint URL.
	 * @param data         the content data that will be associated with the PUT request
	 * @return a preconfigured {@link Builder} for executing PUT requests
	 * @see EvrythngApiBuilder#putMultiple(String, URI, Object, Status)
	 */
	public Builder<Long> putMultiple(final String relativePath, final Object data) throws EvrythngClientException {

		Builder<Long> builder = EvrythngApiBuilder.putMultiple(config.getKey(), absoluteUri(relativePath), data, Status.NO_CONTENT);
		onBuilderCreated(builder);
		return builder;
	}

	/**
	 * Returns a preconfigured {@link Builder} for executing DELETE requests.
	 *
	 * @param relativePath the relative path of the API endpoint. It will be appended to
	 *                     {@link ApiConfiguration#getUrl()} in
	 *                     order to build the absolute endpoint URL.
	 * @return a preconfigured {@link Builder} for executing DELETE requests
	 * @see EvrythngApiBuilder#post(String, URI, Object, Status, TypeReference)
	 */
	public Builder<Boolean> delete(final String relativePath) throws EvrythngClientException {

		Builder<Boolean> builder = EvrythngApiBuilder.delete(config.getKey(), absoluteUri(relativePath), Status.OK);
		onBuilderCreated(builder);
		return builder;
	}

	/**
	 * Returns a preconfigured {@link Builder} for executing bulk DELETE
	 * requests.
	 *
	 * @param relativePath the relative path of the API endpoint. It will be appended to
	 *                     {@link ApiConfiguration#getUrl()} in
	 *                     order to build the absolute endpoint URL.
	 * @return a preconfigured {@link Builder} for executing DELETE requests
	 * @see EvrythngApiBuilder#deleteMultiple(String, URI, Status)
	 */
	public Builder<Long> deleteMultiple(final String relativePath) throws EvrythngClientException {

		Builder<Long> builder = EvrythngApiBuilder.deleteMultiple(config.getKey(), absoluteUri(relativePath), Status.OK);
		onBuilderCreated(builder);
		return builder;
	}

	/**
	 * Builds an absolute {@link URI} using the provided {@code relativePath}
	 * and the predefined {@link ApiConfiguration#getUrl()}.
	 *
	 * @param relativePath the relative path that will be appended to
	 *                     {@link ApiConfiguration#getUrl()} in order to build the
	 *                     absolute endpoint URL.
	 * @return the absolute endpoint {@link URI}
	 */
	protected URI absoluteUri(final String relativePath) throws EvrythngClientException {

		String path = relativePath.startsWith("/") ? relativePath : String.format("/%s", relativePath);
		return URIBuilder.fromUri(String.format("%s%s", config.getUrl(), path)).build();
	}

	protected String mqttUrl() throws EvrythngClientException {

		return config.getMqttUrl();
	}

	public ApiConfiguration getConfig() {

		return config;
	}

	protected void onBuilderCreated(final Builder<?> builder) {

		api.onBuilderCreated(builder);
	}

	protected static String urlEncodePathPart(final String s) {

		try {
			return URLEncoder.encode(s, "UTF-8").replace("+", "%20");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	protected String encodeBase64(final InputStream image, final String mime) throws IOException {

		Base64InputStream b64is = null;
		StringWriter sw = null;
		try {
			// Base64 encoding WITHOUT line separators
			b64is = new Base64InputStream(image, BASE64_ENCODE, BASE64_ENCODE_NO_LINE_LENGTH, BASE64_ENCODE_NO_LINE_SEPARATOR);
			sw = new StringWriter();
			IOUtils.copy(b64is, sw);
			return mime + "," + sw.toString();
		} finally {
			IOUtils.closeQuietly(b64is);
			IOUtils.closeQuietly(sw);
		}
	}
}
