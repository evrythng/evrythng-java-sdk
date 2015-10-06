/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;
import org.apache.commons.collections.list.SetUniqueList;

import java.util.ArrayList;
import java.util.List;

/**
 * Model representation for <em>projects</em>.
 */
public class Project extends DurableResourceModel {

	private static final long serialVersionUID = -2362864476419100132L;
	private String name;
	private String description;
	private String imageUrl;
	private Long startsAt;
	private Long endsAt;
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

		return startsAt;
	}

	/**
	 * @param startsAt the startsAt to set
	 */
	public void setStartsAt(final Long startsAt) {

		this.startsAt = startsAt;
	}

	/**
	 * @return the endsAt
	 */
	public Long getEndsAt() {

		return endsAt;
	}

	/**
	 * @param endsAt the endsAt to set
	 */
	public void setEndsAt(final Long endsAt) {

		this.endsAt = endsAt;
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
		sb.append(", startsAt=").append(startsAt);
		sb.append(", endsAt=").append(endsAt);
		sb.append('}');
		return sb.toString();
	}
}
