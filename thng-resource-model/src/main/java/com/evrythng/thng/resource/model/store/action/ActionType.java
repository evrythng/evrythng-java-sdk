/*
 * (c) Copyright Reserved EVRYTHNG Limited 2017. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.action;

import com.evrythng.thng.resource.model.core.DurableResourceModel;
import com.evrythng.thng.resource.model.utils.ObjectUtils;

import java.util.Comparator;

/**
 * Resource model for an action type.
 */
public class ActionType extends DurableResourceModel {

	private static final long serialVersionUID = -2732500419749798891L;
	public static final char CUSTOM_TYPE_PREFIX = '_';

	public enum Value {

		IMPLICIT_SCANS("implicitScans"),
		SHARES("shares"),
		SCANS("scans"),
		CHECKINS("checkins"),
		ENCODINGS("encodings"),
		COMMISSIONS("commissions"),
		DECOMMISSIONS("decommissions"),
		INVALID_SCANS("invalidScans");

		private final String value;

		Value(final String value) {

			this.value = value;
		}

		public String value() {

			return value;
		}

		public static boolean isCustom(final String type) {

			return type.charAt(0) == CUSTOM_TYPE_PREFIX;
		}
	}

	private String name;
	private String description;

	public ActionType() {

	}

	public ActionType(String name) {
		this.name = name;
	}

	public ActionType(final String name, final String description) {

		this.name = name;
		this.description = description;
	}

	public String getName() {

		return name;
	}

	public void setName(final String name) {

		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}

		ActionType that = (ActionType) o;

		if (!name.equals(that.name)) {
			return false;
		}
		return description != null ? description.equals(that.description) : that.description == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + name.hashCode();
		result = 31 * result + (description != null ? description.hashCode() : 0);
		return result;
	}

	/**
	 * Note: this comparator imposes orderings that are inconsistent with equals.
	 */
	public static final Comparator<ActionType> TYPE_COMPARATOR = new Comparator<ActionType>() {

		@Override
		public int compare(final ActionType type1, final ActionType type2) {

			return ObjectUtils.nullSafeCompare(type1.name, type2.name);
		}
	};

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder("{");
		sb.append("name='").append(name).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
