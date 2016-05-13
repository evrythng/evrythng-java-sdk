/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.core;

import java.io.Serializable;

public class FileToSign implements Serializable {

	private static final long serialVersionUID = -7936308225682126176L;

	private final String name;
	private final String type;

	public FileToSign(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}
}
