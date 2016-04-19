/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.template;

public interface WithId {

	String getId();

	interface Mutable extends WithId {

		void setId(String id);
	}
}
