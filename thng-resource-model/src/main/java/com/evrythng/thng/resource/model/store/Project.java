/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;
import com.evrythng.thng.resource.model.core.DurationModel;
import com.evrythng.thng.resource.model.core.validation.OnCreation;
import com.evrythng.thng.resource.model.core.validation.annotations.Description;
import com.evrythng.thng.resource.model.core.validation.annotations.Duration;
import com.evrythng.thng.resource.model.core.validation.annotations.Name;
import com.evrythng.thng.resource.model.core.validation.annotations.ShortDomains;
import org.apache.commons.collections.list.SetUniqueList;
import org.hibernate.validator.constraints.URL;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Model representation for <em>projects</em>.
 */
public class Project extends DurableResourceModel {

	private static final long serialVersionUID = -2362864476419100132L;
	@Name
	@NotNull(groups = OnCreation.class)
	private String name;
	@Description
	private String description;
	@URL
	private String imageUrl;
	@Valid
	@Duration
	private DurationModel duration;
	@ShortDomains
	private List<String> shortDomains;

	/**
	 * @return the name
	 */
	public String getName() {

		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {

		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {

		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {

		this.description = description;
	}

	/**
	 * @return the url of the image
	 */
	public String getImageUrl() {

		return imageUrl;
	}

	/**
	 * @param imageUrl the url of the image to set
	 */
	public void setImageUrl(final String imageUrl) {

		this.imageUrl = imageUrl;
	}

	/**
	 * @return the startsAt
	 */
	public Long getStartsAt() {

		return duration != null ? duration.getStartsAt() : null;
	}

	/**
	 * @param startsAt the startsAt to set
	 */
	public void setStartsAt(final Long startsAt) {

		if (startsAt != null && duration == null) {
			duration = new DurationModel();
		}
		duration.setStartsAt(startsAt);
	}

	/**
	 * @return the endsAt
	 */
	public Long getEndsAt() {

		return duration != null ? duration.getEndsAt() : null;
	}

	/**
	 * @param endsAt the endsAt to set
	 */
	public void setEndsAt(final Long endsAt) {

		if (endsAt != null && duration == null) {
			duration = new DurationModel();
		}
		duration.setEndsAt(endsAt);
	}

	public List<String> getShortDomains() {

		return shortDomains;
	}

	public void setShortDomains(final List<String> shortDomains) {

		this.shortDomains = shortDomains != null ? SetUniqueList.decorate(new ArrayList(shortDomains)) : null;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder("Project{");
		sb.append("name='").append(name).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", imageUrl='").append(imageUrl).append('\'');
		sb.append(", startsAt=").append(duration != null ? duration.getStartsAt() : null);
		sb.append(", endsAt=").append(duration != null ? duration.getEndsAt() : null);
		sb.append('}');
		return sb.toString();
	}
}
