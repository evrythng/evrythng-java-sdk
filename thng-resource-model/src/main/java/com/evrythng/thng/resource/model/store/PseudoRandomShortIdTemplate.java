/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store;

public class PseudoRandomShortIdTemplate extends ShortIdTemplate {

	private String prefix;
	public static final String PREFIX = "prefix";
	private String suffix;
	public static final String SUFFIX = "suffix";
	private String separator;
	public static final String SEPARATOR = "separator";

	public PseudoRandomShortIdTemplate() {
		setType(Type.PSEUDO_RANDOM);
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

}
