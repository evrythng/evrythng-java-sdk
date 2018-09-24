/*
 * (c) Copyright Reserved EVRYTHNG Limited 2018. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */

package com.evrythng.thng.resource.model.store;

import com.evrythng.commons.EnumUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Map;

public enum ProjectType {
  CAMPAIGN("campaign");

  private final String name;

  private static final Map<String, ProjectType> names;

  ProjectType(final String name) {

    this.name = name;
  }

  static {
    names = EnumUtils.createNames(values());
  }

  @JsonValue
  @Override
  public String toString() {

    return name;
  }

  @JsonCreator
  public static ProjectType fromString(final String name) {

    return EnumUtils.fromString(names, name);
  }
}
