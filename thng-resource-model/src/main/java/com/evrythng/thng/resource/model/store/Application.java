/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.access.SocialNetwork;
import com.evrythng.thng.resource.model.core.DurableResourceModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * A registered Application in the EVRYTHNG engine.
 */
public class Application extends DurableResourceModel {

	private static final long serialVersionUID = -5251229000992852717L;
	public static final String SN_FACEBOOK = "facebook";
	private String name;
	private String description;
	private String project;
	private String appApiKey;
	private Map<String, SocialNetwork> socialNetworks;
	private String defaultUrl;

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
	 * @return the socialNetworks
	 */
	public Map<String, SocialNetwork> getSocialNetworks() {

		return socialNetworks;
	}

	/**
	 * @param socialNetworks the socialNetworks to set
	 */
	public void setSocialNetworks(final Map<String, SocialNetwork> socialNetworks) {

		this.socialNetworks = socialNetworks;
	}

	/**
	 * @return the appApiKey
	 */
	public String getAppApiKey() {

		return appApiKey;
	}

	/**
	 * @return the defaultUrl
	 */
	public String getDefaultUrl() {

		return defaultUrl;
	}

	/**
	 * @param defaultUrl the defaultUrl to set
	 */
	public void setDefaultUrl(final String defaultUrl) {

		this.defaultUrl = defaultUrl;
	}

	public String getProject() {

		return project;
	}

	public void setProject(final String project) {

		this.project = project;
	}

	@Override
	public String toString() {

		return "Application [name=" + name + ", description=" + description + ", appApiKey=" + appApiKey + "]";
	}

	// === helpers methods ================================================= //

	/**
	 * This convenience method set / replace the {@link SocialNetwork} object in the social networks map. It creates the map if necessary.
	 */
	@JsonIgnore
	public void defineSocialNetwork(final String snName, final SocialNetwork socialNetwork) {

		if (socialNetworks == null) {
			socialNetworks = new HashMap<>();
		}

		socialNetworks.put(snName, socialNetwork);
	}

	/**
	 * Set the Facebook social network to the given value.
	 */
	@JsonIgnore
	public void defineFacebook(final SocialNetwork socialNetwork) {

		defineSocialNetwork(SN_FACEBOOK, socialNetwork);
	}
}
