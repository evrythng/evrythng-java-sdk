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
	// TODO _MS_
	private Progress progress;
	// private ResourceLocation resourceLocation;
	private Notification.Policy notificationPolicy;
	public static final String FIELD_NOTIFICATION_POLICY = "notificationPolicy";

	private Long completedAt;


//	public static final class ResourceLocation {
//
//		private String path;
//		private String id;
//
//		public ResourceLocation(final String id, final String path) {
//
//			this.id = id;
//			this.path = path;
//		}
//
//		public ResourceLocation() {
//
//		}
//	}

	public static final class Progress {

		// TODO _MS_ percentage should be an object too
		private Double percentage;

		// TODO _MS_ add more

		public Progress(final Double percentage) {

			this.percentage = percentage;
		}

		public Progress() {

		}
	}

	public static final class Notification {

		public static class Policy {

			public Policy() {

			}

			public Policy(Way singleWay) {
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

	public Progress getProgress() {

		return progress;
	}

	public void setProgress(final Progress progress) {

		this.progress = progress;
	}

//	public ResourceLocation getResourceLocation() {
//
//		return resourceLocation;
//	}
//
//	public void setResourceLocation(final ResourceLocation resourceLocation) {
//
//		this.resourceLocation = resourceLocation;
//	}

	public Long getCompletedAt() {

		return completedAt;
	}

	public void setCompletedAt(final Long completedAt) {

		this.completedAt = completedAt;
	}
}
