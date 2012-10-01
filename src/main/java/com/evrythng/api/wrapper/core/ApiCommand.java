package com.evrythng.api.wrapper.core;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;

import com.evrythng.api.wrapper.ApiConfiguration;
import com.evrythng.api.wrapper.core.HttpMethodBuilder.MethodBuilder;
import com.evrythng.api.wrapper.exception.BadRequestException;
import com.evrythng.api.wrapper.exception.ConflictException;
import com.evrythng.api.wrapper.exception.EvrythngClientException;
import com.evrythng.api.wrapper.exception.EvrythngUnexpectedException;
import com.evrythng.api.wrapper.exception.ForbiddenException;
import com.evrythng.api.wrapper.exception.InternalErrorException;
import com.evrythng.api.wrapper.exception.NotFoundException;
import com.evrythng.api.wrapper.exception.UnauthorizedException;
import com.evrythng.api.wrapper.util.JSONUtils;
import com.evrythng.api.wrapper.util.URIBuilder;
import com.evrythng.thng.resource.model.exception.ErrorMessage;
import com.fasterxml.jackson.core.type.TypeReference;

public class ApiCommand<T> {

	private static final Logger logger = LoggerFactory.getLogger(ApiCommand.class);

	protected LinkedMultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
	protected Map<String, String> headers = new LinkedHashMap<String, String>();

	protected String apiKey;
	protected MethodBuilder<?> methodBuilder;
	protected URI uri;
	protected Status responseStatus;
	protected TypeReference<T> responseType;

	public ApiCommand(String apiKey, MethodBuilder<?> methodBuilder, URI uri, Status responseStatus, TypeReference<T> responseType) {
		this.apiKey = apiKey;
		this.methodBuilder = methodBuilder;
		this.uri = uri;
		this.responseStatus = responseStatus;
		this.responseType = responseType;
	}

	/**
	 * Executes the current command and maps the {@link HttpResponse} entity to {@code T} specified by
	 * {@link ApiCommand#responseType}.
	 * 
	 * @see #execute(HttpClient, HttpRequestBase)
	 * @return
	 * @throws EvrythngClientException
	 * @throws InternalErrorException
	 * @throws EvrythngUnexpectedException
	 * @throws ConflictException
	 * @throws NotFoundException
	 * @throws ForbiddenException
	 * @throws UnauthorizedException
	 * @throws BadRequestException
	 */
	@SuppressWarnings("unchecked")
	public T execute() throws EvrythngClientException, BadRequestException, UnauthorizedException, ForbiddenException, NotFoundException, ConflictException, EvrythngUnexpectedException,
			InternalErrorException {
		// Create a new client:
		HttpClient client = new DefaultHttpClient();
		T result = null;

		try {
			// Execute request:
			HttpResponse response = this.execute(client);

			if (responseType.getType().equals(HttpResponse.class)) {
				// We already have a HttpResponse, let's return it:
				result = (T) response;
			} else {
				try {
					HttpEntity entity = response.getEntity();
					result = JSONUtils.read(entity.getContent(), responseType);
				} catch (Exception e) {
					// Convert to custom exception:
					throw new EvrythngClientException(String.format("Error while mapping response entity! [type=%s]", responseType.getType()), e);
				}
			}
		} finally {
			shutdown(client);
		}

		return result;
	}

	/*
	 * FIXME: check usefulness & validity of this!
	 */
	public int count() throws EvrythngClientException, BadRequestException, UnauthorizedException, ForbiddenException, NotFoundException, ConflictException, EvrythngUnexpectedException,
			InternalErrorException {
		HttpClient client = new DefaultHttpClient();
		try {
			HttpResponse response = execute(client, new HttpGet(buildUri()), Status.OK);
			Header xResultCountHeader = this.getHttpHeader(response, ApiConfiguration.HTTP_HEADER_RESULT_COUNT);
			return Integer.valueOf(xResultCountHeader.getValue());
		} finally {
			shutdown(client);
		}
	}

	public void setHeader(String key, String value) {
		logger.debug("Setting header: [key={}, value={}]", key, value);
		headers.put(key, value);
	}

