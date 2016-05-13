/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core.http;

/**
 * Selection of HTTP status codes used by the EVRYTHNG API (see
 * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10">HTTP/1.1 documentation</a> for the complete
 * list.
 * 
 * Inspired from: javax.ws.rs.core.Response.Status
 * 
 * @author Pedro De Almeida (almeidap)
 */
public enum Status {
	/**
	 * 200 OK, see
	 * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.2.1">HTTP/1.1 documentation</a>.
	 */
	OK(200, "OK"),
	/**
	 * 201 Created, see
	 * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.2.2">HTTP/1.1 documentation</a>.
	 */
	CREATED(201, "Created"),
	/**
	 * 202 Accepted, see
	 * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.2.3">HTTP/1.1 documentation</a>.
	 */
	ACCEPTED(202, "Accepted"),
	/**
	 * 204 No Content, see
	 * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.2.5">HTTP/1.1 documentation</a>
	 */
	NO_CONTENT(204, "No Content"),
	/**
	 * 301 Moved Permanently, see
	 * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.3.2">HTTP/1.1 documentation</a>
	 */
	MOVED_PERMANENTLY(301, "Moved Permanently"),
	/**
	 * 307 Temporary Redirect, see
	 * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.3.8">HTTP/1.1 documentation</a>.
	 */
	TEMPORARY_REDIRECT(307, "Temporary Redirect"),
	/**
	 * 400 Bad Request, see
	 * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.1">HTTP/1.1 documentation</a>.
	 */
	BAD_REQUEST(400, "Bad Request"),
	/**
	 * 401 Unauthorized, see
	 * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.2">HTTP/1.1 documentation</a>.
	 */
	UNAUTHORIZED(401, "Unauthorized"),
	/**
	 * 403 Forbidden, see
	 * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.4">HTTP/1.1 documentation</a>.
	 */
	FORBIDDEN(403, "Forbidden"),
	/**
	 * 404 Not Found, see
	 * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.5">HTTP/1.1 documentation</a>.
	 */
	NOT_FOUND(404, "Not Found"),
	/**
	 * 405 Method Not Allowed, see <a href="http://en.wikipedia.org/wiki/List_of_HTTP_status_codes#405">List of HTTP status
	 * codes</a>.
	 */
	METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
	/**
	 * 406 Not Acceptable, see
	 * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.7">HTTP/1.1 documentation</a>.
	 */
	NOT_ACCEPTABLE(406, "Not Acceptable"),
	/**
	 * 409 Conflict, see
	 * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.10">HTTP/1.1 documentation</a>.
	 */
	CONFLICT(409, "Conflict"),
	/**
	 * 413 Request Entity Too Large
	 *
	 * @see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.14">HTTP/1.1 documentation</a>.
	 */
	REQUEST_ENTITY_TOO_LARGE(413, "Request Entity Too Large"),
	/**
	 * 429 Too Many Requests, see
	 * <a href="http://tools.ietf.org/html/rfc6585#section-4">Additional HTTP Status Codes</a>.
	 */
	TOO_MANY_REQUESTS(429, "Too Many Requests"),
	/**
	 * 500 Internal Server Error, see
	 * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.5.1">HTTP/1.1 documentation</a>.
	 */
	INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
	/**
	 * 503 Service Unavailable, see
	 * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.5.4">HTTP/1.1 documentation</a>.
	 */
	SERVICE_UNAVAILABLE(503, "Service Unavailable");

	private final int code;
	private final String reason;
	private final Family family;

	/**
	 * An enumeration representing the class of status code. Family is used
	 * here since class is overloaded in Java.
	 */
	public enum Family {
		INFORMATIONAL, SUCCESSFUL, REDIRECTION, CLIENT_ERROR, SERVER_ERROR, OTHER
	}

	Status(final int statusCode, final String reasonPhrase) {
		this.code = statusCode;
		this.reason = reasonPhrase;
		switch (code / 100) {
			case 1:
				this.family = Family.INFORMATIONAL;
				break;
			case 2:
				this.family = Family.SUCCESSFUL;
				break;
			case 3:
				this.family = Family.REDIRECTION;
				break;
			case 4:
				this.family = Family.CLIENT_ERROR;
				break;
			case 5:
				this.family = Family.SERVER_ERROR;
				break;
			default:
				this.family = Family.OTHER;
				break;
		}
	}

	/**
	 * Get the class of status code
	 * 
	 * @return the class of status code
	 */
	public Family getFamily() {
		return family;
	}

	/**
	 * Get the associated status code
	 * 
	 * @return the status code
	 */
	public int getStatusCode() {
		return code;
	}

	/**
	 * Get the reason phrase
	 * 
	 * @return the reason phrase
	 */
	public String getReasonPhrase() {
		return toString();
	}

	/**
	 * Get the reason phrase
	 * 
	 * @return the reason phrase
	 */
	@Override
	public String toString() {
		return reason;
	}

	/**
	 * Convert a numerical status code into the corresponding Status
	 * 
	 * @param statusCode the numerical status code
	 * @return the matching Status or null is no matching Status is defined
	 */
	public static Status fromStatusCode(final int statusCode) {
		for (Status s : Status.values()) {
			if (s.code == statusCode) {
				return s;
			}
		}
		return null;
	}
}
