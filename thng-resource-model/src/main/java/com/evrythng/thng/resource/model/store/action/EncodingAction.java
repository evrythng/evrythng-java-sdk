/*
 * (c) Copyright Reserved EVRYTHNG Limited 2019. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store.action;

public class EncodingAction extends ThngAction {

  private static final long serialVersionUID = 5238847876269747147L;

  public static final String TYPE = ActionType.Value.ENCODINGS.value();

  public EncodingAction() {
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
