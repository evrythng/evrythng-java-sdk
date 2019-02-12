/*
 * (c) Copyright Reserved EVRYTHNG Limited 2019. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.action;

/**
 * Model for a invalid scan action.
*/
public class InvalidScanAction extends Action {

	private static final long serialVersionUID = 7734607219137904410L;

	public static final String TYPE = ActionType.Value.INVALID_SCANS.value();

	/**
	 * The URL that has been scanned but no matching resource found for it.
	*/
	private String url;

	public InvalidScanAction() {
		setType(TYPE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void accept(ActionVisitor visitor) {
		visitor.visit(this);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}
}
