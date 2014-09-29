/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64InputStream;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.http.Status;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.java.wrapper.util.URIBuilder;
import com.evrythng.thng.commons.config.ApiConfiguration;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Base definition for EVRYTHNG API services.
 **/
public class EvrythngServiceBase {

	private final ApiManager api;
	private final ApiConfiguration config;

	/**
	 * Creates a new instance of {@link EvrythngServiceBase} using the provided
	 * {@link ApiManager}.
	 * 
	 * @param api
	 *            the {@link ApiManager} for accessing the API service.
	 */
	public EvrythngServiceBase(ApiManager api) {
		this.api = api;
		this.config = api.getConfig();
	}

	/**
	 * Returns a preconfigured {@link Builder} for executing POST requests.
	 * Expects
	 * 201 (created) return code
	 * 
	 * @see EvrythngApiBuilder#post(String, URI, Object, Status, TypeReference)
	 * @param relativePath
	 *            the relative path of the API endpoint. It will be appended to
	 *            {@link ApiConfiguration#getUrl()} in
	 *            order to build the absolute endpoint URL.
	 * @param data
	 *            the content data that will be associated with the POST request
	 * @param type
	 *            the {@link TypeReference} for mapping the {@link HttpResponse}
	 *            entity.
	 * @return a preconfigured {@link Builder} for executing POST requests
	 * @throws EvrythngClientException
	 */
	public <T> Builder<T> post(String relativePath, Object data, TypeReference<T> type) throws EvrythngClientException {
		return post(relativePath, data, Status.CREATED, type);
	}

	/**
	 * Returns a preconfigured {@link Builder} for executing POST requests.
	 * 
	 * @see EvrythngApiBuilder#post(String, URI, Object, Status, TypeReference)
	 * @param relativePath
	 *            the relative path of the API endpoint. It will be appended to
	 *            {@link ApiConfiguration#getUrl()} in
	 *            order to build the absolute endpoint URL.
	 * @param data
	 *            the content data that will be associated with the POST request
	 * @param expected
	 *            The expected return status
	 * @param type
	 *            the {@link TypeReference} for mapping the {@link HttpResponse}
	 *            entity.
	 * @return a preconfigured {@link Builder} for executing POST requests
	 * @throws EvrythngClientException
	 */
	public <T> Builder<T> post(String relativePath, Object data, Status expected, TypeReference<T> type) throws EvrythngClientException {
		Builder<T> builder = EvrythngApiBuilder.post(config.getKey(), absoluteUri(relativePath), data, expected, type);
		onBuilderCreated(builder);
		return builder;
	}

	/**
	 * Returns a preconfigured {@link Builder} for executing GET requests.
	 * 
	 * @see EvrythngApiBuilder#get(String, URI, Status, TypeReference)
	 * @param relativePath
	 *            the relative path of the API endpoint. It will be appended to
	 *            {@link ApiConfiguration#getUrl()} in
	 *            order to build the absolute endpoint URL.
	 * @param type
	 *            the {@link TypeReference} for mapping the {@link HttpResponse}
	 *            entity.
	 * @return a preconfigured {@link Builder} for executing GET requests
	 * @throws EvrythngClientException
	 */
	public <T> Builder<T> get(String relativePath, TypeReference<T> type) throws EvrythngClientException {
		Builder<T> builder = EvrythngApiBuilder.get(config.getKey(), absoluteUri(relativePath), Status.OK, type);
		onBuilderCreated(builder);
		return builder;
	}

	/**
	 * Returns a preconfigured {@link Builder} for executing PUT requests.
	 * 
	 * @see EvrythngApiBuilder#post(String, URI, Object, Status, TypeReference)
	 * @param relativePath
	 *            the relative path of the API endpoint. It will be appended to
	 *            {@link ApiConfiguration#getUrl()} in
	 *            order to build the absolute endpoint URL.
	 * @param data
	 *            the content data that will be associated with the PUT request
	 * @param expected
	 *            the expected Status code of the future request. Will be
	 *            checked.
	 * @param type
	 *            the {@link TypeReference} for mapping the {@link HttpResponse}
	 *            entity.
	 * @return a preconfigured {@link Builder} for executing PUT requests
	 * @throws EvrythngClientException
	 */
	public <T> Builder<T> put(String relativePath, Object data, Status expected, TypeReference<T> type) throws EvrythngClientException {
		Builder<T> builder = EvrythngApiBuilder.put(config.getKey(), absoluteUri(relativePath), data, expected, type);
		onBuilderCreated(builder);
		return builder;
	}

