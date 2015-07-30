/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store;

public class SequentialShortIdTemplate extends FragmentedShortIdTemplate {

	private PaddingType padding;
	public static final String PADDING = "padding";
	private String paddingChar;
	public static final String PADDING_CHAR = "paddingChar";
	private Long start;
	public static final String START = "start";
	private Long increment;
	public static final String INCREMENT = "increment";

	public SequentialShortIdTemplate() {
		setType(Type.SEQUENTIAL);
	}

	public static enum PaddingType {
		ZERO, CHAR;
	}

	public PaddingType getPadding() {

		return padding;
	}

	public void setPadding(final PaddingType padding) {

		this.padding = padding;
	}

	public String getPaddingChar() {

		return paddingChar;
	}

	public void setPaddingChar(final String paddingChar) {

		this.paddingChar = paddingChar;
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
