/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

/**
 * Model representation for <em>batches</em>.
 */
public class Batch extends DurableResourceModel {

	private static final long serialVersionUID = -4440334109484551488L;
	// TODO _MS_
	private String name;
	private String description;
	private String imageUrl;
	private Long startsAt;
	private Long endsAt;

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
