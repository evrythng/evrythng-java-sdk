/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.http;

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

import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.java.wrapper.util.JSONUtils;

/**
 * Builder for {@link HttpRequest} methods.
 * 
 * @author Pedro De Almeida (almeidap)
 **/
public final class HttpMethodBuilder {

	public interface MethodBuilder<T extends HttpRequestBase> {
		T build(URI uri) throws EvrythngClientException;

		Method getMethod();
	}

	public enum Method {
		GET, POST, PUT, DELETE
	}

	/**
	 * Private constructor, use static methods to create a {@link MethodBuilder}
	 * .
	 */
	private HttpMethodBuilder() {
	}

	public static MethodBuilder<HttpPost> httpPost(final Object data) {
		return new EntityMethodBuilder<HttpPost>(Method.POST) {
			@Override
			public HttpPost build(URI uri) throws EvrythngClientException {
				HttpPost request = new HttpPost(uri);
				entity(request, data);
				return request;
			}
		};
	}

	public static MethodBuilder<HttpGet> httpGet() {
		return new MethodBuilder<HttpGet>() {
			@Override
			public HttpGet build(URI uri) {
				return new HttpGet(uri);
			}

			public Method getMethod() {
				return Method.GET;
			}
		};
	}

	public static MethodBuilder<HttpPut> httpPut(final Object data) {
		return new EntityMethodBuilder<HttpPut>(Method.PUT) {
			@Override
			public HttpPut build(URI uri) throws EvrythngClientException {
				HttpPut request = new HttpPut(uri);
				entity(request, data);
				return request;
			}
		};
	}

	public static MethodBuilder<HttpDelete> httpDelete() {
		return new MethodBuilder<HttpDelete>() {
			@Override
			public HttpDelete build(URI uri) {
				return new HttpDelete(uri);
			}

			public Method getMethod() {
				return Method.DELETE;
			}
		};
	}

	protected static abstract class EntityMethodBuilder<E extends HttpEntityEnclosingRequestBase> implements MethodBuilder<E> {

		private Method method;

		public EntityMethodBuilder(Method method) {
			this.method = method;
		}

		protected void entity(E request, final Object data) throws EvrythngClientException {
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
