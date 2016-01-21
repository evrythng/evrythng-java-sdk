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

	public EmailTaskNotificationWay() {

		setType(Type.EMAIL);
	}

	public String getAddress() {

		return address;
	}

	public void setAddress(final String address) {

		this.address = address;
	}

	@Override
	public boolean equals(final Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		EmailTaskNotificationWay that = (EmailTaskNotificationWay) o;

		if (address != null ? !address.equals(that.address) : that.address != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {

		return address != null ? address.hashCode() : 0;
	}
}
