/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.commons.EnumUtils;
import com.evrythng.thng.resource.model.core.DurableResourceModel;
import com.evrythng.thng.resource.model.store.security.passwords.WithPassword;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.Map;

/**
 * Parent class for {@link User} and {@link Operator}
 */
public abstract class AbstractUser extends DurableResourceModel implements WithPassword {

	private static final long serialVersionUID = -1375779857541642147L;
	/**
	 * The user's unique email address.
	 */
	private String email;
	private String oldPassword;
	private String password;
	/**
	 * The user's first name.
	 */
	private String firstName;
	/**
	 * The user's last name.
	 */
	private String lastName;
	/**
	 * The user's timezone offset from <strong>UTC</strong>.
	 *
	 * @see <a
	 * href="http://en.wikipedia.org/wiki/Time_zones#List_of_UTC_offsets">http://en.wikipedia.org/wiki/Time_zones#List_of_UTC_offsets</a>
	 */
	private String timezone;
	/**
	 * The user's locale, as defined by <a href="http://tools.ietf.org/html/rfc5646">RFC 5646</a>.
	 */
	private String locale;
	/**
	 * A picture of the user encoded in a Base64 string.
	 * TODO: Test this with base 64 strings!
	 */
	private String photo;
	private Birthday birthday;
	private Gender gender;

	public String getEmail() {

		return email;
	}

	public void setEmail(final String email) {

		this.email = email;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(final String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getPassword() {

		return password;
	}

	public void setPassword(final String password) {

		this.password = password;
	}

	public String getLastName() {

		return lastName;
	}

	public void setLastName(final String lastName) {

		this.lastName = lastName;
	}

	public String getFirstName() {

		return firstName;
	}

	public void setFirstName(final String firstName) {

		this.firstName = firstName;
	}

	public String getTimezone() {

		return timezone;
	}

	public void setTimezone(final String timezone) {

		this.timezone = timezone;
	}

	public String getLocale() {

		return locale;
	}

	public void setLocale(final String locale) {

		this.locale = locale;
	}

	public String getPhoto() {

		return photo;
	}

	public void setPhoto(final String photo) {

		this.photo = photo;
	}

	public Birthday getBirthday() {

		return birthday;
	}

	public void setBirthday(final Birthday birthday) {

		this.birthday = birthday;
	}

	public Gender getGender() {

		return gender;
	}

	public void setGender(final Gender gender) {

		this.gender = gender;
	}

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

	public enum Gender {

		MALE("male"), FEMALE("female");
		private static final Map<String, Gender> names;
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
}
