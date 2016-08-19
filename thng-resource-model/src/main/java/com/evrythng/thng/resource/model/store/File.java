/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

public class File extends DurableResourceModel {

	private static final long serialVersionUID = 3858064514225475421L;

	private String uploadUrl;
	private String contentUrl;
	private Long size;
	private String name;
	private String type;
	private Boolean privateAccess;

	public String getUploadUrl() {

		return uploadUrl;
	}

	public void setUploadUrl(final String uploadUrl) {

		this.uploadUrl = uploadUrl;
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getPrivateAccess() {

		return privateAccess;
	}

	public void setPrivateAccess(final Boolean privateAccess) {

		this.privateAccess = privateAccess;
	}

	@Override
	public boolean equals(final Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}

		File file = (File) o;

		if (!name.equals(file.name)) {
			return false;
		}
		if (!type.equals(file.type)) {
			return false;
		}
		return privateAccess.equals(file.privateAccess);
	}

	@Override
	public int hashCode() {

		int result = super.hashCode();
		result = 31 * result + name.hashCode();
		result = 31 * result + type.hashCode();
		result = 31 * result + privateAccess.hashCode();
		return result;
	}
}