	/**
	 * @param name
	 * @param value
	 */
	public void setQueryParam(String key, String value) {
		queryParams.set(key, value);
	}

	/**
	 * @param request
	 */
	protected HttpUriRequest prepare(HttpUriRequest request) {
		// Define required API headers:
		request.setHeader("Content-Type", ApiConfiguration.HTTP_CONTENT_TYPE);
		request.setHeader("Accept", ApiConfiguration.HTTP_ACCEPT_TYPE);
		request.setHeader("Authorization", apiKey);

		// Define client headers:
		for (Entry<String, String> header : headers.entrySet()) {
			request.setHeader(header.getKey(), header.getValue());
		}

		return request;
	}

	protected URI buildUri() throws EvrythngClientException {
		return URIBuilder.fromUri(uri.toString()).queryParams(queryParams).build();
	}

	protected Header getHttpHeader(HttpResponse response, String name) {
		logger.debug("Retrieving HTTP header: [name={}]", name);
		return response.getFirstHeader(name);
	}

	/**
	 * 
	 * @param client
	 * @return
	 * @throws EvrythngClientException
	 * @throws BadRequestException
	 * @throws UnauthorizedException
	 * @throws ForbiddenException
	 * @throws NotFoundException
	 * @throws ConflictException
	 * @throws EvrythngUnexpectedException
	 * @throws InternalErrorException
	 */
	protected HttpResponse execute(HttpClient client) throws EvrythngClientException, BadRequestException, UnauthorizedException, ForbiddenException, NotFoundException, ConflictException,
			EvrythngUnexpectedException, InternalErrorException {
		// Build request method:
		HttpRequestBase request = methodBuilder.build(buildUri());

		// Delegate:
		return execute(client, request, responseStatus);
	}

	/**
	 * 
	 * @param client
	 * @param request
	 * @param expectedStatus
	 * @return
	 * @throws EvrythngClientException
	 * @throws BadRequestException
	 * @throws UnauthorizedException
	 * @throws ForbiddenException
	 * @throws NotFoundException
	 * @throws ConflictException
	 * @throws EvrythngUnexpectedException
	 * @throws InternalErrorException
	 */
	protected HttpResponse execute(HttpClient client, HttpRequestBase request, Status expectedStatus) throws EvrythngClientException, BadRequestException, UnauthorizedException, ForbiddenException,
			NotFoundException, ConflictException, EvrythngUnexpectedException, InternalErrorException {
		logger.debug("Executing request: [method={}, url={}]", request.getMethod(), request.getURI().toString());

		HttpResponse response = null;
		try {
			response = client.execute(prepare(request));
		} catch (Exception e) {
			// Convert to custom exception:
			throw new EvrythngClientException("Unable to execute request!", e);
		}

		Status responseStatus = Status.fromStatusCode(response.getStatusLine().getStatusCode());
		if (!responseStatus.equals(expectedStatus)) {
			ErrorMessage message = null;
			try {
				message = JSONUtils.read(response.getEntity().getContent(), new TypeReference<ErrorMessage>() {
				});
			} catch (Exception e) {
				throw new EvrythngClientException("Unable to retrieve ErrorMessage from response!", e);
			}

			// Handle unexpected status (TODO: handle all required status family and codes):
			switch (responseStatus.getFamily()) {
				case CLIENT_ERROR:
					switch (responseStatus) {
						case BAD_REQUEST:
							throw new BadRequestException(message);
						case UNAUTHORIZED:
							throw new UnauthorizedException(message);
						case FORBIDDEN:
							throw new ForbiddenException(message);
						case NOT_FOUND:
							throw new NotFoundException(message);
						case CONFLICT:
							throw new ConflictException(message);
						default:
							throw new EvrythngUnexpectedException(message);
					}
				case SERVER_ERROR:
					throw new InternalErrorException(message);
				default:
					throw new EvrythngUnexpectedException(message);
			}
		}
		return response;
	}

	/**
	 * Shuts down the connection manager to ensure immediate deallocation of all system resources.
	 * 
	 * @param client
	 */
	protected void shutdown(HttpClient client) {
		client.getConnectionManager().shutdown();
	}
}
