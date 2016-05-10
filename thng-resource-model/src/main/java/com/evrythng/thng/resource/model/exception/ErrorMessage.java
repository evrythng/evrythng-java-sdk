/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.thng.resource.model.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This defines the format of an error message as sent by the API. E.g., in JSON
 * such a message looks like: {@code{"status": 201, "errors":["message", "...", "moreInfo" : URL]}}
 *
 * @author Dominique Guinard (domguinard)
 */
public class ErrorMessage {

	private static final String MORE_INFO_ROOT = "https://dashboard.evrythng.com/developers/apidoc";
	/**
	 * HTTP status
	 */
	private int status;
	/**
	 * Human-friendly error(s) description(s)
	 */
	private List<String> errors = new ArrayList<>();
	private String moreInfo = MORE_INFO_ROOT;

	/**
	 * Creates a new ErrorMessage.
	 */
	public ErrorMessage() {

	}

	/**
	 * Creates a new ErrorMessage.
	 */
	public ErrorMessage(final int status) {

		this.status = status;
	}

	/**
	 * Creates a new ErrorMessage containing a single human-friendly description
	 * message.
	 */
	public ErrorMessage(final int status, final String error) {

		this(status, Collections.singletonList(error));
	}

	/**
	 * Creates a new ErrorMessage containing a single human-friendly description
	 * message.
	 *
	 * @param moreInfo Page or anchor in the documentation for more information e.g., #search
	 */
	public ErrorMessage(final int code, final String error, final String moreInfo) {

		this(code, Collections.singletonList(error), "");
	}

	/**
	 * Creates a new error message containing several errors.
	 */
	public ErrorMessage(final int code, final List<String> errors) {

		this(code, errors, "");
	}

	/**
	 * @param moreInfo Page or anchor in the documentation for more information e.g., #search
	 */
	public ErrorMessage(final int status, final List<String> errors, final String moreInfo) {

		this.status = status;
		this.errors = errors;
		this.moreInfo = String.format("%s%s", MORE_INFO_ROOT, moreInfo);
	}

	public int getStatus() {

		return status;
	}

	public void setStatusCode(final int statusCode) {

		this.status = statusCode;
	}

	public String getMoreInfo() {

		return moreInfo;
	}

	public void setMoreInfo(final String moreInfo) {

		this.moreInfo = moreInfo;
	}

	public List<String> getErrors() {

		return errors;
	}

	public void setErrors(final List<String> errors) {

		this.errors = errors;
	}

	@JsonIgnore
	public String errorsAsString() {

		return StringUtils.join(errors, "," + System.lineSeparator());
	}
}
