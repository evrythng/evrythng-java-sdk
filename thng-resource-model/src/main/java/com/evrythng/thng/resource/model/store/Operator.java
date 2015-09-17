/*
 * (c) Copyright 2014 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import java.util.Date;

/**
 * Model representation for <em>operator</em>.
 */
public class Operator extends AbstractUser {

	private static final long serialVersionUID = 2282241536928517609L;
	private OperatorStatus operatorStatus;
	private String registrationCode;

	private Integer loginAttempts;
	private java.util.Date authBlockedUntil;

	private Boolean tfaEnabled;

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

	public Date getAuthBlockedUntil() {

		return authBlockedUntil;
	}

	public void setAuthBlockedUntil(final Date authBlockedUntil) {
		this.authBlockedUntil = authBlockedUntil;
	}
}
