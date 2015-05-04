/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.action;

import com.evrythng.commons.EnumUtils;
import com.evrythng.commons.annotations.csv.CsvTransient;
import com.evrythng.thng.resource.model.core.TemporalResourceModel;
import com.evrythng.thng.resource.model.store.EmbeddedLocation;
import com.evrythng.thng.resource.model.store.rule.reaction.Reaction;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Rest model for an action.
 **/
public abstract class Action extends TemporalResourceModel {

	private static final long serialVersionUID = -7924569012523434017L;

	public enum LocationSource {

		SENSOR("sensor"), PLACE("place"), GEO_IP("geoIp"), UNKNOWN("unknown");

		private static Map<String, LocationSource> names;
		private String name;

		LocationSource(String name) {
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
		public static LocationSource fromString(String name) {
			return EnumUtils.fromString(names, name);
		}
	}

	public static class Context implements Serializable {

		private static final long serialVersionUID = 3873168084260820545L;

		private String ipAddress;

		private String city;
		public static final String FIELD_CITY = "city";

		private String region;
		public static final String FIELD_REGION = "region";

		private String countryCode;
		public static final String FIELD_COUNTRY_CODE = "countryCode";

		private String userAgent;

		private String referer;
		private String language;

		private String userAgentName;
		private String userAgentVersion;
		private String userAgentType;

		private String deviceType;
		private String device;

		private String operatingSystemName;
		private String operatingSystemFamily;
		private String operatingSystemProducer;
		private String operatingSystemVersion;

		private String timeZone;

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			Context context = (Context) o;

			if (city != null ? !city.equals(context.city) : context.city != null)
				return false;
			if (countryCode != null ? !countryCode.equals(context.countryCode) : context.countryCode != null)
				return false;
			if (device != null ? !device.equals(context.device) : context.device != null)
				return false;
			if (deviceType != null ? !deviceType.equals(context.deviceType) : context.deviceType != null)
				return false;
			if (ipAddress != null ? !ipAddress.equals(context.ipAddress) : context.ipAddress != null)
				return false;
			if (language != null ? !language.equals(context.language) : context.language != null)
				return false;
			if (operatingSystemFamily != null ? !operatingSystemFamily.equals(context.operatingSystemFamily) : context.operatingSystemFamily != null)
				return false;
			if (operatingSystemName != null ? !operatingSystemName.equals(context.operatingSystemName) : context.operatingSystemName != null)
				return false;
			if (operatingSystemProducer != null ? !operatingSystemProducer.equals(context.operatingSystemProducer) : context.operatingSystemProducer != null)
				return false;
			if (operatingSystemVersion != null ? !operatingSystemVersion.equals(context.operatingSystemVersion) : context.operatingSystemVersion != null)
				return false;
			if (referer != null ? !referer.equals(context.referer) : context.referer != null)
				return false;
			if (region != null ? !region.equals(context.region) : context.region != null)
				return false;
			if (userAgent != null ? !userAgent.equals(context.userAgent) : context.userAgent != null)
				return false;
			if (userAgentName != null ? !userAgentName.equals(context.userAgentName) : context.userAgentName != null)
				return false;
			if (userAgentType != null ? !userAgentType.equals(context.userAgentType) : context.userAgentType != null)
				return false;
			if (userAgentVersion != null ? !userAgentVersion.equals(context.userAgentVersion) : context.userAgentVersion != null)
				return false;
			if (timeZone != null ? !timeZone.equals(context.timeZone) : context.timeZone != null)
				return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = ipAddress != null ? ipAddress.hashCode() : 0;
			result = 31 * result + (city != null ? city.hashCode() : 0);
			result = 31 * result + (region != null ? region.hashCode() : 0);
			result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
			result = 31 * result + (userAgent != null ? userAgent.hashCode() : 0);
			result = 31 * result + (referer != null ? referer.hashCode() : 0);
			result = 31 * result + (language != null ? language.hashCode() : 0);
			result = 31 * result + (userAgentName != null ? userAgentName.hashCode() : 0);
			result = 31 * result + (userAgentVersion != null ? userAgentVersion.hashCode() : 0);
			result = 31 * result + (userAgentType != null ? userAgentType.hashCode() : 0);
			result = 31 * result + (deviceType != null ? deviceType.hashCode() : 0);
			result = 31 * result + (device != null ? device.hashCode() : 0);
			result = 31 * result + (operatingSystemName != null ? operatingSystemName.hashCode() : 0);
			result = 31 * result + (operatingSystemFamily != null ? operatingSystemFamily.hashCode() : 0);
			result = 31 * result + (operatingSystemProducer != null ? operatingSystemProducer.hashCode() : 0);
			result = 31 * result + (operatingSystemVersion != null ? operatingSystemVersion.hashCode() : 0);
			result = 31 * result + (timeZone != null ? timeZone.hashCode() : 0);
			return result;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getRegion() {
			return region;
		}

		public void setRegion(String region) {
			this.region = region;
		}

		public String getCountryCode() {
			return countryCode;
		}

		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}

		public String getIpAddress() {
			return ipAddress;
		}

		public void setIpAddress(String ipAddress) {
			this.ipAddress = ipAddress;
		}

		public void setUserAgent(final String userAgent) {
			this.userAgent = userAgent;
		}

		public String getUserAgent() {
			return userAgent;
		}

		public void setReferer(final String referer) {
			this.referer = referer;
		}

		public void setLanguage(String language) {
			this.language = language;
		}

