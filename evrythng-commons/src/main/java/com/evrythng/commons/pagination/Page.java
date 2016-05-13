/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
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