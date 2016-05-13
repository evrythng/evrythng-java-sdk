/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
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
