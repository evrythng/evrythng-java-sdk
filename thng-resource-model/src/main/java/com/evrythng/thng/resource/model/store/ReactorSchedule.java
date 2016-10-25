/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

/**
 * REST model representation for reactor schedule.
 */
public class ReactorSchedule extends DurableResourceModel {

	private static final long serialVersionUID = 2312479445439758160L;
	private Object event;
	private String function;
	private Long executeAt;
	private String cron;
	private String description;
	private Boolean enabled;

	/**
	 * Retrieves event object which will be supplied as a parameter in a function executed by schedule.
	 *
	 * @return instance of {@link Object}.
	 */
	public Object getEvent() {

		return event;
	}

	/**
	 * Sets event object which will be supplied as a parameter in a function executed by schedule.
	 *
	 * @param event event to set.
	 */
	public void setEvent(final Object event) {

		this.event = event;
	}

	/**
	 * Retrieves optional function name which will be running by schedule.
	 *
	 * @return instance of {@link String}.
	 */
	public String getFunction() {

		return function;
	}

	/**
	 * Sets optional function name which will be running by schedule.
	 *
	 * @param function function name to set.
	 */
	public void setFunction(final String function) {

		this.function = function;
	}

	/**
	 * Retrieves unix time (milliseconds since Epoch, ISO 8601) to trigger function execution.
	 *
	 * @return instance of {@link Long}.
	 */
	public Long getExecuteAt() {

		return executeAt;
	}

	/**
	 * Sets unix time when function execution will be triggered. Either this parameter or {@code cron} may be specified.
	 *
	 * @param executeAt date and time to trigger function execution, milliseconds since Epoch, ISO 8601.
	 */
	public void setExecuteAt(final Long executeAt) {

		this.executeAt = executeAt;
	}

	/**
	 * Retrieves cron expression defining schedule of repeatable function executions.
	 */
	public String getCron() {

		return cron;
	}

	/**
	 * Sets cron expression defining schedule of repeatable function execution. Either this parameter or {@code executeAt}
	 * may be specified.
	 *
	 * @param cron cron expression.
	 */
	public void setCron(final String cron) {

		this.cron = cron;
	}

	/**
	 * Retrieves optional description of a reactor schedule.
	 *
	 * @return instance of {@link String}.
	 */
	public String getDescription() {

		return description;
	}

	/**
	 * Sets optional description of a reactor schedule.
	 *
	 * @param description description to set.
	 */
	public void setDescription(final String description) {

		this.description = description;
	}

	/**
	 * Retrieves whether current reactor schedule is enabled.
	 *
	 * @return instance of {@link Boolean}.
	 */
	public Boolean getEnabled() {

		return enabled;
	}

	/**
	 * Enables or disables current reactor schedule. Reactor schedule is enabled by default.
	 *
	 * @param enabled {@code true} to enable reactor schedule and {@code false} to disable it.
	 */
	public void setEnabled(final Boolean enabled) {

		this.enabled = enabled;
	}

	@Override
	public String toString() {

		return "ReactorSchedule{" +
				"event=" + event +
				", function='" + function + '\'' +
				", executeAt=" + executeAt +
				", cron='" + cron + '\'' +
				", description='" + description + '\'' +
				", enabled=" + enabled +
				'}';
	}
}
