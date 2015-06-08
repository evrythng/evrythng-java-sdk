/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

import java.util.Optional;

/**
 * Model representation for long <em>tasks</em> to distribute between machines.
 */
public class Task<R> extends DurableResourceModel {

	private static final long serialVersionUID = -9117128840439882175L;
	// TODO _MS_
	private Progress progress;
	private Optional<ResourceLocation> resourceLocation;

	public static final class ResourceLocation {

		private String path;
		private String id;

		public ResourceLocation(final String id, final String path) {

			this.id = id;
			this.path = path;
		}

		public ResourceLocation() {

		}
	}

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
}
