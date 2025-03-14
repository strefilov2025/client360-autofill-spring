package ru.mtsbank.autocomplete.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * Электронная почта
 */
@ApiModel(description = "Электронная почта")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-06-28T14:03:31.041683100+03:00[Europe/Moscow]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Email implements Serializable {
    private static final long serialVersionUID = 5119737476892020040L;
    @JsonProperty("primary")
    private Boolean primary;

    @JsonProperty("emailType")
    private String emailType;

    @JsonProperty("email")
    private String email;

    public Email primary(Boolean primary) {
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

  public Email emailType(String emailType) {
    this.emailType = emailType;
    return this;
  }

  /**
   * Тип электронной почты
   * @return emailType
  */
  @ApiModelProperty(readOnly = true, value = "Тип электронной почты")


  public String getEmailType() {
    return emailType;
  }

  public void setEmailType(String emailType) {
    this.emailType = emailType;
  }

  public Email email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Адрес электронной почты
   * @return email
  */
  @ApiModelProperty(value = "Адрес электронной почты")


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Email email = (Email) o;
    return Objects.equals(this.primary, email.primary) &&
        Objects.equals(this.emailType, email.emailType) &&
        Objects.equals(this.email, email.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(primary, emailType, email);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Email {\n");
    
    sb.append("    primary: ").append(toIndentedString(primary)).append("\n");
    sb.append("    emailType: ").append(toIndentedString(emailType)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
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

