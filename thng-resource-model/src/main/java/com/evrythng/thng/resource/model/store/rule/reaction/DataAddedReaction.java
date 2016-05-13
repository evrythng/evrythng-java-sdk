/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.rule.reaction;

/**
 * Reaction when data has been added to the action response. This is hipster.
 * Use it wisely.
 * 
 **/
public class DataAddedReaction extends Reaction {

	private static final long serialVersionUID = 2820875931797220143L;

	public static final String TYPE = "dataAdded";

	private Object data;

	public DataAddedReaction() {
		setType(TYPE);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void accept(ReactionVisitor visitor) {
		visitor.visit(this);
	}
}
