/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.action.jobs.creation;

import com.evrythng.thng.resource.model.store.action.Action;

import java.util.Arrays;
import java.util.List;

public final class CreateActionJob extends ActionJob<CreateActionJob.OptionType> {

	private static final long serialVersionUID = -6679521183634195240L;

	public CreateActionJob() {

		setType(ActionJob.Type.ACTION_CREATION);
	}

	public enum Target {
		THNGS
	}

	public enum OptionType {
		SINGLE_RESOURCE_NOTIFICATION, SINGLE_RESOURCE_REACTIONS
	}

	private Action payload;
	public static final String FIELD_PAYLOAD = "payload";
	private Target target;
	public static final String FIELD_TARGET = "target";
	private Input input;
	public static final String FIELD_INPUT = "input";

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

	@Override
	public final List<OptionType> availableOptionTypes() {

		return Arrays.asList(OptionType.values());
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
