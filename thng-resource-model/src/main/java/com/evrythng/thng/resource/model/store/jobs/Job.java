/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.jobs;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Job<PROGRESS extends Progress, TYPE, OPTION_TYPE extends Enum<OPTION_TYPE>> extends DurableResourceModel {

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

	public final Status getStatus() {

		return history != null ? history.get(history.size() - 1).getStatus() : null;
	}

	public final List<NewStatus> getHistory() {

		return history != null ? new ArrayList<>(history) : null;
	}

	public final void setHistory(final List<NewStatus> history) {

		this.history = history != null ? new ArrayList<>(history) : null;
	}

	public final void addHistory(final NewStatus... history) {

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

	public final Long getCompletedAt() {

		return completedAt;
	}

	public final void setCompletedAt(final Long completedAt) {

		this.completedAt = completedAt;
	}

	public final void setFailedOperationsAmount(final Long failedOperationsAmount) {

		this.failedOperationsAmount = failedOperationsAmount;
	}

	public final Long getFailedOperationsAmount() {

		return failedOperationsAmount;
	}

	public final List<JobOption<OPTION_TYPE>> getOptions() {

		return options != null ? new ArrayList<>(options) : null;
	}

	public final void setOptions(final List<JobOption<OPTION_TYPE>> options) {

		this.options = options != null ? new ArrayList<>(options) : null;
	}

	public abstract List<OPTION_TYPE> availableOptionTypes();
}