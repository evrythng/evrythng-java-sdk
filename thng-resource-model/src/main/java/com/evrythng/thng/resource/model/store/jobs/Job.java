/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.jobs;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Job<PROGRESS extends Progress> extends DurableResourceModel {

	private static final long serialVersionUID = 2316724847629568170L;
	private List<Status> history;
	private PROGRESS progress;

	public final Status getStatus() {

		return history != null ? history.get(history.size() - 1) : null;
	}

	public final List<Status> getHistory() {

		return history != null ? new ArrayList<>(history) : null;
	}

	public final void setHistory(final List<Status> history) {

		this.history = history != null ? new ArrayList<>(history) : null;
	}

	public final void addHistory(final Status... history) {

		if (this.history == null) {
			this.history = new ArrayList<>();
		}
		this.history.addAll(Arrays.asList(history));
	}

	public final PROGRESS getProgress() {

		return progress;
	}

	public final void setProgress(final PROGRESS progress) {

		this.progress = progress;
	}
}