/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.commons.pagination;

import com.evrythng.commons.domain.Size;
import org.pcollections.PVector;

public interface Page<ELEMENT, QUERY, TOKEN> {

	PVector<ELEMENT> elements();

	QUERY query();

	Size size();

	boolean hasNext();

	TOKEN token();
}