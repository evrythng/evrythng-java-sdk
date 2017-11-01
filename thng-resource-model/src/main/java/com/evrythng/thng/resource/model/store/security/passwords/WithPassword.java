/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.security.passwords;

public interface WithPassword {

	String getPassword();

	void setPassword(String password);

	String getOldPassword();

	void setOldPassword(String oldPassword);
}