	/**
	 * Returns a preconfigured {@link Builder} for executing PUT requests.
	 * 
	 * @see EvrythngApiBuilder#put(String, URI, Object, Status, TypeReference)
	 * @param relativePath
	 *            the relative path of the API endpoint. It will be appended to
	 *            {@link ApiConfiguration#getUrl()} in
	 *            order to build the absolute endpoint URL.
	 * @param data
	 *            the content data that will be associated with the PUT request
	 * @param type
	 *            the {@link TypeReference} for mapping the {@link HttpResponse}
	 *            entity.
	 * @return a preconfigured {@link Builder} for executing PUT requests
	 * @throws EvrythngClientException
	 */
	public <T> Builder<T> put(String relativePath, Object data, TypeReference<T> type) throws EvrythngClientException {
		return put(relativePath, data, Status.OK, type);
	}

	/**
	 * Returns a preconfigured {@link Builder} for executing PUT requests.
	 * The reference return type is Long, and will contain the amount of updated
	 * documents.
	 * 
	 * @see EvrythngApiBuilder#putMultiple(String, URI, Object, Status,
	 *      TypeReference)
	 * @param relativePath
	 *            the relative path of the API endpoint. It will be appended to
	 *            {@link ApiConfiguration#getUrl()} in
	 *            order to build the absolute endpoint URL.
	 * @param data
	 *            the content data that will be associated with the PUT request
	 * @return a preconfigured {@link Builder} for executing PUT requests
	 * @throws EvrythngClientException
	 */
	public Builder<Long> putMultiple(String relativePath, Object data) throws EvrythngClientException {
		Builder<Long> builder = EvrythngApiBuilder.putMultiple(config.getKey(), absoluteUri(relativePath), data, Status.NO_CONTENT);
		onBuilderCreated(builder);
		return builder;
	}

	/**
	 * Returns a preconfigured {@link Builder} for executing DELETE requests.
	 * 
	 * @see EvrythngApiBuilder#post(String, URI, Object, Status, TypeReference)
	 * @param relativePath
	 *            the relative path of the API endpoint. It will be appended to
	 *            {@link ApiConfiguration#getUrl()} in
	 *            order to build the absolute endpoint URL.
	 * @return a preconfigured {@link Builder} for executing DELETE requests
	 * @throws EvrythngClientException
	 */
	public Builder<Boolean> delete(String relativePath) throws EvrythngClientException {
		Builder<Boolean> builder = EvrythngApiBuilder.delete(config.getKey(), absoluteUri(relativePath), Status.OK);
		onBuilderCreated(builder);
		return builder;
	}

	/**
	 * Returns a preconfigured {@link Builder} for executing bulk DELETE
	 * requests.
	 * 
	 * @see EvrythngApiBuilder#deleteMultiple(String, URI, Object, Status,
	 *      TypeReference)
	 * @param relativePath
	 *            the relative path of the API endpoint. It will be appended to
	 *            {@link ApiConfiguration#getUrl()} in
	 *            order to build the absolute endpoint URL.
	 * @return a preconfigured {@link Builder} for executing DELETE requests
	 * @throws EvrythngClientException
	 */
	public Builder<Long> deleteMultiple(String relativePath) throws EvrythngClientException {
		Builder<Long> builder = EvrythngApiBuilder.deleteMultiple(config.getKey(), absoluteUri(relativePath), Status.OK);
		onBuilderCreated(builder);
		return builder;
	}

	/**
	 * Builds an absolute {@link URI} using the provided {@code relativePath}
	 * and the predefined {@link ApiConfiguration#getUrl()}.
	 * 
	 * @param relativePath
	 *            the relative path that will be appended to
	 *            {@link ApiConfiguration#getUrl()} in order to build the
	 *            absolute endpoint URL.
	 * @return the absolute endpoint {@link URI}
	 * @throws EvrythngClientException
	 */
	protected URI absoluteUri(String relativePath) throws EvrythngClientException {
		String path = relativePath.startsWith("/") ? relativePath : String.format("/%s", relativePath);
		return URIBuilder.fromUri(String.format("%s%s", config.getUrl(), path)).build();
	}

	public ApiConfiguration getConfig() {
		return config;
	}

	protected void onBuilderCreated(Builder<?> builder) {
		api.onBuilderCreated(builder);
	}

	protected static String urlEncodePathPart(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8").replace("+", "%20");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	protected String encodeBase64(InputStream image, String mime) throws IOException {

		Base64InputStream b64is = null;
		StringWriter sw = null;
		try {
			b64is = new Base64InputStream(image, true);
			sw = new StringWriter();
			IOUtils.copy(b64is, sw);
			return mime + "," + sw.toString();
		} finally {
			IOUtils.closeQuietly(b64is);
			IOUtils.closeQuietly(sw);
		}
	}
}
