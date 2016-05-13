/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
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

		IMPLICIT_SCANS("implicitScans"), SHARES("shares"), SCANS("scans"), CHECKINS("checkins");
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

	public ActionType() {

	}

	public ActionType(final String name) {

		this.name = name;
	}

	public String getName() {

		return name;
	}

	public void setName(final String name) {

		this.name = name;
	}

	@Override
	public boolean equals(final Object o) {

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

		return name.equals(that.name);

	}

	@Override
	public int hashCode() {

		int result = super.hashCode();
		return 31 * result + name.hashCode();
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
		sb.append('}');
		return sb.toString();
	}
}
