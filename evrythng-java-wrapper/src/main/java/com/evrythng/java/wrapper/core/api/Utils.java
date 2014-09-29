/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.evrythng.java.wrapper.core.http.Status;
import com.evrythng.java.wrapper.exception.BadRequestException;
import com.evrythng.java.wrapper.exception.ConflictException;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.java.wrapper.exception.EvrythngException;
import com.evrythng.java.wrapper.exception.EvrythngUnexpectedException;
import com.evrythng.java.wrapper.exception.ForbiddenException;
import com.evrythng.java.wrapper.exception.InternalErrorException;
import com.evrythng.java.wrapper.exception.MethodNotAllowedException;
import com.evrythng.java.wrapper.exception.NotFoundException;
import com.evrythng.java.wrapper.exception.UnauthorizedException;
import com.evrythng.java.wrapper.util.JSONUtils;
import com.evrythng.thng.resource.model.exception.ErrorMessage;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Class that contains static utility methods.
 * 
 * @author Michel Yerly (my)
 **/
public class Utils {

	private static final Logger logger = LoggerFactory.getLogger(Utils.class);

	private Utils() {
		throw new IllegalStateException("This class is static.");
	}

	@SuppressWarnings("unchecked")
	public static <K> K convert(HttpResponse response, TypeReference<K> type) throws EvrythngException {

		K result = null;

		logger.debug("Performing conversion: [type={}]", type.getType());
		if (type.getType().equals(Void.class)) {
			return null;
		} else if (type.getType().equals(InputStream.class)) {
			try {
				// Retrieve response content stream and buffer data as connection may be closed before input is handled:
				result = (K) IOUtils.toBufferedInputStream(entityStream(response));
			} catch (Exception e) {
				throw new EvrythngClientException(String.format("Unable to retrieve response content stream: [type=%s, cause=%s]", type.getType(), e.getMessage()), e);
			}
		} else {
			if (type.getType().equals(HttpResponse.class)) {
				// We already have a HttpResponse, let's return it:
				result = (K) response;
			} else {
				// Retrieve response entity (as String so that it can be outputted in case of exception):
				String entity = entityString(response);

				if (type.getType().equals(String.class)) {
					result = (K) entity;
				} else {
					try {
						result = JSONUtils.read(entity, type);
					} catch (Exception e) {
						throw new EvrythngClientException(String.format("Unable to map response entity: [type=%s, entity=%s, cause=%s]", type.getType(), entity, e.getMessage()), e);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Reads entity content stream from the provided {@link HttpResponse}.
	 * 
	 * @param response
	 * 
	 * @return the {@link HttpResponse} entity content as {@link InputStream}
	 * 
	 * @throws EvrythngClientException
	 */
	private static InputStream entityStream(HttpResponse response) throws EvrythngClientException {
		logger.debug("Reading response content stream...");

		InputStream result = null;
		try {
			HttpEntity entity = response.getEntity();
			result = entity.getContent();
		} catch (Exception e) {
			// Convert to custom exception:
			throw new EvrythngClientException("Error while reading response content stream!", e);
		}
		return result;
	}

	/**
	 * Reads entity content from the provided {@link HttpResponse}.
	 * 
	 * @param response
	 * 
	 * @return the {@link HttpResponse} entity content as {@link String}
	 * 
	 * @throws EvrythngClientException
	 */
	private static String entityString(HttpResponse response) throws EvrythngClientException {
		logger.debug("Reading response entity...");

		String result = null;
		try {
			result = IOUtils.toString(entityStream(response));
		} catch (Exception e) {
			// Convert to custom exception:
			throw new EvrythngClientException(String.format("Error while reading response entity! [type=%s]", String.class), e);
		}
		return result;
	}

	/**
	 * Asserts {@code expected} {@link Status} against the provided
	 * {@link HttpResponse}. If {@code actual} response {@link Status} does not
	 * match {@code expected} one, then response entity
	 * will be mapped to an {@link ErrorMessage} instance and an exception will
	 * be thrown.
	 * 
	 * @param response
	 *            the {@link HttpResponse} holding a valid status code
	 * @param expected
	 *            the expected response status code
	 * 
	 * @throws EvrythngException
	 *             if provided {@code response} {@link Status} does not match
	 *             {@code expected} one
	 */
	public static void assertStatus(HttpResponse response, Status expected) throws EvrythngException {
		Status actual = Status.fromStatusCode(response.getStatusLine().getStatusCode());
		if (actual == null) {
			throw new EvrythngUnexpectedException(new ErrorMessage(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Unknown status code " + response.getStatusLine().getStatusCode()));
		}
		logger.debug("Checking response status: [expected={}, actual={}]", expected.getStatusCode(), actual.getStatusCode());

		if (!actual.equals(expected)) {
			logger.debug("Unexpected response status!");

			// Map entity to ErrorMessage:
			String entity = entityString(response);
			ErrorMessage message = null;
			try {
				logger.debug("Mapping response to ErrorMessage: [entity={}]", entity);
				// API should always return an ErrorMessage as JSON:
				message = JSONUtils.read(entity, new TypeReference<ErrorMessage>() {
				});
			} catch (Exception e) {
				throw new EvrythngClientException("Unable to retrieve ErrorMessage from response!", e);
			}

			// Handle unexpected status:
			switch (actual.getFamily()) {
				case CLIENT_ERROR:
					switch (actual) {
						case BAD_REQUEST:
							throw new BadRequestException(message);
						case UNAUTHORIZED:
							throw new UnauthorizedException(message);
						case FORBIDDEN:
							throw new ForbiddenException(message);
						case NOT_FOUND:
							throw new NotFoundException(message);
						case METHOD_NOT_ALLOWED:
							throw new MethodNotAllowedException(message);
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
	}
}
