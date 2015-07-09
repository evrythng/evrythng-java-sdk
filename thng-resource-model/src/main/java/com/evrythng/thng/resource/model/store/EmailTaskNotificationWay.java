/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store;

/**
 * Task Nofification.Way. Represent the e-mail address to send notifications to
 */
public class EmailTaskNotificationWay extends TaskNotificationWay {

	private String address;
	public static final String FIELD_ADDRESS = "address";

	public String getAddress() {

		return address;
	}

	public void setAddress(final String address) {

		this.address = address;
	}
}
