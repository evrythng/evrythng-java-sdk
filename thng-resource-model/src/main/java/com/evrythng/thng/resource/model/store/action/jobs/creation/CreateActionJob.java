/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.action.jobs.creation;

import com.evrythng.thng.resource.model.store.action.Action;

public final class CreateActionJob extends ActionJob {

	private static final long serialVersionUID = -6679521183634195240L;

	public CreateActionJob() {

		setType(ActionJob.Type.ACTION_CREATION);
	}

	public enum Target {
		THNGS
	}

	private Action payload;
	private Target target;
	private Input input;

	public Input getInput() {

		return input;
	}

	public void setInput(final Input input) {

		this.input = input;
	}

	public Action getPayload() {

		return payload;
	}

	public void setPayload(final Action payload) {

		this.payload = payload;
	}

	public Target getTarget() {

		return target;
	}

	public void setTarget(final Target target) {

		this.target = target;
	}

	public abstract static class Input {

		private Type type;
		public static final String FIELD_TYPE = "type";
		private ContentType contentType;
		public static final String FIELD_CONTENT_TYPE = "contentType";

		public enum Type {
			LIST, FILE
		}

		public enum ContentType {
			EVRYTHNG_IDS, EXTERNAL_IDS, SHORT_IDS
		}

		public ContentType getContentType() {

			return contentType;
		}

		public void setContentType(final ContentType contentType) {

			this.contentType = contentType;
		}

		public Type getType() {

			return type;
		}

		public void setType(final Type type) {

			this.type = type;
		}
	}
}
