/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.util;

import com.evrythng.java.wrapper.exception.EvrythngClientException;
import org.apache.commons.collections.map.MultiValueMap;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public final class URIBuilder {

	private final String baseUri;

	private MultiValueMap parameters = new MultiValueMap();

	private URIBuilder(final String baseUri) {
		this.baseUri = baseUri;
	}

	/**
	 * Creates a URIBuilder with a base URI string as the starting point
	 */
	public static URIBuilder fromUri(final String baseUri) {
		return new URIBuilder(baseUri);
	}

	/**
	 * Adds a query parameter to the URI
	 */
	public URIBuilder queryParam(final String name, final String value) {
		parameters.put(name, value);
		return this;
	}

	/**
	 * Adds a query parameters to the URI
	 */
	public URIBuilder queryParams(final MultiValueMap params) {
		parameters.putAll(params);
		return this;
	}

	/**
	 * Builds the URI
	 *
	 * @throws EvrythngClientException
	 */
	@SuppressWarnings("unchecked")
	public URI build() throws EvrythngClientException {
		try {
			StringBuilder builder = new StringBuilder();
			Set<Entry<String, List<String>>> entrySet = parameters.entrySet();
			for (Iterator<Entry<String, List<String>>> entryIt = entrySet.iterator(); entryIt.hasNext();) {
				Entry<String, List<String>> entry = entryIt.next();
				String name = entry.getKey();
				List<String> values = entry.getValue();
				for (Iterator<String> valueIt = values.iterator(); valueIt.hasNext();) {
					String value = valueIt.next();
					builder.append(formEncode(name)).append("=");
					if (value != null) {
						builder.append(formEncode(value));
					}
					if (valueIt.hasNext()) {
						builder.append("&");
					}
				}
				if (entryIt.hasNext()) {
					builder.append("&");
				}
			}

			String queryDelimiter = "?";
			if (URI.create(baseUri).getQuery() != null) {
				queryDelimiter = "&";
			}
			return new URI(baseUri + (builder.length() > 0 ? queryDelimiter + builder.toString() : ""));
		} catch (URISyntaxException e) {
			// Convert to custom exception:
			throw new EvrythngClientException("Unable to build URI: Bad URI syntax", e);
		}
	}

	private String formEncode(final String data) {
		try {
			return URLEncoder.encode(data, "UTF-8");
		} catch (UnsupportedEncodingException wontHappen) {
			throw new IllegalStateException(wontHappen);
		}
	}
}
