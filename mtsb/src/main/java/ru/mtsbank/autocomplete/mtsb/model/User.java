package ru.mtsbank.autocomplete.mtsb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * msisdn  - номер абонента name - имя владельца surname - фамилия владельца patronymic - отчество владельца
 */
@ApiModel(description = "msisdn  - номер абонента name - имя владельца surname - фамилия владельца patronymic - отчество владельца")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-07-13T16:33:10.240228500+03:00[Europe/Moscow]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
  @JsonProperty("msisdn")
  private String msisdn;

  @JsonProperty("last_name_mdm")
  private String lastNameMdm;

  @JsonProperty("first_name_mdm")
  private String firstNameMdm;

  @JsonProperty("mid_name_mdm")
  private String midNameMdm;

  @JsonProperty("sex_mdm")
  private String sexMdm;

  @JsonProperty("age_mdm")
  private String ageMdm;

  @JsonProperty("first_name_spgr")
  private String firstNameSpgr;

  @JsonProperty("mid_name_spgr")
  private String midNameSpgr;

  @JsonProperty("last_name_spgr")
  private String lastNameSpgr;

  @JsonProperty("age_spgr")
  private String ageSpgr;

  @JsonProperty("sex_spgr")
  private String sexSpgr;

  @JsonProperty("is_first_name_equal")
  private Integer isFirstNameEqual;

  @JsonProperty("is_first_last_name_equal")
  private Integer isFirstLastNameEqual;

  public User msisdn(String msisdn) {
    this.msisdn = msisdn;
    return this;
  }

  /**
   * Get msisdn
   *
   * @return msisdn
   */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public User lastNameMdm(String lastNameMdm) {
    this.lastNameMdm = lastNameMdm;
    return this;
  }

  /**
   * Get lastNameMdm
   *
   * @return lastNameMdm
   */
  @ApiModelProperty(value = "")


  public String getLastNameMdm() {
    return lastNameMdm;
  }

  public void setLastNameMdm(String lastNameMdm) {
    this.lastNameMdm = lastNameMdm;
  }

  public User firstNameMdm(String firstNameMdm) {
    this.firstNameMdm = firstNameMdm;
    return this;
  }

  /**
   * Get firstNameMdm
   *
   * @return firstNameMdm
   */
  @ApiModelProperty(value = "")


  public String getFirstNameMdm() {
    return firstNameMdm;
  }

  public void setFirstNameMdm(String firstNameMdm) {
    this.firstNameMdm = firstNameMdm;
  }

  public User midNameMdm(String midNameMdm) {
    this.midNameMdm = midNameMdm;
    return this;
  }

  /**
   * Get midNameMdm
   *
   * @return midNameMdm
   */
  @ApiModelProperty(value = "")


  public String getMidNameMdm() {
    return midNameMdm;
  }

  public void setMidNameMdm(String midNameMdm) {
    this.midNameMdm = midNameMdm;
  }

  public User sexMdm(String sexMdm) {
    this.sexMdm = sexMdm;
    return this;
  }

  /**
   * Get sexMdm
   *
   * @return sexMdm
   */
  @ApiModelProperty(value = "")


  public String getSexMdm() {
    return sexMdm;
  }

  public void setSexMdm(String sexMdm) {
    this.sexMdm = sexMdm;
  }

  public User ageMdm(String ageMdm) {
    this.ageMdm = ageMdm;
    return this;
  }

  /**
   * Get ageMdm
   *
   * @return ageMdm
   */
  @ApiModelProperty(value = "")


  public String getAgeMdm() {
    return ageMdm;
  }

  public void setAgeMdm(String ageMdm) {
    this.ageMdm = ageMdm;
  }

  public User firstNameSpgr(String firstNameSpgr) {
    this.firstNameSpgr = firstNameSpgr;
    return this;
  }

  /**
   * Get firstNameSpgr
   *
   * @return firstNameSpgr
   */
  @ApiModelProperty(value = "")


  public String getFirstNameSpgr() {
    return firstNameSpgr;
  }

  public void setFirstNameSpgr(String firstNameSpgr) {
    this.firstNameSpgr = firstNameSpgr;
  }

  public User midNameSpgr(String midNameSpgr) {
    this.midNameSpgr = midNameSpgr;
    return this;
  }

  /**
   * Get midNameSpgr
   *
   * @return midNameSpgr
   */
  @ApiModelProperty(value = "")


  public String getMidNameSpgr() {
    return midNameSpgr;
  }

  public void setMidNameSpgr(String midNameSpgr) {
    this.midNameSpgr = midNameSpgr;
  }

  public User lastNameSpgr(String lastNameSpgr) {
    this.lastNameSpgr = lastNameSpgr;
    return this;
  }

  /**
   * Get lastNameSpgr
   *
   * @return lastNameSpgr
   */
  @ApiModelProperty(value = "")


  public String getLastNameSpgr() {
    return lastNameSpgr;
  }

  public void setLastNameSpgr(String lastNameSpgr) {
    this.lastNameSpgr = lastNameSpgr;
  }

  public User ageSpgr(String ageSpgr) {
    this.ageSpgr = ageSpgr;
    return this;
  }

  /**
   * Get ageSpgr
   *
   * @return ageSpgr
   */
  @ApiModelProperty(value = "")


  public String getAgeSpgr() {
    return ageSpgr;
  }

  public void setAgeSpgr(String ageSpgr) {
    this.ageSpgr = ageSpgr;
  }

  public User sexSpgr(String sexSpgr) {
    this.sexSpgr = sexSpgr;
    return this;
  }

  /**
   * Get sexSpgr
   *
   * @return sexSpgr
   */
  @ApiModelProperty(value = "")


  public String getSexSpgr() {
    return sexSpgr;
  }

  public void setSexSpgr(String sexSpgr) {
    this.sexSpgr = sexSpgr;
  }

  public User isFirstNameEqual(Integer isFirstNameEqual) {
    this.isFirstNameEqual = isFirstNameEqual;
    return this;
  }

  /**
   * Get isFirstNameEqual
   *
   * @return isFirstNameEqual
   */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getIsFirstNameEqual() {
    return isFirstNameEqual;
  }

  public void setIsFirstNameEqual(Integer isFirstNameEqual) {
    this.isFirstNameEqual = isFirstNameEqual;
  }

  public User isFirstLastNameEqual(Integer isFirstLastNameEqual) {
    this.isFirstLastNameEqual = isFirstLastNameEqual;
    return this;
  }

  /**
   * Get isFirstLastNameEqual
   *
   * @return isFirstLastNameEqual
   */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getIsFirstLastNameEqual() {
    return isFirstLastNameEqual;
  }

  public void setIsFirstLastNameEqual(Integer isFirstLastNameEqual) {
    this.isFirstLastNameEqual = isFirstLastNameEqual;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.msisdn, user.msisdn) &&
            Objects.equals(this.lastNameMdm, user.lastNameMdm) &&
            Objects.equals(this.firstNameMdm, user.firstNameMdm) &&
            Objects.equals(this.midNameMdm, user.midNameMdm) &&
            Objects.equals(this.sexMdm, user.sexMdm) &&
            Objects.equals(this.ageMdm, user.ageMdm) &&
            Objects.equals(this.firstNameSpgr, user.firstNameSpgr) &&
            Objects.equals(this.midNameSpgr, user.midNameSpgr) &&
            Objects.equals(this.lastNameSpgr, user.lastNameSpgr) &&
            Objects.equals(this.ageSpgr, user.ageSpgr) &&
            Objects.equals(this.sexSpgr, user.sexSpgr) &&
            Objects.equals(this.isFirstNameEqual, user.isFirstNameEqual) &&
            Objects.equals(this.isFirstLastNameEqual, user.isFirstLastNameEqual);
  }

  @Override
  public int hashCode() {
    return Objects.hash(msisdn, lastNameMdm, firstNameMdm, midNameMdm, sexMdm, ageMdm, firstNameSpgr, midNameSpgr, lastNameSpgr, ageSpgr, sexSpgr, isFirstNameEqual, isFirstLastNameEqual);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");

    sb.append("    msisdn: ").append(toIndentedString(msisdn)).append("\n");
    sb.append("    lastNameMdm: ").append(toIndentedString(lastNameMdm)).append("\n");
    sb.append("    firstNameMdm: ").append(toIndentedString(firstNameMdm)).append("\n");
    sb.append("    midNameMdm: ").append(toIndentedString(midNameMdm)).append("\n");
    sb.append("    sexMdm: ").append(toIndentedString(sexMdm)).append("\n");
    sb.append("    ageMdm: ").append(toIndentedString(ageMdm)).append("\n");
    sb.append("    firstNameSpgr: ").append(toIndentedString(firstNameSpgr)).append("\n");
    sb.append("    midNameSpgr: ").append(toIndentedString(midNameSpgr)).append("\n");
    sb.append("    lastNameSpgr: ").append(toIndentedString(lastNameSpgr)).append("\n");
    sb.append("    ageSpgr: ").append(toIndentedString(ageSpgr)).append("\n");
    sb.append("    sexSpgr: ").append(toIndentedString(sexSpgr)).append("\n");
    sb.append("    isFirstNameEqual: ").append(toIndentedString(isFirstNameEqual)).append("\n");
    sb.append("    isFirstLastNameEqual: ").append(toIndentedString(isFirstLastNameEqual)).append("\n");
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

