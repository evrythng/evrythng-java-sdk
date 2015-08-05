/*
 * (c) Copyright 2014 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.core;

import org.apache.commons.collections.list.SetUniqueList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds the scopes for a resource.
 */
public class ScopeResource implements Serializable {

	public static final String ALL_USERS = "allU";
	public static final String ALL_PROJECTS = "allP";
	private static final long serialVersionUID = -1514722768762632206L;
	private List<String> users;
	private List<String> projects;
	public static final ScopeResource EMPTY = new ScopeResource();

	public ScopeResource() {

		users = new ArrayList<>();
		projects = new ArrayList<>();
	}

	public ScopeResource(final List<String> users, final List<String> projects) {

		this.users = users;
		this.projects = projects;
	}

	public List<String> getUsers() {

		return users;
	}

	public void setUsers(final List<String> users) {

		this.users = users != null ? SetUniqueList.decorate(new ArrayList(users)) : null;
	}

	public List<String> getProjects() {

		return projects;
	}

	public void setProjects(final List<String> projects) {

		this.projects = projects != null ? SetUniqueList.decorate(new ArrayList(projects)) : null;
	}

	@Override
	public boolean equals(final Object other) {

		if (this == other) {
			return true;
		}
		if (other == null || getClass() != other.getClass()) {
			return false;
		}

		ScopeResource that = (ScopeResource) other;

		if (projects != null ? !projects.equals(that.projects) : that.projects != null) {
			return false;
		}
		if (users != null ? !users.equals(that.users) : that.users != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {

		int result = users != null ? users.hashCode() : 0;
		result = 31 * result + (projects != null ? projects.hashCode() : 0);
		return result;
	}
}
