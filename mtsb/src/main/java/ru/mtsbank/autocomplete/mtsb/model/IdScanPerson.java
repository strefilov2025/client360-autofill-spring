package ru.mtsbank.autocomplete.mtsb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * Объект IdScanPerson.  Описывает формат ответа для endpoint&#39;a по запросу персональных данных для продукта ID Scan.
 */
@ApiModel(description = "Объект IdScanPerson.  Описывает формат ответа для endpoint'a по запросу персональных данных для продукта ID Scan.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-07-13T16:33:10.240228500+03:00[Europe/Moscow]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IdScanPerson {
  @JsonProperty("msisdn")
  private String msisdn;

  @JsonProperty("last_name")
  private String lastName;

  @JsonProperty("first_name")
  private String firstName;

  @JsonProperty("mid_name")
  private String midName;

  @JsonProperty("birthdate")
  private Date birthdate;

  @JsonProperty("doc_type")
  private DocTypeEnum docType = null;

  @JsonProperty("doc_series")
  private String docSeries;

  @JsonProperty("doc_number")
  private String docNumber;

  @JsonProperty("inn")
  private String inn;

  @JsonProperty("fns_check_date")
  private Date fnsCheckDate;

  @JsonProperty("fns_check_flag")
  private FnsCheckFlag fnsCheckFlag = null;

  public IdScanPerson msisdn(String msisdn) {
    this.msisdn = msisdn;
    return this;
  }

  /**
   * Get msisdn
   *
   * @return msisdn
   */
  @ApiModelProperty(example = "7XXXXXXXXXX", required = true, value = "")
  @NotNull


  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public IdScanPerson lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   *
   * @return lastName
   */
  @ApiModelProperty(value = "")


  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public IdScanPerson firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   *
   * @return firstName
   */
  @ApiModelProperty(value = "")


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public IdScanPerson midName(String midName) {
    this.midName = midName;
    return this;
  }

  /**
   * Get midName
   *
   * @return midName
   */
  @ApiModelProperty(value = "")


  public String getMidName() {
    return midName;
  }

  public void setMidName(String midName) {
    this.midName = midName;
  }

  public IdScanPerson birthdate(Date birthdate) {
    this.birthdate = birthdate;
    return this;
  }

  /**
   * Get birthdate
   *
   * @return birthdate
   */
  @ApiModelProperty(value = "")

  @Valid

  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  public IdScanPerson docType(DocTypeEnum docType) {
    this.docType = docType;
    return this;
  }

  /**
   * Get docType
   *
   * @return docType
   */
  @ApiModelProperty(value = "")

  @Valid

  public DocTypeEnum getDocType() {
    return docType;
  }

  public void setDocType(DocTypeEnum docType) {
    this.docType = docType;
  }

  public IdScanPerson docSeries(String docSeries) {
    this.docSeries = docSeries;
    return this;
  }

  /**
   * Get docSeries
   *
   * @return docSeries
   */
  @ApiModelProperty(value = "")


  public String getDocSeries() {
    return docSeries;
  }

  public void setDocSeries(String docSeries) {
    this.docSeries = docSeries;
  }

  public IdScanPerson docNumber(String docNumber) {
    this.docNumber = docNumber;
    return this;
  }

  /**
   * Get docNumber
   *
   * @return docNumber
   */
  @ApiModelProperty(value = "")


  public String getDocNumber() {
    return docNumber;
  }

  public void setDocNumber(String docNumber) {
    this.docNumber = docNumber;
  }

  public IdScanPerson inn(String inn) {
    this.inn = inn;
    return this;
  }

  /**
   * Get inn
   *
   * @return inn
   */
  @ApiModelProperty(value = "")


  public String getInn() {
    return inn;
  }

  public void setInn(String inn) {
    this.inn = inn;
  }

  public IdScanPerson fnsCheckDate(Date fnsCheckDate) {
    this.fnsCheckDate = fnsCheckDate;
    return this;
  }

  /**
   * Get fnsCheckDate
   *
   * @return fnsCheckDate
   */
  @ApiModelProperty(value = "")

  @Valid

  public Date getFnsCheckDate() {
    return fnsCheckDate;
  }

  public void setFnsCheckDate(Date fnsCheckDate) {
    this.fnsCheckDate = fnsCheckDate;
  }

  public IdScanPerson fnsCheckFlag(FnsCheckFlag fnsCheckFlag) {
    this.fnsCheckFlag = fnsCheckFlag;
    return this;
  }

  /**
   * Get fnsCheckFlag
   *
   * @return fnsCheckFlag
   */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public FnsCheckFlag getFnsCheckFlag() {
    return fnsCheckFlag;
  }

  public void setFnsCheckFlag(FnsCheckFlag fnsCheckFlag) {
    this.fnsCheckFlag = fnsCheckFlag;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IdScanPerson idScanPerson = (IdScanPerson) o;
    return Objects.equals(this.msisdn, idScanPerson.msisdn) &&
            Objects.equals(this.lastName, idScanPerson.lastName) &&
            Objects.equals(this.firstName, idScanPerson.firstName) &&
            Objects.equals(this.midName, idScanPerson.midName) &&
            Objects.equals(this.birthdate, idScanPerson.birthdate) &&
            Objects.equals(this.docType, idScanPerson.docType) &&
            Objects.equals(this.docSeries, idScanPerson.docSeries) &&
            Objects.equals(this.docNumber, idScanPerson.docNumber) &&
            Objects.equals(this.inn, idScanPerson.inn) &&
            Objects.equals(this.fnsCheckDate, idScanPerson.fnsCheckDate) &&
            Objects.equals(this.fnsCheckFlag, idScanPerson.fnsCheckFlag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(msisdn, lastName, firstName, midName, birthdate, docType, docSeries, docNumber, inn, fnsCheckDate, fnsCheckFlag);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IdScanPerson {\n");

    sb.append("    msisdn: ").append(toIndentedString(msisdn)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    midName: ").append(toIndentedString(midName)).append("\n");
    sb.append("    birthdate: ").append(toIndentedString(birthdate)).append("\n");
    sb.append("    docType: ").append(toIndentedString(docType)).append("\n");
    sb.append("    docSeries: ").append(toIndentedString(docSeries)).append("\n");
    sb.append("    docNumber: ").append(toIndentedString(docNumber)).append("\n");
    sb.append("    inn: ").append(toIndentedString(inn)).append("\n");
    sb.append("    fnsCheckDate: ").append(toIndentedString(fnsCheckDate)).append("\n");
    sb.append("    fnsCheckFlag: ").append(toIndentedString(fnsCheckFlag)).append("\n");
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

