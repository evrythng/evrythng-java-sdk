/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store;

/**
 * Abstract. Ways to notify about task progress
 */
abstract class TaskNotificationWay implements Task.Notification.Way {
	
	private Type type;

	@Override
	public final void setType(final Type type) {

		this.type = type;
	}

	@Override
	public final Type getType() {

		return type;
	}
}