		public void setUserAgentName(String userAgentName) {
			this.userAgentName = userAgentName;
		}

		public void setUserAgentVersion(String userAgentVersion) {
			this.userAgentVersion = userAgentVersion;
		}

		public void setUserAgentType(String userAgentType) {
			this.userAgentType = userAgentType;
		}

		public void setDeviceType(String deviceType) {
			this.deviceType = deviceType;
		}

		public void setDevice(String device) {
			this.device = device;
		}

		public void setOperatingSystemName(String operatingSystemName) {
			this.operatingSystemName = operatingSystemName;
		}

		public void setOperatingSystemFamily(String operatingSystemFamily) {
			this.operatingSystemFamily = operatingSystemFamily;
		}

		public void setOperatingSystemProducer(String operatingSystemProducer) {
			this.operatingSystemProducer = operatingSystemProducer;
		}

		public void setOperatingSystemVersion(String operatingSystemVersion) {
			this.operatingSystemVersion = operatingSystemVersion;
		}

		public String getReferer() {
			return referer;
		}

		public String getLanguage() {
			return language;
		}

		public String getUserAgentName() {
			return userAgentName;
		}

		public String getUserAgentVersion() {
			return userAgentVersion;
		}

		public String getUserAgentType() {
			return userAgentType;
		}

		public String getDeviceType() {
			return deviceType;
		}

		public String getDevice() {
			return device;
		}

		public String getOperatingSystemName() {
			return operatingSystemName;
		}

		public String getOperatingSystemFamily() {
			return operatingSystemFamily;
		}

		public String getOperatingSystemProducer() {
			return operatingSystemProducer;
		}

		public String getOperatingSystemVersion() {
			return operatingSystemVersion;
		}

		public String getTimeZone() {

			return timeZone;
		}

		public void setTimeZone(final String timeZone) {

			this.timeZone = timeZone;
		}

		@Override
		public String toString() {

			StringBuilder sb = new StringBuilder("Context{");
			sb.append("city='").append(city).append('\'');
			sb.append(", ipAddress='").append(ipAddress).append('\'');
			sb.append(", region='").append(region).append('\'');
			sb.append(", countryCode='").append(countryCode).append('\'');
			sb.append(", userAgent='").append(userAgent).append('\'');
			sb.append(", referer='").append(referer).append('\'');
			sb.append(", language='").append(language).append('\'');
			sb.append(", userAgentName='").append(userAgentName).append('\'');
			sb.append(", userAgentVersion='").append(userAgentVersion).append('\'');
			sb.append(", userAgentType='").append(userAgentType).append('\'');
			sb.append(", deviceType='").append(deviceType).append('\'');
			sb.append(", device='").append(device).append('\'');
			sb.append(", operatingSystemName='").append(operatingSystemName).append('\'');
			sb.append(", operatingSystemFamily='").append(operatingSystemFamily).append('\'');
			sb.append(", operatingSystemProducer='").append(operatingSystemProducer).append('\'');
			sb.append(", operatingSystemVersion='").append(operatingSystemVersion).append('\'');
			sb.append(", timeZone='").append(timeZone).append('\'');
			sb.append('}');
			return sb.toString();
		}
	}

	private String type;
	public static final String FIELD_TYPE = "type";

	private String user;
	public static final String FIELD_USER = "user";

	private EmbeddedLocation location;

	private LocationSource locationSource;

	private String device;

	private Context context;
	public static final String FIELD_CONTEXT = "context";

	//Note: had to add the constant here (as the ActionDocument is not visible from the model package)
	//TODO maybe at some point we want to have the gender and userSegments here anyway in the model.
	public static final String FIELD_GENDER = "gender";
	public static final String FIELD_USER_SEGMENTS = "userSegments";

	private List<Reaction> reactions;
	private String createdByProject;
	private String createdByApp;

	public Action() {

	}

	public Action(String type, String user, EmbeddedLocation location, Context context) {
		this.type = type;
		this.user = user;
		this.location = location;
		this.context = context;
	}

	public void copyTo(Action action) {

	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[type=" + type + ", user=" + user + ", location=" + location + ", context=" + context + ", nb of reactions=" + (reactions != null ? reactions.size() : 0)
				+ "]";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public EmbeddedLocation getLocation() {
		return location;
	}

	public void setLocation(EmbeddedLocation location) {
		this.location = location;
	}

	@JsonIgnore
	public boolean hasPosition() {
		return location != null && location.hasPosition();
	}

	public LocationSource getLocationSource() {
		return locationSource;
	}

	public void setLocationSource(LocationSource locationSource) {
		this.locationSource = locationSource;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	@CsvTransient
	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	@CsvTransient
	public List<Reaction> getReactions() {
		return reactions;
	}

	public void setReactions(List<Reaction> reactions) {
		this.reactions = reactions;
	}

	public void addReaction(Reaction reaction) {
		if (reactions == null) {
			reactions = new ArrayList<Reaction>();
		}
		reactions.add(reaction);
	}

	public String getCreatedByProject() {

		return createdByProject;
	}

	public void setCreatedByProject(final String createdByProject) {

		this.createdByProject = createdByProject;
	}

	public String getCreatedByApp() {
		return createdByApp;
	}

	public void setCreatedByApp(String createdByApp) {
		this.createdByApp = createdByApp;
	}

	@JsonIgnore
	@CsvTransient
	public boolean isCustom() {
		return ActionType.Value.isCustom(type);
	}

	public abstract void accept(ActionVisitor visitor);
}
