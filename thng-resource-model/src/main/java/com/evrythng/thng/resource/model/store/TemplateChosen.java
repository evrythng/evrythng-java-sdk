package com.evrythng.thng.resource.model.store;

import com.evrythng.commons.EnumUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Map;

public enum TemplateChosen {
  REWARD("reward"),
  PROMOTION("promotion"),
  FEEDBACK("feedback"),
  CONTENT("content"),
  NONE("none");

  private final String name;

  private static final Map<String, TemplateChosen> names;

  TemplateChosen(final String name) {

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
  public static TemplateChosen fromString(final String name) {

    return EnumUtils.fromString(names, name);
  }
}
