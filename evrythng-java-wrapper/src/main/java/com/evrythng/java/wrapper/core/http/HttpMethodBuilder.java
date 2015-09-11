/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.http;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URI;

import org.apache.commons.lang3.CharEncoding;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.java.wrapper.util.JSONUtils;

/**
 * Builder for {@link HttpRequest} methods.
 */
public final class HttpMethodBuilder {

	public interface MethodBuilder<T extends HttpRequestBase> {
		T build(URI uri) throws EvrythngClientException;

		Method getMethod();
	}

	/**
	 * Enum containing Http methods
	 */
	public enum Method {
		GET, POST, PUT, DELETE
	}

	/**
	 * Private constructor, use static methods to create a {@link MethodBuilder}
	 * .
	 */
	private HttpMethodBuilder() {
	}

	/**
	 * Creates {@link MethodBuilder} for POST request
	 * @param data the content data that will be associated with the POST request
	 * @return the {@link MethodBuilder} instance
	 */
	public static MethodBuilder<HttpPost> httpPost(final Object data) {
		return new EntityMethodBuilder<HttpPost>(Method.POST) {
			@Override
			public HttpPost build(final URI uri) throws EvrythngClientException {
				HttpPost request = new HttpPost(uri);
				entity(request, data);
				return request;
			}
		};
	}

	/**
	 * Creates {@link MethodBuilder} for multipart POST request
	 * @param file file
	 * @return the {@link MethodBuilder} instance
	 */
	public static MethodBuilder<HttpPost> httpPostMultipart(final File file) {
		return new EntityMethodBuilder<HttpPost>(Method.POST) {
			@Override
			public HttpPost build(final URI uri) throws EvrythngClientException {
				HttpPost request = new HttpPost(uri);
				MultipartEntity reqEntity = new MultipartEntity();

				try {
					// Name of the file
					StringBody strBody = new StringBody(file.getName());
					reqEntity.addPart("filename", strBody);

					// File attachment
					FileBody fileBody = new FileBody(file);
					reqEntity.addPart("file", fileBody);

					request.setEntity(reqEntity);

				} catch (UnsupportedEncodingException ex) {
					throw new EvrythngClientException("Unable to build the multipart post request", ex);
				}

				return request;
			}
		};
	}


	/**
	 * Creates {@link MethodBuilder} for multipart PUT request
	 * @param file file
	 * @return the {@link MethodBuilder} instance
	 */
	public static MethodBuilder<HttpPut> httpPutMultipart(final File file) {
		return new EntityMethodBuilder<HttpPut>(Method.PUT) {
			@Override
			public HttpPut build(final URI uri) throws EvrythngClientException {
				HttpPut request = new HttpPut(uri);
				MultipartEntity reqEntity = new MultipartEntity();

				try {
					// Name of the file
					StringBody strBody = new StringBody(file.getName());
					reqEntity.addPart("filename", strBody);

					// File attachment
					FileBody fileBody = new FileBody(file);
					reqEntity.addPart("file", fileBody);

					request.setEntity(reqEntity);

				} catch (UnsupportedEncodingException ex) {
					throw new EvrythngClientException("Unable to build the multipart put request", ex);
				}

				return request;
			}
		};
	}


	/**
	 * Creates {@link MethodBuilder} for GET request
	 * @return the {@link MethodBuilder} instance
	 */
	public static MethodBuilder<HttpGet> httpGet() {
		return new MethodBuilder<HttpGet>() {
			@Override
			public HttpGet build(final URI uri) {
				return new HttpGet(uri);
			}

			@Override
			public Method getMethod() {
				return Method.GET;
			}
		};
	}

	/**
	 * Creates {@link MethodBuilder} for PUT request
	 * @param data the content data that will be associated with the PUT request
	 * @return the {@link MethodBuilder} instance
	 */
	public static MethodBuilder<HttpPut> httpPut(final Object data) {
		return new EntityMethodBuilder<HttpPut>(Method.PUT) {
			@Override
			public HttpPut build(final URI uri) throws EvrythngClientException {
				HttpPut request = new HttpPut(uri);
				entity(request, data);
				return request;
			}
		};
	}

	/**
	 * Creates {@link MethodBuilder} for DELETE request
	 * @return the {@link MethodBuilder} instance
	 */
	public static MethodBuilder<HttpDelete> httpDelete() {
		return new MethodBuilder<HttpDelete>() {
			@Override
			public HttpDelete build(final URI uri) {
				return new HttpDelete(uri);
			}

			@Override
			public Method getMethod() {
				return Method.DELETE;
			}
		};
	}

	protected abstract static class EntityMethodBuilder<E extends HttpEntityEnclosingRequestBase> implements MethodBuilder<E> {

		private Method method;

		protected EntityMethodBuilder(final Method method) {
			this.method = method;
		}

		protected void entity(final E request, final Object data) throws EvrythngClientException {
			try {
				request.setEntity(new StringEntity(JSONUtils.write(data), CharEncoding.UTF_8));
			} catch (Exception e) {
				// Convert to custom exception:
				throw new EvrythngClientException("Unable to define request entity: [data={}]", e);
			}
		}

		@Override
		public Method getMethod() {
			return method;
		}
	}
}
