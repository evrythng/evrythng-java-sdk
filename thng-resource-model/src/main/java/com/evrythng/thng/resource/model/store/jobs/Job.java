/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.jobs;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Job<PROGRESS extends Progress, TYPE, OPTION_TYPE extends Enum<OPTION_TYPE>> extends DurableResourceModel implements WithProgress<PROGRESS>, WithStatusHistory, WithOptions<OPTION_TYPE>, WithCompletionDate, WithFailedOperationsAmount {

	private static final long serialVersionUID = 2316724847629568170L;
	public static final String FIELD_STATUS = "status";
	private List<NewStatus> history;
	public static final String FIELD_HISTORY = "history";
	private PROGRESS progress;
	public static final String FIELD_PROGRESS = "progress";
	private Long completedAt;
	public static final String FIELD_COMPLETED_AT = "completedAt";
	private Long failedOperationsAmount;
	public static final String FIELD_FAILED_OPERATIONS_AMOUNT = "failedOperationsAmount";
	private TYPE type;
	public static final String FIELD_TYPE = "type";
	private List<JobOption<OPTION_TYPE>> options;
	public static final String FIELD_OPTIONS = "options";

	public final TYPE getType() {

		return type;
	}

	public final void setType(final TYPE type) {

		this.type = type;
	}

	@Override
	public final Status getStatus() {

		return history != null ? history.get(history.size() - 1).getStatus() : null;
	}

	@Override
	public final List<NewStatus> getHistory() {

		return history != null ? new ArrayList<>(history) : null;
	}

	@Override
	public final void setHistory(final List<NewStatus> history) {

		this.history = history != null ? new ArrayList<>(history) : null;
	}

	@Override
	public final void addHistory(final NewStatus... history) {

		if (this.history == null) {
			this.history = new ArrayList<>();
		}
		this.history.addAll(Arrays.asList(history));
	}

	@Override
	public final PROGRESS getProgress() {

		return progress;
	}

	@Override
	public final void setProgress(final PROGRESS progress) {

		this.progress = progress;
	}

	@Override
	public final void setFailedOperationsAmount(final Long failedOperationsAmount) {

		this.failedOperationsAmount = failedOperationsAmount;
	}

	@Override
	public final Long getFailedOperationsAmount() {

		return failedOperationsAmount;
	}

	@Override
	public final void setCompletedAt(final Long completedAt) {

		this.completedAt = completedAt;
	}

	@Override
	public final Long getCompletedAt() {

		return completedAt;
	}

	@Override
	public final List<JobOption<OPTION_TYPE>> getOptions() {

		return options != null ? new ArrayList<>(options) : null;
	}

	@Override
	public final void setOptions(final List<JobOption<OPTION_TYPE>> options) {

		this.options = options != null ? new ArrayList<>(options) : null;
	}
}