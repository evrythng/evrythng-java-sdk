/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api;

import java.io.InputStream;
import java.net.URI;
import java.security.KeyStore;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.evrythng.java.wrapper.util.LogUtils;
import com.evrythng.thng.commons.config.ApiConfiguration;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.evrythng.java.wrapper.core.http.HttpMethodBuilder;
import com.evrythng.java.wrapper.core.http.HttpMethodBuilder.Method;
import com.evrythng.java.wrapper.core.http.HttpMethodBuilder.MethodBuilder;
import com.evrythng.java.wrapper.core.http.Status;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.java.wrapper.exception.EvrythngException;
import com.evrythng.java.wrapper.util.URIBuilder;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Generic definition for API commands.
 */
public class ApiCommand<T> {

	private static final Logger logger = LoggerFactory.getLogger(ApiCommand.class);
	private MultiValueMap queryParams = new MultiValueMap();
	private Map<String, String> headers = new LinkedHashMap<>();
	private HttpParams httpParams = null;
	private MethodBuilder<?> methodBuilder;
	private URI uri;
	private Status responseStatus;
	private TypeReference<T> responseType;

	/**
	 * Creates a new instance of {@link ApiCommand}.
	 *
	 * @param methodBuilder  the {@link MethodBuilder} used for creating the
	 *                       request
	 * @param uri            the {@link URI} holding the absolute URL
	 * @param responseStatus the expected {@link HttpResponse} status
	 * @param responseType   the native type to which the {@link HttpResponse} will be
	 *                       mapped to
	 */
	public ApiCommand(final MethodBuilder<?> methodBuilder, final URI uri, final Status responseStatus, final TypeReference<T> responseType) {

		this.methodBuilder = methodBuilder;
		this.uri = uri;
		this.responseStatus = responseStatus;
		this.responseType = responseType;
	}

	/**
	 * Gets the expected response status.
	 *
	 * @return {@link Status} instance
	 */
	public Status getExpectedResponseStatus() {

		return responseStatus;
	}

	/**
	 * Gets the response type.
	 *
	 * @return {@link TypeReference} instance
	 */
	public TypeReference<T> getResponseType() {

		return responseType;
	}

	/**
	 * Gets the HTTP method.
	 *
	 * @return {@link Method} type
	 */
	public Method getMethod() {

		return methodBuilder.getMethod();
	}

	/**
	 * Executes the current command and maps the {@link HttpResponse} entity to
	 * {@code T} specified by {@link ApiCommand#responseType}.
	 *
	 * @return the {@link HttpResponse} entity mapped to {@code T}
	 * @see #execute(TypeReference)
	 */
	public T execute() throws EvrythngException {

		return execute(responseType);
	}

	/**
	 * Executes the current command and returns the {@link HttpResponse} entity
	 * content as {@link String}.
	 *
	 * @return the {@link HttpResponse} entity content as {@link String}
	 * @see #execute(TypeReference)
	 */
	public String content() throws EvrythngException {

		return execute(new TypeReference<String>() {

		});
	}

	/**
	 * Executes the current command and returns the native {@link HttpResponse}.
	 *
	 * @return the {@link HttpResponse} implied by the request
	 * @see #execute(TypeReference)
	 */
	public HttpResponse request() throws EvrythngException {

		return execute(new TypeReference<HttpResponse>() {

		});
	}

	/**
	 * Executes the current command and returns the {@link HttpResponse} entity
	 * body as {@link InputStream}.
	 *
	 * @return the {@link HttpResponse} entity as {@link InputStream}
	 * @see #execute(TypeReference)
	 */
	public InputStream stream() throws EvrythngException {

		return execute(new TypeReference<InputStream>() {

		});
	}

	/**
	 * Execute the current command and returns both {@link HttpResponse} and
	 * the entity typed. Bundled in a {@link TypedResponseWithEntity} object
	 *
	 * @return {@link HttpResponse} bundled with entity
	 */
	public TypedResponseWithEntity<T> bundle() throws EvrythngException {

		HttpClient client = httpParams == null ? new DefaultHttpClient() : new DefaultHttpClient(httpParams);
		client = wrapClient(client);
		try {
			HttpResponse response = performRequest(client, methodBuilder, responseStatus);
			T entity = Utils.convert(response, responseType);
			return new TypedResponseWithEntity<>(response, entity);
		} finally {
			shutdown(client);
		}
	}

	/**
	 * Executes the current command using the HTTP {@code HEAD} method and
	 * returns the value of the first {@link HttpResponse} {@link Header}
	 * specified by {@code headerName}. This
	 * method is usefull for obtaining
	 * metainformation about the {@link HttpResponse} implied by the request
	 * without transferring the entity-body.
	 * <p>
	 * FIXME: HEAD not supported for now, using GET instead
	 *
	 * @param headerName the {@link HttpResponse} header to be retrieved
	 * @return the value of the first retrieved {@link HttpResponse} header or
	 * null if no such header could be found.
	 * @see HttpResponse#getFirstHeader(String)
	 */
	public Header head(final String headerName) throws EvrythngException {

		HttpResponse response = execute(HttpMethodBuilder.httpGet(), new TypeReference<HttpResponse>() {

		});
		logger.debug("Retrieving first header: [name={}]", headerName);
		return response.getFirstHeader(headerName);
	}

