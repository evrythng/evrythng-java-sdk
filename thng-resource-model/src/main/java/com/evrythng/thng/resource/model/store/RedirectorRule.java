/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

import java.io.Serializable;
import java.util.List;

/**
 * Model representation for single <em>redirector rule</em>.
 */
public class RedirectorRule implements Serializable {

	private static final long serialVersionUID = -6245862812999796493L;
	private String match;
	private String name;
	private String redirectUrl;
	private List<ProjectAndApp> delegates;

	public String getMatch() {

		return match;
	}

	public void setMatch(final String match) {

		this.match = match;
	}

	public String getName() {

		return name;
	}

	public void setName(final String name) {

		this.name = name;
	}

	public String getRedirectUrl() {

		return redirectUrl;
	}

	public void setRedirectUrl(final String redirectUrl) {

		this.redirectUrl = redirectUrl;
	}

	public List<ProjectAndApp> getDelegates() {

		return delegates;
	}

	public void setDelegates(final List<ProjectAndApp> delegates) {

		this.delegates = delegates;
	}

	public static class ProjectAndApp implements Serializable {

		private static final long serialVersionUID = -1848206315458756297L;
		private String app;
		private String project;

		public ProjectAndApp() {

		}

		public ProjectAndApp(final String project, final String app) {

			this.project = project;
			this.app = app;
		}

		public String getApp() {

			return app;
		}

		public void setApp(final String app) {

			this.app = app;
		}

		public String getProject() {

			return project;
		}

		public void setProject(final String project) {

			this.project = project;
		}

		@Override
		public boolean equals(final Object o) {

			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			ProjectAndApp that = (ProjectAndApp) o;

			if (app != null ? !app.equals(that.app) : that.app != null) {
				return false;
			}
			return !(project != null ? !project.equals(that.project) : that.project != null);

		}

		@Override
		public int hashCode() {

			int result = app != null ? app.hashCode() : 0;
			result = 31 * result + (project != null ? project.hashCode() : 0);
			return result;
		}
	}

	@Override
	public boolean equals(final Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		RedirectorRule that = (RedirectorRule) o;

		if (match != null ? !match.equals(that.match) : that.match != null) {
			return false;
		}
		if (name != null ? !name.equals(that.name) : that.name != null) {
			return false;
		}
		if (redirectUrl != null ? !redirectUrl.equals(that.redirectUrl) : that.redirectUrl != null) {
			return false;
		}
		return !(delegates != null ? !delegates.equals(that.delegates) : that.delegates != null);

	}

	@Override
	public int hashCode() {

		int result = match != null ? match.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (redirectUrl != null ? redirectUrl.hashCode() : 0);
		result = 31 * result + (delegates != null ? delegates.hashCode() : 0);
		return result;
	}
}
