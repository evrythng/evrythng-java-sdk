/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.commons.domain;

public interface WithId {

	String getId();

	interface Mutable extends WithId {

		void setId(String id);
	}
}
