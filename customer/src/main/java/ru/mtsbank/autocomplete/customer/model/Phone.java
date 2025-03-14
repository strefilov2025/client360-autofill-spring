package ru.mtsbank.autocomplete.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * Телефон
 */
@ApiModel(description = "Телефон")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-06-28T14:03:31.041683100+03:00[Europe/Moscow]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Phone implements Serializable {
    private static final long serialVersionUID = 2875993836270613527L;
    @JsonProperty("primary")
    private Boolean primary;

    @JsonProperty("phoneType")
    private String phoneType;

    @JsonProperty("number")
    private String number;

    public Phone primary(Boolean primary) {
    this.primary = primary;
    return this;
  }

  /**
   * Признак основного
   * @return primary
  */
  @ApiModelProperty(readOnly = true, value = "Признак основного")


  public Boolean getPrimary() {
    return primary;
  }

  public void setPrimary(Boolean primary) {
    this.primary = primary;
  }

  public Phone phoneType(String phoneType) {
    this.phoneType = phoneType;
    return this;
  }

  /**
   * Тип телефона
   * @return phoneType
  */
  @ApiModelProperty(readOnly = true, value = "Тип телефона")


  public String getPhoneType() {
    return phoneType;
  }

  public void setPhoneType(String phoneType) {
    this.phoneType = phoneType;
  }

  public Phone number(String number) {
    this.number = number;
    return this;
  }

  /**
   * Номер телефона
   * @return number
  */
  @ApiModelProperty(value = "Номер телефона")


  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Phone phone = (Phone) o;
    return Objects.equals(this.primary, phone.primary) &&
        Objects.equals(this.phoneType, phone.phoneType) &&
        Objects.equals(this.number, phone.number);
  }

  @Override
  public int hashCode() {
    return Objects.hash(primary, phoneType, number);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Phone {\n");
    
    sb.append("    primary: ").append(toIndentedString(primary)).append("\n");
    sb.append("    phoneType: ").append(toIndentedString(phoneType)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

