/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.commons.pagination;

public interface IPagination<TOKEN> {

	TOKEN token();

	PageSize size();
}
