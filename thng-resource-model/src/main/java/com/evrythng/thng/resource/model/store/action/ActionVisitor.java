/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.action;

/**
 * Visitor interface for actions.
 **/
public interface ActionVisitor {

	void visit(CustomAction action);

	void visit(CheckinAction action);

	void visit(ScanAction action);

	void visit(ImplicitScanAction action);

	void visit(ShareAction action);

	void visit(CommissionAction action);

	void visit(DecommissionAction action);
}
