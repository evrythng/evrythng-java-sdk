/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
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

		if (projects != null && that.projects != null) {
			if (projects.size() != that.projects.size() || !projects.containsAll(that.projects)){
				return false;
			}
		}
		if (projects != null && that.projects == null || projects == null && that.projects != null) {
			return false;
		}
		if (users != null && that.users != null) {
			if (users.size() != that.users.size() || !users.containsAll(that.users)){
				return false;
			}
		}
		if (users != null && that.users == null || users == null && that.users != null) {
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

	@Override
	public String toString() {

		final StringBuilder sb = new StringBuilder("{");
		sb.append("projects=").append(projects);
		sb.append(", users=").append(users);
		sb.append('}');
		return sb.toString();
	}
}
