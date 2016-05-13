/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
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
