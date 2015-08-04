/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import java.util.Arrays;
import java.util.List;

/**
 * Input for the BatchPopulatingTask. Defines a shortId template, and a quantity.
 */
public class CsvAdiOutputParameters extends BaseAdiOutputParameters {
	
	private static final CsvAdiOutputParameters DEFAULT = new CsvAdiOutputParameters(Arrays.asList(Column.THNG, Column.SHORT_ID));
	
	public static CsvAdiOutputParameters defaultValue(){
		
		return DEFAULT;
	}

	public CsvAdiOutputParameters() {

		this(null);
	}

	private CsvAdiOutputParameters(final List<Column> columns) {

		super(columns);
		setType(Type.CSV);
	}
}