	/**
	 * Sets (adds or overwrittes) the specified request header.
	 *
	 * @param name  the request header name
	 * @param value the request header value
	 */
	public void setHeader(final String name, final String value) {

		logger.debug("Setting header: [name={}, value={}]", name,
		             ApiConfiguration.HTTP_HEADER_AUTHORIZATION.equals(name) ? LogUtils.maskApiKey(value) : value);
		headers.put(name, value);
	}

	/**
	 * Removes the specified request header.
	 *
	 * @param name the name of the request header to be removed
	 */
	public void removeHeader(final String name) {

		logger.debug("Removing header: [name={}]", name);
		headers.remove(name);
	}

	/**
	 * Sets (adds or overwrittes) the specified query parameter.
	 *
	 * @param name  the query parameter name
	 * @param value the query parameter value
	 */
	public void setQueryParam(final String name, final String value) {
		// Ensure unicity of parameter:
		queryParams.remove(name);

		logger.debug("Setting query parameter: [name={}, value={}]", name, value);
		queryParams.put(name, value);
	}

	/**
	 * Sets (adds or overwrittes) the multi-value of specified query parameter.
	 *
	 * @param name  the query parameter name
	 * @param value the query parameter values list
	 */
	public void setQueryParam(final String name, final List<String> value) {

		logger.debug("Setting query parameter: [name={}, value={}]", name, value);
		queryParams.putAll(name, value);
	}

	/**
	 * Removes the specified query parameter.
	 *
	 * @param name the name of the query parameter to be removed
	 */
	public void removeQueryParam(final String name) {

		logger.debug("Removing query parameter: [name={}]", name);
		queryParams.remove(name);
	}

	/**
	 * Sets HTTP-specific params, {
	 *
	 * @param params {@link HttpParams} instance
	 * @see HttpClient
	 */
	public void setHttpParams(final HttpParams params) {

		logger.debug("Setting HttpParams: [{}]", params);
		this.httpParams = params;
	}

	private <K> K execute(final TypeReference<K> type) throws EvrythngException {
		// Delegate:
		return execute(methodBuilder, type);
	}

	private <K> K execute(final MethodBuilder<?> method, final TypeReference<K> type) throws EvrythngException {
		// Delegate:
		return execute(method, responseStatus, type);
	}

	private <K> K execute(final MethodBuilder<?> method, final Status expectedStatus, final TypeReference<K> type) throws EvrythngException {

		HttpClient client = httpParams == null ? new DefaultHttpClient() : new DefaultHttpClient(httpParams);
		client = wrapClient(client);
		try {
			HttpResponse response = performRequest(client, method, expectedStatus);
			return Utils.convert(response, type);
		} finally {
			shutdown(client);
		}
	}

	private HttpResponse performRequest(final HttpClient client, final MethodBuilder<?> method, final Status expectedStatus) throws EvrythngException {

		HttpResponse response;
		HttpUriRequest request = buildRequest(method);
		try {
			logger.debug(">> Executing request: [method={}, url={}]", request.getMethod(), request.getURI().toString());
			response = client.execute(request);
			logger.debug("<< Response received: [statusLine={}]", response.getStatusLine().toString());
		} catch (Exception e) {
			// Convert to custom exception:
			throw new EvrythngClientException(String.format("Unable to execute request: [uri=%s, cause=%s]", request.getURI(), e.getMessage()), e);
		}

		// Assert response status:
		Utils.assertStatus(response, expectedStatus);

		return response;
	}

	private static HttpClient wrapClient(final HttpClient base) {

		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			trustStore.load(null, null);
			SSLSocketFactory ssf = new WrapperSSLSocketFactory(trustStore);
			ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			ClientConnectionManager ccm = base.getConnectionManager();
			SchemeRegistry sr = ccm.getSchemeRegistry();
			sr.register(new Scheme("https", ssf, 443));
			return new DefaultHttpClient(ccm, base.getParams());
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * Builds and prepares the {@link HttpUriRequest}.
	 *
	 * @param method the {@link MethodBuilder} used to build the request
	 * @return the prepared {@link HttpUriRequest} for execution
	 */
	private HttpUriRequest buildRequest(final MethodBuilder<?> method) throws EvrythngClientException {

		// Build request method:
		HttpUriRequest request = method.build(buildUri());

		// Define client headers:
		for (Entry<String, String> header : headers.entrySet()) {
			request.setHeader(header.getKey(), header.getValue());
		}

		return request;
	}

	/**
	 * Builds the final {@link URI} using {@link ApiCommand#uri} as base URL and
	 * all defined {@link ApiCommand#queryParams} as query parameters.
	 *
	 * @return the absolute URI
	 */
	private URI buildUri() throws EvrythngClientException {

		return URIBuilder.fromUri(uri.toString()).queryParams(queryParams).build();
	}

	/**
	 * Shuts down the connection manager to ensure immediate deallocation of all
	 * system resources.
	 *
	 * @param client the {@link HttpClient} to shut down
	 */
	protected void shutdown(final HttpClient client) {

		client.getConnectionManager().shutdown();
	}
}
