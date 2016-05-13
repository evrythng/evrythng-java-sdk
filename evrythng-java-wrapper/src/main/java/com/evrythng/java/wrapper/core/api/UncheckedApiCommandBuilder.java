/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.api;

import com.evrythng.java.wrapper.core.http.HttpMethodBuilder.MethodBuilder;
import com.evrythng.java.wrapper.core.http.Status;
import com.evrythng.java.wrapper.exception.EvrythngException;
import com.evrythng.thng.commons.config.ApiConfiguration;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.HttpResponse;

import java.io.InputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Generic API command builder.
 */
@SuppressWarnings("rawtypes")
public class UncheckedApiCommandBuilder<T, B extends UncheckedApiCommandBuilder> {

	private final ApiCommand<?> command;

	/**
	 * @param methodBuilder  the {@link MethodBuilder} used for creating the
	 *                       request
	 * @param uri            the {@link URI} holding the absolute URL
	 * @param responseStatus the expected response {@link Status}
	 * @param responseType   the native type to which the {@link HttpResponse} will be
	 *                       mapped to
	 */
	public <X> UncheckedApiCommandBuilder(final MethodBuilder<?> methodBuilder, final URI uri, final Status responseStatus, final TypeReference<X> responseType) {

		this.command = new ApiCommand<>(methodBuilder, uri, responseStatus, responseType);
	}

	/**
	 * Sets a query parameter or removes it if {@code value} equals {@code null}
	 * .
	 *
	 * @param name  the query parameter name
	 * @param value the query parameter value
	 * @return the current {@code B} instance
	 */
	@SuppressWarnings("unchecked")
	public B queryParam(final String name, final String value) {

		if (value != null) {
			command.setQueryParam(name, value);
		} else {
			command.removeQueryParam(name);
		}
		return (B) this;
	}

	@SuppressWarnings("unchecked")
	public B placeHolder(final Boolean placeHolder) {

		if (placeHolder != null) {
			command.setQueryParam("placeHolder", String.valueOf(placeHolder));
		} else {
			command.setQueryParam("placeHolder", "both");
		}
		return (B) this;
	}

	/**
	 * Sets a query parameter or removes it if the {@code value} equals
	 * {@code null}.
	 *
	 * @param qpv the name and the value of the query parameter.
	 * @return the current {@code B} instance
	 */
	public B queryParam(final QueryParamValue qpv) {

		return queryParam(qpv.getKey(), qpv.getValue());
	}

	/**
	 * Sets a multi-valued query parameter or removes it if {@code value} equals
	 * {@code null}.
	 *
	 * @param name  parameter name
	 * @param value parameter values or {@code null}
	 * @return the current {@code B} instance
	 */
	@SuppressWarnings("unchecked")
	public B queryParam(final String name, final List<String> value) {

		if (value != null) {
			command.setQueryParam(name, value);
		} else {
			command.removeQueryParam(name);
		}
		return (B) this;
	}

	/**
	 * Sets a multi-valued query parameter or removes it if {@code value} equals
	 * {@code null}.
	 *
	 * @param name   parameter name
	 * @param values parameter values or {@code null}
	 * @return the current {@code B} instance
	 */
	@SuppressWarnings("unchecked")
	public B queryParamList(final String name, final List<String> values) {

		if (values != null) {
			command.setQueryParam(name, concatenateList(values));
		} else {
			command.removeQueryParam(name);
		}
		return (B) this;
	}

	/**
	 * Sets a multi-valued query parameter or removes it if {@code value} equals
	 * {@code null}.
	 *
	 * @param name   parameter name
	 * @param values parameter values or {@code null}
	 * @return the current {@code B} instance
	 */
	public B queryParamList(final String name, final String... values) {

		return queryParamList(name, values == null ? null : Arrays.asList(values));
	}

	private String concatenateList(final List<String> values) {

		StringBuilder builder = new StringBuilder();
		for (String value : values) {
			builder.append(value);
			builder.append(",");
		}
		builder.setLength(Math.max(builder.length() - 1, 0));
		return builder.toString();
	}

	/**
	 * Sets the provided query parametes.
	 *
	 * @param params a map name/value entries
	 * @return the current {@code B} instance
	 * @see #queryParam(String, String)
	 */
	@SuppressWarnings("unchecked")
	public B queryParams(final Map<String, String> params) {

		for (Entry<String, String> entry : params.entrySet()) {
			queryParam(entry.getKey(), entry.getValue());
		}
		return (B) this;
	}

	/**
	 * Sets a request header or removes it if {@code value} equals {@code null}.
	 *
	 * @param name  request header name
	 * @param value the request header value
	 * @return the current {@code B} instance
	 */
	@SuppressWarnings("unchecked")
	public B header(final String name, final String value) {

		if (value != null) {
			command.setHeader(name, value);
		} else {
			command.removeHeader(name);
		}
		return (B) this;
	}

	/**
	 * Sets the value of the {@code Accept} HTTP header.
	 *
	 * @param mediaType a valid media type for the {@code Accept} HTTP header
	 * @return the current {@code B} instance
	 */
	public B accept(final String mediaType) {

		return header(ApiConfiguration.HTTP_HEADER_ACCEPT, mediaType);
	}

	/**
	 * Executes the current command and maps the {@link HttpResponse} entity to
	 * {@code T} specified by {@link ApiCommand#responseType}.
	 *
	 * @return the {@link HttpResponse} entity mapped to {@code T}
	 * @see ApiCommand#execute()
	 */
	public <X> X execute() throws EvrythngException {

		return (X) command.execute();
	}

	/**
	 * Executes the current command and returns the {@link HttpResponse} entity
	 * content as {@link String}.
	 *
	 * @return the {@link HttpResponse} entity content as {@link String}
	 * @see ApiCommand#content()
	 */
	public String content() throws EvrythngException {

		return command.content();
	}

	/**
	 * Executes the current command and returns the native {@link HttpResponse}.
	 *
	 * @return the {@link HttpResponse} resulting from the request
	 * @see ApiCommand#request()
	 */
	public HttpResponse request() throws EvrythngException {

		return command.request();
	}

	/**
	 * Executes the current command and returns the {@link HttpResponse} content
	 * {@link InputStream}.
	 *
	 * @return the {@link InputStream} of the {@link HttpResponse}
	 * @see ApiCommand#stream()
	 */
	public InputStream stream() throws EvrythngException {

		return command.stream();
	}

	/**
	 * @return {@link ApiCommand} instance
	 */
	public <X> ApiCommand<X> getCommand() {

		return (ApiCommand<X>) command;
	}
}
