package ru.mtsbank.autocomplete.mtsb.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * An enumeration.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum DocTypeEnum {

  PASSPORT_RU("PASSPORT_RU");

  private final String value;

  DocTypeEnum(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static DocTypeEnum fromValue(String text) {
    for (DocTypeEnum b : DocTypeEnum.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + text + "'");
  }
}

