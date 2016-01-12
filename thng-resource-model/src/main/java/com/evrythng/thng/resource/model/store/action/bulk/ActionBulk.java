/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.action.bulk;

import com.evrythng.thng.resource.model.store.action.Action;
import com.evrythng.thng.resource.model.store.jobs.Job;
import com.evrythng.thng.resource.model.store.jobs.RequestedAmountProgress;

public class ActionBulk extends Job<RequestedAmountProgress> {

	private static final long serialVersionUID = 7631007639064000811L;
	private boolean notify;
	private Content content;
	private Payload payload;
	private Action template;
	private Resource resource;

	public enum Resource {
		THNGS
	}

	public Action getTemplate() {

		return template;
	}

	public void setTemplate(final Action template) {

		this.template = template;
	}

	public boolean isNotify() {

		return notify;
	}

	public void setNotify(final boolean notify) {

		this.notify = notify;
	}

	public Content getContent() {

		return content;
	}

	public void setContent(final Content content) {

		this.content = content;
	}

	public Payload getPayload() {

		return payload;
	}

	public void setPayload(final Payload payload) {

		this.payload = payload;
	}

	public Resource getResource() {

		return resource;
	}

	public void setResource(final Resource resource) {

		this.resource = resource;
	}

}