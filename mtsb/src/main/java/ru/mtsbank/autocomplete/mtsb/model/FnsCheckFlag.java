package ru.mtsbank.autocomplete.mtsb.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * An enumeration.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum FnsCheckFlag {

  NUMBER_1(1),

  NUMBER_0(0);

  private final Integer value;

  FnsCheckFlag(Integer value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static FnsCheckFlag fromValue(String text) {
    for (FnsCheckFlag b : FnsCheckFlag.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + text + "'");
  }
}

