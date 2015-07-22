/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store;

public class SequentialShortIdTemplate extends ShortIdTemplate {

	private String prefix;
	public static final String PREFIX = "prefix";
	private String suffix;
	public static final String SUFFIX = "suffix";
	private String separator;
	public static final String SEPARATOR = "separator";
	private PaddingType padding;
	public static final String PADDING = "padding";
	private Long start;
	public static final String START = "start";
	private Long increment;
	public static final String INCREMENT = "increment";

	public SequentialShortIdTemplate() {
		setType(Type.SEQUENTIAL);
	}

	public static enum PaddingType {
		ZERO;
	}

	public String getPrefix() {

		return prefix;
	}

	public void setPrefix(final String prefix) {

		this.prefix = prefix;
	}

	public String getSuffix() {

		return suffix;
	}

	public void setSuffix(final String suffix) {

		this.suffix = suffix;
	}

	public String getSeparator() {

		return separator;
	}

	public void setSeparator(final String separator) {

		this.separator = separator;
	}

	public PaddingType getPadding() {

		return padding;
	}

	public void setPadding(final PaddingType padding) {

		this.padding = padding;
	}

	public Long getStart() {

		return start;
	}

	public void setStart(final Long start) {

		this.start = start;
	}

	public Long getIncrement() {

		return increment;
	}

	public void setIncrement(final Long increment) {

		this.increment = increment;
	}
}
