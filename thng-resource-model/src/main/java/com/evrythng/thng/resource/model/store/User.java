/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.commons.EnumUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Model representation for <em>users</em>.
 */
public class User extends AbstractUser {

	private static final long serialVersionUID = -1452057625044137170L;

	/**
	 * @deprecated since 1.16 - use {@link AbstractUser.Birthday} instead
	 */
	@Deprecated
	public static class Birthday implements Serializable {

		private static final long serialVersionUID = -5028876391961121928L;
		private Integer day;
		private Integer month;
		private Integer year;

		public Birthday() {

		}

		public Birthday(final Integer day, final Integer month, final Integer year) {

			this.month = month;
			this.day = day;
			this.year = year;
		}

		public Integer getMonth() {

			return month;
		}

		public void setMonth(final Integer month) {

			this.month = month;
		}

		public Integer getDay() {

			return day;
		}

		public void setDay(final Integer day) {

			this.day = day;
		}

		public Integer getYear() {

			return year;
		}

		public void setYear(final Integer year) {

			this.year = year;
		}

		@JsonIgnore
		public boolean isCompleteDate() {

			return year != null && hasDayAndMonth();
		}

		@JsonIgnore
		public boolean hasDayAndMonth() {

			return month != null && day != null;
		}
	}

	/**
	 * @deprecated since 1.16 - use {@link AbstractUser.Gender} instead
	 */
	@Deprecated
	public enum Gender {

		MALE("male"), FEMALE("female");
		private static Map<String, Gender> names = new HashMap<>();
		private final String name;

		Gender(final String name) {

			this.name = name;
		}

		static {
			names = EnumUtils.createNames(values());
		}

		@JsonValue
		@Override
		public String toString() {

			return name;
		}

		@JsonCreator
		public static Gender fromString(final String name) {

			return EnumUtils.fromString(names, name);
		}
	}

	private SocialNetwork primarySocialNetwork;
	private Long socialProfileLastSync;
	/**
	 * Tells if the user can log in or not.
	 */
	@JsonIgnore
	private Boolean canLogin;
	private String project;
	private String app;
	private Integer numberOfFriends;

	public User() {

		this(null);
	}

	public User(final String app) {

		this.app = app;
	}

	public String getProject() {

		return project;
	}

	public void setProject(final String project) {

		this.project = project;
	}

	public void setApp(final String app) {

		this.app = app;
	}

	public String getApp() {

		return app;
	}

	/**
	 * Engine returns true. This canLogin is not used anymore
	 * MOCDTW-385
	 */
	@Deprecated
	@JsonIgnore
	public Boolean isCanLogin() {

		return canLogin;
	}

	@Deprecated
	public void setCanLogin(final Boolean canLogin) {

		this.canLogin = canLogin;
	}

	public SocialNetwork getPrimarySocialNetwork() {

		return primarySocialNetwork;
	}

	public void setPrimarySocialNetwork(final SocialNetwork primarySocialNetwork) {

		this.primarySocialNetwork = primarySocialNetwork;
	}

	public Long getSocialProfileLastSync() {

		return socialProfileLastSync;
	}

	public void setSocialProfileLastSync(final Long socialProfileLastSync) {

		this.socialProfileLastSync = socialProfileLastSync;
	}

	public Integer getNumberOfFriends() {

		return numberOfFriends;
	}

	public void setNumberOfFriends(final Integer numberOfFriends) {

		this.numberOfFriends = numberOfFriends;
	}
}
