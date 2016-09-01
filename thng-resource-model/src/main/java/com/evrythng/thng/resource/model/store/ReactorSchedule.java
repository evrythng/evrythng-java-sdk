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

	private String project;
	private String application;
	private Object event;
	private String function;
	private Long executeAt;
	private String cron;
	private String description;
	private Boolean enabled;

	public String getProject() {

		return project;
	}

	public void setProject(final String project) {

		this.project = project;
	}

	public String getApplication() {

		return application;
	}

	public void setApplication(final String application) {

		this.application = application;
	}

	public Object getEvent() {

		return event;
	}

	public void setEvent(final Object event) {

		this.event = event;
	}

	public String getFunction() {

		return function;
	}

	public void setFunction(final String function) {

		this.function = function;
	}

	public Long getExecuteAt() {

		return executeAt;
	}

	public void setExecuteAt(final Long executeAt) {

		this.executeAt = executeAt;
	}

	public String getCron() {

		return cron;
	}

	public void setCron(final String cron) {

		this.cron = cron;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(final String description) {

		this.description = description;
	}

	public Boolean getEnabled() {

		return enabled;
	}

	public void setEnabled(final Boolean enabled) {

		this.enabled = enabled;
	}

	@Override
	public String toString() {

		return "ReactorSchedule{" +
				"project='" + project + '\'' +
				", application='" + application + '\'' +
				", event=" + event +
				", function='" + function + '\'' +
				", executeAt=" + executeAt +
				", cron='" + cron + '\'' +
				", description='" + description + '\'' +
				", enabled=" + enabled +
				'}';
	}
}
