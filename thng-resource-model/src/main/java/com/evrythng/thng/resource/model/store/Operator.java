/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.commons.EnumUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Map;

/**
 * Model representation for <em>operator</em>.
 */
public class Operator extends AbstractUser {

	public enum AuthProvider {
		GOOGLE("google");

		private final String name;

		private static final Map<String, AuthProvider> names;

		AuthProvider(final String name) {

			this.name = name;
		}

		static {
			names = EnumUtils.createNames(values());
		}

		@JsonValue
		@Override
		public String toString() {

			return name;
		}

		@JsonCreator
		public static AuthProvider fromString(final String name) {

			return EnumUtils.fromString(names, name);
		}
	}

	private static final long serialVersionUID = 2282241536928517609L;
	private OperatorStatus operatorStatus;
	private String registrationCode;

	private Integer loginAttempts;
	private Long authBlockedUntil;

	private Boolean tfaEnabled;

	private AuthProvider authProvider;

	public Boolean getTfaEnabled() {
		return tfaEnabled;
	}

	public void setTfaEnabled(final Boolean tfaEnabled) {
		this.tfaEnabled = tfaEnabled;
	}

	public OperatorStatus getOperatorStatus() {

		return operatorStatus;
	}

	public void setOperatorStatus(final OperatorStatus operatorStatus) {

		this.operatorStatus = operatorStatus;
	}

	public void setRegistrationCode(final String registrationCode) {

		this.registrationCode = registrationCode;
	}

	public String getRegistrationCode() {

		return registrationCode;
	}

	public Integer getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(final Integer loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public Long getAuthBlockedUntil() {

		return authBlockedUntil;
	}

	public void setAuthBlockedUntil(final Long authBlockedUntil) {
		this.authBlockedUntil = authBlockedUntil;
	}

	public AuthProvider getAuthProvider() {
		return authProvider;
	}

	public void setAuthProvider(final AuthProvider authProvider) {
		this.authProvider = authProvider;
	}
}
