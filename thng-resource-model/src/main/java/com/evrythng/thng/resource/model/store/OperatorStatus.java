/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.commons.EnumUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.Map;

/**
 * This document represents the status of an operator.
 */
public class OperatorStatus implements Serializable {

	public enum Status {
		ACTIVE("active"), INACTIVE("inactive"), BLOCKED("blocked");
		private final String value;
		private static final Map<String, Status> byValue;

		static {
			byValue = EnumUtils.createNames(values());
		}

		Status(final String value) {

			this.value = value;
		}

		@JsonValue
		@Override
		public String toString() {

			return value;
		}

		/**
		 * Search for {@code Status} by value.
		 *
		 * @param value status value
		 * @return {@code Status} or {@code null} if value is {@code null}
		 * @throws IllegalArgumentException if no enum found for the specified value
		 */
		@JsonCreator
		public static Status fromString(final String value) {

			return EnumUtils.fromString(byValue, value);
		}
	}

	private static final long serialVersionUID = 7677492009804434412L;
	private Status status;
	public static final String FIELD_STATUS = "status";
	private Long activationDate;
	private String activationCode;
	public static final String FIELD_ACTIVATION_CODE = "activationCode";

	public OperatorStatus(final String activationCode) {

		this.activationCode = activationCode;
	}

	public OperatorStatus() {

	}

	public Status getStatus() {

		return status;
	}

	public void setStatus(final Status status) {

		this.status = status;
	}

	public Long getActivationDate() {

		return activationDate;
	}

	public void setActivationDate(final Long activationDate) {

		this.activationDate = activationDate;
	}

	public String getActivationCode() {

		return activationCode;
	}

	public void setActivationCode(final String activationCode) {

		this.activationCode = activationCode;
	}
}
