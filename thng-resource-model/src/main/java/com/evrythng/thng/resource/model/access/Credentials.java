/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.access;

/**
 * Exchanged information during registration / validation / authentication
 */
public class Credentials {

	/**
	 * Value of getStatus()
	 */
	public static final String STATUS_ACTIVE = "active";
	public static final String STATUS_INACTIVE = "inactive";
	public static final String STATUS_ANONYMOUS = "anonymous";
	private String evrythngUser;
	private String activationCode;
	private String email;
	private String password;
	private String status;
	private String evrythngApiKey;

	public Credentials() {
		// nop
	}

	public Credentials(final String email, final String password) {

		this.email = email;
		this.password = password;
	}

	/**
	 * @return the evrythngUser (userId)
	 */
	public String getEvrythngUser() {

		return evrythngUser;
	}

	/**
	 * @param evrythngUser the evrythngUser (userId) to set
	 */
	public void setEvrythngUser(final String evrythngUser) {

		this.evrythngUser = evrythngUser;
	}

	/**
	 * @return the activationCode
	 */
	public String getActivationCode() {

		return activationCode;
	}

	/**
	 * @param activationCode the activationCode to set
	 */
	public void setActivationCode(final String activationCode) {

		this.activationCode = activationCode;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {

		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(final String email) {

		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {

		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(final String password) {

		this.password = password;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {

		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(final String status) {

		this.status = status;
	}

	/**
	 * @return the evrythngApiKey
	 */
	public String getEvrythngApiKey() {

		return evrythngApiKey;
	}

	/**
	 * @param evrythngApiKey the evrythngApiKey to set
	 */
	public void setEvrythngApiKey(final String evrythngApiKey) {

		this.evrythngApiKey = evrythngApiKey;
	}
}
