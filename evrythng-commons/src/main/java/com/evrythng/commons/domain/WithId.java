/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.commons.domain;

public interface WithId {

	String getId();

	interface Mutable extends WithId {

		void setId(String id);
	}
}
