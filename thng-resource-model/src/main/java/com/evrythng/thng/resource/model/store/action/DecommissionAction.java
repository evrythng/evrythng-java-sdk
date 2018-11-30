package com.evrythng.thng.resource.model.store.action;

public class DecommissionAction extends ThngAction {

	private static final long serialVersionUID = -4194139597852338570L;

	public static final String TYPE = ActionType.Value.DECOMMISSIONS.value();

	public DecommissionAction() {
		setType(TYPE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void accept(ActionVisitor visitor) {
		visitor.visit(this);
	}
}
