/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store;

/**
 * TODO write javadoc here
 */
abstract class TaskNotificationWay implements Task.Notification.Way {
	
	private Type type;

	@Override
	public final void setType(final Type type) {

		this.type = type;
	}

	@Override
	public final Type type() {

		return type;
	}
}
