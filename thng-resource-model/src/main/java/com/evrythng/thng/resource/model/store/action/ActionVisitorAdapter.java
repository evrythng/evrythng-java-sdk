/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.action;

/**
 * Adapter class for {@link ActionVisitor}.
 **/
public class ActionVisitorAdapter implements ActionVisitor {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(CustomAction action) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(CheckinAction action) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(ScanAction action) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(ImplicitScanAction action) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(ShareAction action) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(EncodingAction action) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(CommissionAction action) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(DecommissionAction action) {
	}
}
