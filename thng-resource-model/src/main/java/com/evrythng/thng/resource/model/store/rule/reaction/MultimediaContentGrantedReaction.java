/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.rule.reaction;

import com.evrythng.thng.resource.model.store.content.MultimediaContent;

/**
 * Reaction when multimedia content was granted.
 **/
public class MultimediaContentGrantedReaction extends Reaction {

	private static final long serialVersionUID = 6048898727953494001L;

	public static final String TYPE = "multimediaContentGranted";

	private MultimediaContent multimediaContent;

	public MultimediaContentGrantedReaction() {
		setType(TYPE);
	}

	public MultimediaContent getMultimediaContent() {
		return multimediaContent;
	}

	public void setMultimediaContent(MultimediaContent multimediaContent) {
		this.multimediaContent = multimediaContent;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void accept(ReactionVisitor visitor) {
		visitor.visit(this);
	}

}
