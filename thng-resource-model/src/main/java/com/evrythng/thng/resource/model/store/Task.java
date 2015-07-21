/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

import java.util.Collections;
import java.util.List;

/**
 * Model representation for long <em>tasks</em> to distribute between machines.
 */
public class Task extends DurableResourceModel {

	private static final long serialVersionUID = -9117128840439882175L;
	// private ResourceLocation resourceLocation;
	private Notification.Policy notificationPolicy;
	public static final String FIELD_NOTIFICATION_POLICY = "notificationPolicy";
	private Long completedAt;
	public static final String FIELD_COMPLETED_AT = "completedAt";

	public static final class Notification {

		public static class Policy {

			public Policy() {

			}

			public Policy(final Way singleWay) {

				this.completion = Collections.singletonList(singleWay);
			}

			private List<Notification.Way> completion;
			public static final String FIELD_COMPLETION = "completion";

			public List<Way> getCompletion() {

				return completion;
			}

			public void setCompletion(final List<Way> completion) {

				this.completion = completion;
			}
		}

		public static interface Way {

			Way.Type getType();

			void setType(Way.Type type);

			enum Type {

				EMAIL
			}
		}
	}

	public Notification.Policy getNotificationPolicy() {

		return notificationPolicy;
	}

	public void setNotificationPolicy(final Notification.Policy notificationPolicy) {

		this.notificationPolicy = notificationPolicy;
	}

	public Long getCompletedAt() {

		return completedAt;
	}

	public void setCompletedAt(final Long completedAt) {

		this.completedAt = completedAt;
	}
}
