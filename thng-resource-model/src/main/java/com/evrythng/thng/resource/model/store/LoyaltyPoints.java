/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.commons.LowerCaseKeyMap;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Map;

public class LoyaltyPoints extends LowerCaseKeyMap<LoyaltyPoints.SchemePoints> {

	private static final long serialVersionUID = -3050341780268798332L;

	public LoyaltyPoints() {
	}

	public LoyaltyPoints(String scheme, Integer pointsChange, Integer tierPointsChange) {
		put(scheme, pointsChange, tierPointsChange);
	}

	/**
	 * Deep copy of all the items.
	 */
	public LoyaltyPoints(LoyaltyPoints items) {
		for (Map.Entry<String, LoyaltyPoints.SchemePoints> pc : items.entrySet()) {
			put(pc.getKey(), pc.getValue().points, pc.getValue().tierPoints);
		}
	}

	public static class SchemePoints implements Serializable {

		private static final long serialVersionUID = -2883368743402696369L;

		private Integer points;
		private Integer tierPoints;

		public Integer getPoints() {
			return points;
		}

		@JsonIgnore
		public int getPointsOrZero() {
			return points != null ? points : 0;
		}

		public void setPoints(Integer points) {
			this.points = points;
		}

		public Integer getTierPoints() {
			return tierPoints;
		}

		@JsonIgnore
		public int getTierPointsOrZero() {
			return tierPoints != null ? tierPoints : 0;
		}

		public void setTierPoints(Integer tierPoints) {
			this.tierPoints = tierPoints;
		}

		public void add(SchemePoints other) {
			if (other.getPointsOrZero() != 0) {
				setPoints(getPointsOrZero() + other.getPointsOrZero());
			}
			if (other.getTierPointsOrZero() != 0) {
				setTierPoints(getTierPointsOrZero() + other.getTierPointsOrZero());
			}
		}
	}

	public void put(String scheme, Integer points, Integer tierPoints) {
		LoyaltyPoints.SchemePoints pc = new SchemePoints();
		pc.points = points;
		pc.tierPoints = tierPoints;
		put(scheme, pc);
	}

	/**
	 * Merges the given loyalty points into <code>this</code>.
	 */
	public void merge(LoyaltyPoints other) {
		for (Map.Entry<String, LoyaltyPoints.SchemePoints> pc : other.entrySet()) {
			LoyaltyPoints.SchemePoints thisPoints = get(pc.getKey());
			if (thisPoints == null) {
				put(pc.getKey(), pc.getValue().getPoints(), pc.getValue().getTierPoints());
			} else {
				thisPoints.add(pc.getValue());
			}
		}
	}

	/**
	 * Creates a new map with all values multiplied by -1.
	 */
	public LoyaltyPoints invert() {
		LoyaltyPoints inv = new LoyaltyPoints();
		for (Map.Entry<String, LoyaltyPoints.SchemePoints> pc : this.entrySet()) {
			inv.put(pc.getKey(), invert(pc.getValue().points), invert(pc.getValue().tierPoints));
		}
		return inv;
	}

	private static Integer invert(Integer v) {
		if (v == null) {
			return null;
		}
		return -v;
	}
}