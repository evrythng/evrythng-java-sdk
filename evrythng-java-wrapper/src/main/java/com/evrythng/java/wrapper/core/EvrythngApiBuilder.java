/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core;

import com.evrythng.commons.Ref;
import com.evrythng.java.wrapper.core.api.ApiCommand;
import com.evrythng.java.wrapper.core.api.ApiCommandBuilder;
import com.evrythng.java.wrapper.core.api.TypedResponseWithEntity;
import com.evrythng.java.wrapper.core.api.Utils;
import com.evrythng.java.wrapper.core.api.param.AppQueryParamValue;
import com.evrythng.java.wrapper.core.api.param.CallbackQueryParamValue;
import com.evrythng.java.wrapper.core.api.param.CreationScopeQueryParamValue;
import com.evrythng.java.wrapper.core.api.param.FromQueryParamValue;
import com.evrythng.java.wrapper.core.api.param.PageQueryParamValue;
import com.evrythng.java.wrapper.core.api.param.PerPageQueryParamValue;
import com.evrythng.java.wrapper.core.api.param.QSearchQueryParamValue;
import com.evrythng.java.wrapper.core.api.param.ScopeQueryParamValue;
import com.evrythng.java.wrapper.core.api.param.ToQueryParamValue;
import com.evrythng.java.wrapper.core.api.param.UserScopeQueryParamValue;
import com.evrythng.java.wrapper.core.http.HttpMethodBuilder;
import com.evrythng.java.wrapper.core.http.HttpMethodBuilder.Method;
import com.evrythng.java.wrapper.core.http.HttpMethodBuilder.MethodBuilder;
import com.evrythng.java.wrapper.core.http.Status;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.java.wrapper.exception.EvrythngException;
import com.evrythng.thng.commons.config.ApiConfiguration;
import com.evrythng.thng.commons.config.ApiConfiguration.QueryKeyword;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URI;

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
	protected EvrythngApiBuilder() {
	}

	/**
	 * Creates a {@link Builder} for executing a {@code POST} request.
	 * 
	 * @param apiKey
	 *            the authorization token for accessing the EVRYTHNG API
	 * @param uri
	 *            the {@link URI} holding the absolute URL
	 * @param data
	 *            the content data that will be associated with the POST request
	 * @param responseStatus
	 *            the expected {@link HttpResponse} status
	 * @param responseType
	 *            the native type to which the {@link HttpResponse} will be
	 *            mapped to
	 * @return an EVRYTHNG API-ready {@link Builder}
	 */
	public static <T> Builder<T> post(String apiKey, URI uri, Object data, Status responseStatus, TypeReference<T> responseType) {
		return new Builder<T>(apiKey, HttpMethodBuilder.httpPost(data), uri, responseStatus, responseType);
	}

	/**
	 * Creates a {@link Builder} for executing a file upload via a {@code POST}
	 * request.
	 * 
	 * @param apiKey
	 * @param uri
	 * @param file
	 * @param responseStatus
	 * @param responseType
	 * @return
	 */
	public static <T> Builder<T> postMultipart(String apiKey, URI uri, File file, Status responseStatus, TypeReference<T> responseType) {
		return new Builder<T>(apiKey, HttpMethodBuilder.httpPostMultipart(file), uri, responseStatus, responseType, null);
	}

	/**
	 * Creates a {@link Builder} for executing a {@code GET} request.
	 * 
	 * @param apiKey
	 *            the authorization token for accessing the EVRYTHNG API
	 * @param uri
	 *            the {@link URI} holding the absolute URL
	 * @param responseStatus
	 *            the expected {@link HttpResponse} status
	 * @param responseType
	 *            the native type to which the {@link HttpResponse} will be
	 *            mapped to
	 * @return an EVRYTHNG API-ready {@link Builder}
	 */
	public static <T> Builder<T> get(String apiKey, URI uri, Status responseStatus, TypeReference<T> returnType) {
		return new Builder<T>(apiKey, HttpMethodBuilder.httpGet(), uri, responseStatus, returnType);
	}

	/**
	 * Creates a {@link Builder} for executing a {@code PUT} request.
	 * 
	 * @param apiKey
	 *            the authorization token for accessing the EVRYTHNG API
	 * @param uri
	 *            the {@link URI} holding the absolute URL
	 * @param data
	 *            the content data that will be associated with the POST request
	 * @param responseStatus
	 *            the expected {@link HttpResponse} status
	 * @param responseType
	 *            the native type to which the {@link HttpResponse} will be
	 *            mapped to
	 * @return an EVRYTHNG API-ready {@link Builder}
	 */
	public static <T> Builder<T> put(String apiKey, URI uri, Object data, Status responseStatus, TypeReference<T> returnType) {
		return new Builder<T>(apiKey, HttpMethodBuilder.httpPut(data), uri, responseStatus, returnType);
	}

	/**
	 * Create a {@link Builder} for executing a {@code PUT} request, expecting
	 * no result payload. But a
	 * {@link ApiConfiguration.HTTP_HEADER_RESULT_COUNT} header which
	 * contains the amount of document updated.
	 * 
	 * @param apiKey
	 *            the authorization token for accessing the EVRYTHNG API
	 * @param uri
	 *            the {@link URI} holding the absolute URL
	 * @param data
	 *            the content data that will be associated with the PUT request
	 * @param responseStatus
	 *            the expected {@link HttpResponse} status. More likely 204 No
	 *            Content
	 * @return
	 */
	public static Builder<Long> putMultiple(String apiKey, URI uri, Object data, Status responseStatus) {
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
			};
		};
	}

	/**
	 * Creates a {@link Builder} for executing a {@code DELETE} request.
	 * 
	 * @param apiKey
	 *            the authorization token for accessing the EVRYTHNG API
	 * @param uri
	 *            the {@link URI} holding the absolute URL
	 * @param responseStatus
	 *            the expected {@link HttpResponse} status
	 * @return an EVRYTHNG API-ready {@link Builder}
	 */
	public static Builder<Boolean> delete(String apiKey, URI uri, Status responseStatus) {
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
			};
		};
	}

	/**
	 * Creates a {@link Builder} for executing a bulk {@code DELETE} request,
	 * and wrap the X-result-count header as a integer response.
	 * 
	 * @param apiKey
	 *            the authorization token for accessing the EVRYTHNG API
	 * @param uri
	 *            the {@link URI} holding the absolute URL
	 * @param responseStatus
	 *            the expected {@link HttpResponse} status
	 * @return an EVRYTHNG API-ready {@link Builder}
	 */
	public static Builder<Long> deleteMultiple(String apiKey, URI uri, Status responseStatus) {
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
			};
		};
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
		 */
		private Builder(String apiKey, MethodBuilder<?> methodBuilder, URI uri, Status responseStatus, TypeReference<T> responseType) {
			this(apiKey, methodBuilder, uri, responseStatus, responseType, ApiConfiguration.HTTP_CONTENT_TYPE);
		}

		/**
		 * Private constructor, use {@link EvrythngApiBuilder} static methods
		 * for creating a {@link Builder}.
		 */
		private Builder(String apiKey, MethodBuilder<?> methodBuilder, URI uri, Status responseStatus, TypeReference<T> responseType, String contentType) {
			super(methodBuilder, uri, responseStatus, responseType);

			// Define required API metainformation:
			// header("Content-Type", ApiConfiguration.HTTP_CONTENT_TYPE);
			if (contentType != null) {
				header("Content-Type", contentType);
			}
			header("Accept", ApiConfiguration.HTTP_ACCEPT_TYPE);
			apiKey(apiKey);
		}

		public Builder<T> apiKey(String apiKey) {
			return header(ApiConfiguration.HTTP_HEADER_AUTHORIZATION, apiKey);
		}

		public Builder<T> search(String pattern) {
			return queryParam(QSearchQueryParamValue.pattern(pattern));
		}

		public Builder<T> page(int page) {
			return queryParam(PageQueryParamValue.page(page));
		}

		public Builder<T> perPage(int perPage) {
			return queryParam(PerPageQueryParamValue.perPage(perPage));
		}

		public Builder<T> from(long from) {
			return queryParam(FromQueryParamValue.from(String.valueOf(from)));
		}

		public Builder<T> from(String from) {
			return queryParam(FromQueryParamValue.from(from));
		}

		public Builder<T> from(QueryKeyword queryKeyword) {
			return queryParam(FromQueryParamValue.from(queryKeyword.toString()));
		}

		public Builder<T> to(long to) {
			return queryParam(ToQueryParamValue.to(String.valueOf(to)));
		}

		public Builder<T> to(String to) {
			return queryParam(ToQueryParamValue.to(to));
		}

		public Builder<T> to(QueryKeyword queryKeyword) {
			return queryParam(ToQueryParamValue.to(queryKeyword.toString()));
		}

		public Builder<T> app(String appId) {
			return queryParam(AppQueryParamValue.appId(appId));
		}

		public Builder<T> creationScope(Iterable<String> scope) {

			return queryParam(CreationScopeQueryParamValue.valueOf(StringUtils.join(scope, ',')));
		}

		public Builder<T> userScope(Iterable<String> scope) {

			return queryParam(UserScopeQueryParamValue.valueOf(StringUtils.join(scope, ',')));
		}

		public Builder<T> userScopeAll() {

			return queryParam(ScopeQueryParamValue.valueOf(QueryKeyword.ALL.toString()));
		}

		/**
		 * Counts the <strong>total</strong> number of elements if the current
		 * command was executed as a {@code HEAD} request.
		 * 
		 * @see ApiCommand#head(String)
		 * @return the <strong>total</strong> number of elements matching the
		 *         current request
		 * @throws EvrythngException
		 * @Deprecated Use {@link #fetchTotalCount(Ref)} instead.
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

			return new Result<T>(ret, n);
		}

		/**
		 * Executes the current command by requesting JSONP in the
		 * {@link HttpResponse} entity.
		 * 
		 * TODO: check usefulness & validity of this!
		 * 
		 * @param callback
		 *            the name of the callback function
		 * @throws EvrythngException
		 * @return the {@link HttpResponse} entity in the form of JSONP content
		 */
		public String jsonp(String callback) throws EvrythngException {
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

			public Result(R result, long totalCount) {

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
