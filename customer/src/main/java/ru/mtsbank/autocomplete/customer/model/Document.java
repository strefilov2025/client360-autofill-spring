package ru.mtsbank.autocomplete.customer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Документ
 */
@ApiModel(description = "Документ")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-06-28T14:03:31.041683100+03:00[Europe/Moscow]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Document implements Serializable {
  private static final long serialVersionUID = 4626863731861881362L;
  @JsonProperty("primary")
  private Boolean primary;

  @JsonProperty("type")
  private String type;

  @JsonProperty("series")
  private String series;

  @JsonProperty("number")
  private String number;
  @JsonFormat(pattern = "yyyy-MM-dd")
  @JsonProperty("issueDate")
  private Date issueDate;

  @JsonProperty("issuedBy")
  private String issuedBy;

  @JsonProperty("departCode")
  private String departCode;

  @JsonProperty("valid")
  private Boolean valid;
  @JsonFormat(pattern = "yyyy-MM-dd")
  @JsonProperty("expiryDate")
  private Date expiryDate;

  public Document primary(Boolean primary) {
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

  public Document type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Тип документа
   * @return type
   */
  @ApiModelProperty(value = "Тип документа")


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Document series(String series) {
    this.series = series;
    return this;
  }

  /**
   * Серия
   * @return series
   */
  @ApiModelProperty(value = "Серия")


  public String getSeries() {
    return series;
  }

  public void setSeries(String series) {
    this.series = series;
  }

  public Document number(String number) {
    this.number = number;
    return this;
  }

  /**
   * Номер
   * @return number
   */
  @ApiModelProperty(value = "Номер")


  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public Document issueDate(Date issueDate) {
    this.issueDate = issueDate;
    return this;
  }

  /**
   * Дата выдачи
   * @return issueDate
   */
  @ApiModelProperty(readOnly = true, value = "Дата выдачи")

  @Valid

  public Date getIssueDate() {
    return issueDate;
  }

  public void setIssueDate(Date issueDate) {
    this.issueDate = issueDate;
  }

  public Document issuedBy(String issuedBy) {
    this.issuedBy = issuedBy;
    return this;
  }

  /**
   * Кем выдан
   * @return issuedBy
   */
  @ApiModelProperty(readOnly = true, value = "Кем выдан")


  public String getIssuedBy() {
    return issuedBy;
  }

  public void setIssuedBy(String issuedBy) {
    this.issuedBy = issuedBy;
  }

  public Document departCode(String departCode) {
    this.departCode = departCode;
    return this;
  }

  /**
   * Код подразделения
   * @return departCode
   */
  @ApiModelProperty(readOnly = true, value = "Код подразделения")


  public String getDepartCode() {
    return departCode;
  }

  public void setDepartCode(String departCode) {
    this.departCode = departCode;
  }

  public Document valid(Boolean valid) {
    this.valid = valid;
    return this;
  }

  /**
   * Признак действительности
   *
   * @return valid
   */
  @ApiModelProperty(readOnly = true, value = "Признак действительности")


  public Boolean getValid() {
    return valid;
  }

  public void setValid(Boolean valid) {
    this.valid = valid;
  }

  public Document expiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
    return this;
  }

  /**
   * Дата окончания действия
   *
   * @return expiryDate
   */
  @ApiModelProperty(readOnly = true, value = "Дата окончания действия")

  @Valid

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Document document = (Document) o;
    return Objects.equals(this.primary, document.primary) &&
            Objects.equals(this.type, document.type) &&
            Objects.equals(this.series, document.series) &&
            Objects.equals(this.number, document.number) &&
            Objects.equals(this.issueDate, document.issueDate) &&
            Objects.equals(this.issuedBy, document.issuedBy) &&
            Objects.equals(this.departCode, document.departCode) &&
            Objects.equals(this.valid, document.valid) &&
            Objects.equals(this.expiryDate, document.expiryDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(primary, type, series, number, issueDate, issuedBy, departCode, valid, expiryDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Document {\n");

    sb.append("    primary: ").append(toIndentedString(primary)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    series: ").append(toIndentedString(series)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    issueDate: ").append(toIndentedString(issueDate)).append("\n");
    sb.append("    issuedBy: ").append(toIndentedString(issuedBy)).append("\n");
    sb.append("    departCode: ").append(toIndentedString(departCode)).append("\n");
    sb.append("    valid: ").append(toIndentedString(valid)).append("\n");
    sb.append("    expiryDate: ").append(toIndentedString(expiryDate)).append("\n");
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
