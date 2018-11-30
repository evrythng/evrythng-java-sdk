package com.evrythng.thng.resource.model.store.action;

public class CommissionAction extends ThngAction {

	private static final long serialVersionUID = -5051453193382695256L;

	public static final String TYPE = ActionType.Value.COMMISSIONS.value();

	public CommissionAction() {
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
