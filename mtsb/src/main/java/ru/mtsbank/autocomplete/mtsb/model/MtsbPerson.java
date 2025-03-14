package ru.mtsbank.autocomplete.mtsb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.Date;
import java.util.Objects;

/**
 * Объект MtsbPerson.  Описывает формат ответа для endpoint&#39;a по запросу персональных данных для продукта МТСБ.
 */
@ApiModel(description = "Объект MtsbPerson.  Описывает формат ответа для endpoint'a по запросу персональных данных для продукта МТСБ.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-07-13T16:33:10.240228500+03:00[Europe/Moscow]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MtsbPerson {
  @JsonProperty("doc_type")
  private String docType;

  @JsonProperty("doc_series")
  private String docSeries;

  @JsonProperty("doc_number")
  private String docNumber;

  @JsonProperty("doc_issue_dt")
  private Date docIssueDt;

  @JsonProperty("doc_issue_authority")
  private String docIssueAuthority;

  @JsonProperty("doc_department_code")
  private String docDepartmentCode;

  @JsonProperty("inn_number")
  private String innNumber;

  @JsonProperty("cnt_sim")
  private Integer cntSim;

  @JsonProperty("birthdate_dt")
  private Date birthdateDt;

  @JsonProperty("birth_place")
  private String birthPlace;

  @JsonProperty("last_name_mdm")
  private String lastNameMdm;

  @JsonProperty("first_name_mdm")
  private String firstNameMdm;

  @JsonProperty("mid_name_mdm")
  private String midNameMdm;

  @JsonProperty("last_name_spgr")
  private String lastNameSpgr;

  @JsonProperty("first_name_spgr")
  private String firstNameSpgr;

  @JsonProperty("mid_name_spgr")
  private String midNameSpgr;

  @JsonProperty("is_first_name_equal")
  private Integer isFirstNameEqual;

  @JsonProperty("is_first_last_name_equal")
  private Integer isFirstLastNameEqual;

  @JsonProperty("home_actuality_date")
  private Date homeActualityDate;

  @JsonProperty("home_primary_flag")
  private Integer homePrimaryFlag;

  @JsonProperty("home_raw_source")
  private String homeRawSource;

  @JsonProperty("home_postalcode")
  private String homePostalcode;

  @JsonProperty("home_country")
  private String homeCountry;

  @JsonProperty("home_regiontype")
  private String homeRegiontype;

  @JsonProperty("home_region")
  private String homeRegion;

  @JsonProperty("home_rayontype")
  private String homeRayontype;

  @JsonProperty("home_rayon")
  private String homeRayon;

  @JsonProperty("home_citytype")
  private String homeCitytype;

  @JsonProperty("home_city")
  private String homeCity;

  @JsonProperty("home_settlementtype")
  private String homeSettlementtype;

  @JsonProperty("home_settlement")
  private String homeSettlement;

  @JsonProperty("home_streettype")
  private String homeStreettype;

  @JsonProperty("home_street")
  private String homeStreet;

  @JsonProperty("home_housenumber")
  private String homeHousenumber;

  @JsonProperty("home_korpus")
  private String homeKorpus;

  @JsonProperty("home_stroenie")
  private String homeStroenie;

  @JsonProperty("home_flat")
  private String homeFlat;

  @JsonProperty("home_kladrcode")
  private String homeKladrcode;

  @JsonProperty("home_okatocode")
  private String homeOkatocode;

  @JsonProperty("home_fias_code")
  private String homeFiasCode;

  @JsonProperty("home_fias_level")
  private String homeFiasLevel;

  @JsonProperty("registration_actuality_date")
  private Date registrationActualityDate;

  @JsonProperty("registration_primary_flag")
  private Integer registrationPrimaryFlag;

  @JsonProperty("registration_raw_source")
  private String registrationRawSource;

  @JsonProperty("registration_postalcode")
  private String registrationPostalcode;

  @JsonProperty("registration_country")
  private String registrationCountry;

  @JsonProperty("registration_regiontype")
  private String registrationRegiontype;

  @JsonProperty("registration_region")
  private String registrationRegion;

  @JsonProperty("registration_rayontype")
  private String registrationRayontype;

  @JsonProperty("registration_rayon")
  private String registrationRayon;

  @JsonProperty("registration_citytype")
  private String registrationCitytype;

  @JsonProperty("registration_city")
  private String registrationCity;

  @JsonProperty("registration_settlementtype")
  private String registrationSettlementtype;

  @JsonProperty("registration_settlement")
  private String registrationSettlement;

  @JsonProperty("registration_streettype")
  private String registrationStreettype;

  @JsonProperty("registration_street")
  private String registrationStreet;

  @JsonProperty("registration_housenumber")
  private String registrationHousenumber;

  @JsonProperty("registration_korpus")
  private String registrationKorpus;

  @JsonProperty("registration_stroenie")
  private String registrationStroenie;

  @JsonProperty("registration_flat")
  private String registrationFlat;

  @JsonProperty("registration_kladrcode")
  private String registrationKladrcode;

  @JsonProperty("registration_okatocode")
  private String registrationOkatocode;

  @JsonProperty("registration_fias_code")
  private String registrationFiasCode;

  @JsonProperty("registration_fias_level")
  private String registrationFiasLevel;

  @JsonProperty("constant_registration_actuality_date")
  private Date constantRegistrationActualityDate;

  @JsonProperty("constant_registration_primary_flag")
  private Integer constantRegistrationPrimaryFlag;

  @JsonProperty("constant_registration_raw_source")
  private String constantRegistrationRawSource;

  @JsonProperty("constant_registration_postalcode")
  private String constantRegistrationPostalcode;

  @JsonProperty("constant_registration_country")
  private String constantRegistrationCountry;

  @JsonProperty("constant_registration_regiontype")
  private String constantRegistrationRegiontype;

  @JsonProperty("constant_registration_region")
  private String constantRegistrationRegion;

  @JsonProperty("constant_registration_rayontype")
  private String constantRegistrationRayontype;

  @JsonProperty("constant_registration_rayon")
  private String constantRegistrationRayon;

  @JsonProperty("constant_registration_citytype")
  private String constantRegistrationCitytype;

  @JsonProperty("constant_registration_city")
  private String constantRegistrationCity;

  @JsonProperty("constant_registration_settlementtype")
  private String constantRegistrationSettlementtype;

  @JsonProperty("constant_registration_settlement")
  private String constantRegistrationSettlement;

  @JsonProperty("constant_registration_streettype")
  private String constantRegistrationStreettype;

  @JsonProperty("constant_registration_street")
  private String constantRegistrationStreet;

  @JsonProperty("constant_registration_housenumber")
  private String constantRegistrationHousenumber;

  @JsonProperty("constant_registration_korpus")
  private String constantRegistrationKorpus;

  @JsonProperty("constant_registration_stroenie")
  private String constantRegistrationStroenie;

  @JsonProperty("constant_registration_flat")
  private String constantRegistrationFlat;

  @JsonProperty("constant_registration_kladrcode")
  private String constantRegistrationKladrcode;

  @JsonProperty("constant_registration_okatocode")
  private String constantRegistrationOkatocode;

  @JsonProperty("constant_registration_fias_code")
  private String constantRegistrationFiasCode;

  @JsonProperty("constant_registration_fias_level")
  private String constantRegistrationFiasLevel;

  public MtsbPerson docType(String docType) {
    this.docType = docType;
    return this;
  }

  /**
   * Get docType
   *
   * @return docType
   */
  @ApiModelProperty(value = "")


  public String getDocType() {
    return docType;
  }

  public void setDocType(String docType) {
    this.docType = docType;
  }

  public MtsbPerson docSeries(String docSeries) {
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

  public MtsbPerson docNumber(String docNumber) {
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

  public MtsbPerson docIssueDt(Date docIssueDt) {
    this.docIssueDt = docIssueDt;
    return this;
  }

  /**
   * Get docIssueDt
   *
   * @return docIssueDt
   */
  @ApiModelProperty(value = "")

  @Valid

  public Date getDocIssueDt() {
    return docIssueDt;
  }

  public void setDocIssueDt(Date docIssueDt) {
    this.docIssueDt = docIssueDt;
  }

  public MtsbPerson docIssueAuthority(String docIssueAuthority) {
    this.docIssueAuthority = docIssueAuthority;
    return this;
  }

  /**
   * Get docIssueAuthority
   *
   * @return docIssueAuthority
   */
  @ApiModelProperty(value = "")


  public String getDocIssueAuthority() {
    return docIssueAuthority;
  }

  public void setDocIssueAuthority(String docIssueAuthority) {
    this.docIssueAuthority = docIssueAuthority;
  }

  public MtsbPerson docDepartmentCode(String docDepartmentCode) {
    this.docDepartmentCode = docDepartmentCode;
    return this;
  }

  /**
   * Get docDepartmentCode
   *
   * @return docDepartmentCode
   */
  @ApiModelProperty(value = "")


  public String getDocDepartmentCode() {
    return docDepartmentCode;
  }

  public void setDocDepartmentCode(String docDepartmentCode) {
    this.docDepartmentCode = docDepartmentCode;
  }

  public MtsbPerson innNumber(String innNumber) {
    this.innNumber = innNumber;
    return this;
  }

  /**
   * Get innNumber
   *
   * @return innNumber
   */
  @ApiModelProperty(value = "")


  public String getInnNumber() {
    return innNumber;
  }

  public void setInnNumber(String innNumber) {
    this.innNumber = innNumber;
  }

  public MtsbPerson cntSim(Integer cntSim) {
    this.cntSim = cntSim;
    return this;
  }

  /**
   * Get cntSim
   *
   * @return cntSim
   */
  @ApiModelProperty(value = "")


  public Integer getCntSim() {
    return cntSim;
  }

  public void setCntSim(Integer cntSim) {
    this.cntSim = cntSim;
  }

  public MtsbPerson birthdateDt(Date birthdateDt) {
    this.birthdateDt = birthdateDt;
    return this;
  }

  /**
   * Get birthdateDt
   *
   * @return birthdateDt
   */
  @ApiModelProperty(value = "")

  @Valid

  public Date getBirthdateDt() {
    return birthdateDt;
  }

  public void setBirthdateDt(Date birthdateDt) {
    this.birthdateDt = birthdateDt;
  }

  public MtsbPerson birthPlace(String birthPlace) {
    this.birthPlace = birthPlace;
    return this;
  }

  /**
   * Get birthPlace
   *
   * @return birthPlace
   */
  @ApiModelProperty(value = "")


  public String getBirthPlace() {
    return birthPlace;
  }

  public void setBirthPlace(String birthPlace) {
    this.birthPlace = birthPlace;
  }

  public MtsbPerson lastNameMdm(String lastNameMdm) {
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

  public MtsbPerson firstNameMdm(String firstNameMdm) {
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

  public MtsbPerson midNameMdm(String midNameMdm) {
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

  public MtsbPerson lastNameSpgr(String lastNameSpgr) {
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

  public MtsbPerson firstNameSpgr(String firstNameSpgr) {
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

  public MtsbPerson midNameSpgr(String midNameSpgr) {
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

  public MtsbPerson isFirstNameEqual(Integer isFirstNameEqual) {
    this.isFirstNameEqual = isFirstNameEqual;
    return this;
  }

  /**
   * Get isFirstNameEqual
   *
   * @return isFirstNameEqual
   */
  @ApiModelProperty(value = "")


  public Integer getIsFirstNameEqual() {
    return isFirstNameEqual;
  }

  public void setIsFirstNameEqual(Integer isFirstNameEqual) {
    this.isFirstNameEqual = isFirstNameEqual;
  }

  public MtsbPerson isFirstLastNameEqual(Integer isFirstLastNameEqual) {
    this.isFirstLastNameEqual = isFirstLastNameEqual;
    return this;
  }

  /**
   * Get isFirstLastNameEqual
   *
   * @return isFirstLastNameEqual
   */
  @ApiModelProperty(value = "")


  public Integer getIsFirstLastNameEqual() {
    return isFirstLastNameEqual;
  }

  public void setIsFirstLastNameEqual(Integer isFirstLastNameEqual) {
    this.isFirstLastNameEqual = isFirstLastNameEqual;
  }

  public MtsbPerson homeActualityDate(Date homeActualityDate) {
    this.homeActualityDate = homeActualityDate;
    return this;
  }

  /**
   * Get homeActualityDate
   *
   * @return homeActualityDate
   */
  @ApiModelProperty(value = "")

  @Valid

  public Date getHomeActualityDate() {
    return homeActualityDate;
  }

  public void setHomeActualityDate(Date homeActualityDate) {
    this.homeActualityDate = homeActualityDate;
  }

  public MtsbPerson homePrimaryFlag(Integer homePrimaryFlag) {
    this.homePrimaryFlag = homePrimaryFlag;
    return this;
  }

  /**
   * Get homePrimaryFlag
   *
   * @return homePrimaryFlag
   */
  @ApiModelProperty(value = "")


  public Integer getHomePrimaryFlag() {
    return homePrimaryFlag;
  }

  public void setHomePrimaryFlag(Integer homePrimaryFlag) {
    this.homePrimaryFlag = homePrimaryFlag;
  }

  public MtsbPerson homeRawSource(String homeRawSource) {
    this.homeRawSource = homeRawSource;
    return this;
  }

  /**
   * Get homeRawSource
   *
   * @return homeRawSource
   */
  @ApiModelProperty(value = "")


  public String getHomeRawSource() {
    return homeRawSource;
  }

  public void setHomeRawSource(String homeRawSource) {
    this.homeRawSource = homeRawSource;
  }

  public MtsbPerson homePostalcode(String homePostalcode) {
    this.homePostalcode = homePostalcode;
    return this;
  }

  /**
   * Get homePostalcode
   *
   * @return homePostalcode
   */
  @ApiModelProperty(value = "")


  public String getHomePostalcode() {
    return homePostalcode;
  }

  public void setHomePostalcode(String homePostalcode) {
    this.homePostalcode = homePostalcode;
  }

  public MtsbPerson homeCountry(String homeCountry) {
    this.homeCountry = homeCountry;
    return this;
  }

  /**
   * Get homeCountry
   *
   * @return homeCountry
   */
  @ApiModelProperty(value = "")


  public String getHomeCountry() {
    return homeCountry;
  }

  public void setHomeCountry(String homeCountry) {
    this.homeCountry = homeCountry;
  }

  public MtsbPerson homeRegiontype(String homeRegiontype) {
    this.homeRegiontype = homeRegiontype;
    return this;
  }

  /**
   * Get homeRegiontype
   *
   * @return homeRegiontype
   */
  @ApiModelProperty(value = "")


  public String getHomeRegiontype() {
    return homeRegiontype;
  }

  public void setHomeRegiontype(String homeRegiontype) {
    this.homeRegiontype = homeRegiontype;
  }

  public MtsbPerson homeRegion(String homeRegion) {
    this.homeRegion = homeRegion;
    return this;
  }

  /**
   * Get homeRegion
   *
   * @return homeRegion
   */
  @ApiModelProperty(value = "")


  public String getHomeRegion() {
    return homeRegion;
  }

  public void setHomeRegion(String homeRegion) {
    this.homeRegion = homeRegion;
  }

  public MtsbPerson homeRayontype(String homeRayontype) {
    this.homeRayontype = homeRayontype;
    return this;
  }

  /**
   * Get homeRayontype
   *
   * @return homeRayontype
   */
  @ApiModelProperty(value = "")


  public String getHomeRayontype() {
    return homeRayontype;
  }

  public void setHomeRayontype(String homeRayontype) {
    this.homeRayontype = homeRayontype;
  }

  public MtsbPerson homeRayon(String homeRayon) {
    this.homeRayon = homeRayon;
    return this;
  }

  /**
   * Get homeRayon
   *
   * @return homeRayon
   */
  @ApiModelProperty(value = "")


  public String getHomeRayon() {
    return homeRayon;
  }

  public void setHomeRayon(String homeRayon) {
    this.homeRayon = homeRayon;
  }

  public MtsbPerson homeCitytype(String homeCitytype) {
    this.homeCitytype = homeCitytype;
    return this;
  }

  /**
   * Get homeCitytype
   *
   * @return homeCitytype
   */
  @ApiModelProperty(value = "")


  public String getHomeCitytype() {
    return homeCitytype;
  }

  public void setHomeCitytype(String homeCitytype) {
    this.homeCitytype = homeCitytype;
  }

  public MtsbPerson homeCity(String homeCity) {
    this.homeCity = homeCity;
    return this;
  }

  /**
   * Get homeCity
   *
   * @return homeCity
   */
  @ApiModelProperty(value = "")


  public String getHomeCity() {
    return homeCity;
  }

  public void setHomeCity(String homeCity) {
    this.homeCity = homeCity;
  }

  public MtsbPerson homeSettlementtype(String homeSettlementtype) {
    this.homeSettlementtype = homeSettlementtype;
    return this;
  }

  /**
   * Get homeSettlementtype
   *
   * @return homeSettlementtype
   */
  @ApiModelProperty(value = "")


  public String getHomeSettlementtype() {
    return homeSettlementtype;
  }

  public void setHomeSettlementtype(String homeSettlementtype) {
    this.homeSettlementtype = homeSettlementtype;
  }

  public MtsbPerson homeSettlement(String homeSettlement) {
    this.homeSettlement = homeSettlement;
    return this;
  }

  /**
   * Get homeSettlement
   *
   * @return homeSettlement
   */
  @ApiModelProperty(value = "")


  public String getHomeSettlement() {
    return homeSettlement;
  }

  public void setHomeSettlement(String homeSettlement) {
    this.homeSettlement = homeSettlement;
  }

  public MtsbPerson homeStreettype(String homeStreettype) {
    this.homeStreettype = homeStreettype;
    return this;
  }

  /**
   * Get homeStreettype
   *
   * @return homeStreettype
   */
  @ApiModelProperty(value = "")


  public String getHomeStreettype() {
    return homeStreettype;
  }

  public void setHomeStreettype(String homeStreettype) {
    this.homeStreettype = homeStreettype;
  }

  public MtsbPerson homeStreet(String homeStreet) {
    this.homeStreet = homeStreet;
    return this;
  }

  /**
   * Get homeStreet
   *
   * @return homeStreet
   */
  @ApiModelProperty(value = "")


  public String getHomeStreet() {
    return homeStreet;
  }

  public void setHomeStreet(String homeStreet) {
    this.homeStreet = homeStreet;
  }

  public MtsbPerson homeHousenumber(String homeHousenumber) {
    this.homeHousenumber = homeHousenumber;
    return this;
  }

  /**
   * Get homeHousenumber
   *
   * @return homeHousenumber
   */
  @ApiModelProperty(value = "")


  public String getHomeHousenumber() {
    return homeHousenumber;
  }

  public void setHomeHousenumber(String homeHousenumber) {
    this.homeHousenumber = homeHousenumber;
  }

  public MtsbPerson homeKorpus(String homeKorpus) {
    this.homeKorpus = homeKorpus;
    return this;
  }

  /**
   * Get homeKorpus
   *
   * @return homeKorpus
   */
  @ApiModelProperty(value = "")


  public String getHomeKorpus() {
    return homeKorpus;
  }

  public void setHomeKorpus(String homeKorpus) {
    this.homeKorpus = homeKorpus;
  }

  public MtsbPerson homeStroenie(String homeStroenie) {
    this.homeStroenie = homeStroenie;
    return this;
  }

  /**
   * Get homeStroenie
   *
   * @return homeStroenie
   */
  @ApiModelProperty(value = "")


  public String getHomeStroenie() {
    return homeStroenie;
  }

  public void setHomeStroenie(String homeStroenie) {
    this.homeStroenie = homeStroenie;
  }

  public MtsbPerson homeFlat(String homeFlat) {
    this.homeFlat = homeFlat;
    return this;
  }

  /**
   * Get homeFlat
   *
   * @return homeFlat
   */
  @ApiModelProperty(value = "")


  public String getHomeFlat() {
    return homeFlat;
  }

  public void setHomeFlat(String homeFlat) {
    this.homeFlat = homeFlat;
  }

  public MtsbPerson homeKladrcode(String homeKladrcode) {
    this.homeKladrcode = homeKladrcode;
    return this;
  }

  /**
   * Get homeKladrcode
   *
   * @return homeKladrcode
   */
  @ApiModelProperty(value = "")


  public String getHomeKladrcode() {
    return homeKladrcode;
  }

  public void setHomeKladrcode(String homeKladrcode) {
    this.homeKladrcode = homeKladrcode;
  }

  public MtsbPerson homeOkatocode(String homeOkatocode) {
    this.homeOkatocode = homeOkatocode;
    return this;
  }

  /**
   * Get homeOkatocode
   *
   * @return homeOkatocode
   */
  @ApiModelProperty(value = "")


  public String getHomeOkatocode() {
    return homeOkatocode;
  }

  public void setHomeOkatocode(String homeOkatocode) {
    this.homeOkatocode = homeOkatocode;
  }

  public MtsbPerson homeFiasCode(String homeFiasCode) {
    this.homeFiasCode = homeFiasCode;
    return this;
  }

  /**
   * Get homeFiasCode
   *
   * @return homeFiasCode
   */
  @ApiModelProperty(value = "")


  public String getHomeFiasCode() {
    return homeFiasCode;
  }

  public void setHomeFiasCode(String homeFiasCode) {
    this.homeFiasCode = homeFiasCode;
  }

  public MtsbPerson homeFiasLevel(String homeFiasLevel) {
    this.homeFiasLevel = homeFiasLevel;
    return this;
  }

  /**
   * Get homeFiasLevel
   *
   * @return homeFiasLevel
   */
  @ApiModelProperty(value = "")


  public String getHomeFiasLevel() {
    return homeFiasLevel;
  }

  public void setHomeFiasLevel(String homeFiasLevel) {
    this.homeFiasLevel = homeFiasLevel;
  }

  public MtsbPerson registrationActualityDate(Date registrationActualityDate) {
    this.registrationActualityDate = registrationActualityDate;
    return this;
  }

  /**
   * Get registrationActualityDate
   *
   * @return registrationActualityDate
   */
  @ApiModelProperty(value = "")

  @Valid

  public Date getRegistrationActualityDate() {
    return registrationActualityDate;
  }

  public void setRegistrationActualityDate(Date registrationActualityDate) {
    this.registrationActualityDate = registrationActualityDate;
  }

  public MtsbPerson registrationPrimaryFlag(Integer registrationPrimaryFlag) {
    this.registrationPrimaryFlag = registrationPrimaryFlag;
    return this;
  }

  /**
   * Get registrationPrimaryFlag
   *
   * @return registrationPrimaryFlag
   */
  @ApiModelProperty(value = "")


  public Integer getRegistrationPrimaryFlag() {
    return registrationPrimaryFlag;
  }

  public void setRegistrationPrimaryFlag(Integer registrationPrimaryFlag) {
    this.registrationPrimaryFlag = registrationPrimaryFlag;
  }

  public MtsbPerson registrationRawSource(String registrationRawSource) {
    this.registrationRawSource = registrationRawSource;
    return this;
  }

  /**
   * Get registrationRawSource
   *
   * @return registrationRawSource
   */
  @ApiModelProperty(value = "")


  public String getRegistrationRawSource() {
    return registrationRawSource;
  }

  public void setRegistrationRawSource(String registrationRawSource) {
    this.registrationRawSource = registrationRawSource;
  }

  public MtsbPerson registrationPostalcode(String registrationPostalcode) {
    this.registrationPostalcode = registrationPostalcode;
    return this;
  }

  /**
   * Get registrationPostalcode
   *
   * @return registrationPostalcode
   */
  @ApiModelProperty(value = "")


  public String getRegistrationPostalcode() {
    return registrationPostalcode;
  }

  public void setRegistrationPostalcode(String registrationPostalcode) {
    this.registrationPostalcode = registrationPostalcode;
  }

  public MtsbPerson registrationCountry(String registrationCountry) {
    this.registrationCountry = registrationCountry;
    return this;
  }

  /**
   * Get registrationCountry
   *
   * @return registrationCountry
   */
  @ApiModelProperty(value = "")


  public String getRegistrationCountry() {
    return registrationCountry;
  }

  public void setRegistrationCountry(String registrationCountry) {
    this.registrationCountry = registrationCountry;
  }

  public MtsbPerson registrationRegiontype(String registrationRegiontype) {
    this.registrationRegiontype = registrationRegiontype;
    return this;
  }

  /**
   * Get registrationRegiontype
   *
   * @return registrationRegiontype
   */
  @ApiModelProperty(value = "")


  public String getRegistrationRegiontype() {
    return registrationRegiontype;
  }

  public void setRegistrationRegiontype(String registrationRegiontype) {
    this.registrationRegiontype = registrationRegiontype;
  }

  public MtsbPerson registrationRegion(String registrationRegion) {
    this.registrationRegion = registrationRegion;
    return this;
  }

  /**
   * Get registrationRegion
   *
   * @return registrationRegion
   */
  @ApiModelProperty(value = "")


  public String getRegistrationRegion() {
    return registrationRegion;
  }

  public void setRegistrationRegion(String registrationRegion) {
    this.registrationRegion = registrationRegion;
  }

  public MtsbPerson registrationRayontype(String registrationRayontype) {
    this.registrationRayontype = registrationRayontype;
    return this;
  }

  /**
   * Get registrationRayontype
   *
   * @return registrationRayontype
   */
  @ApiModelProperty(value = "")


  public String getRegistrationRayontype() {
    return registrationRayontype;
  }

  public void setRegistrationRayontype(String registrationRayontype) {
    this.registrationRayontype = registrationRayontype;
  }

  public MtsbPerson registrationRayon(String registrationRayon) {
    this.registrationRayon = registrationRayon;
    return this;
  }

  /**
   * Get registrationRayon
   *
   * @return registrationRayon
   */
  @ApiModelProperty(value = "")


  public String getRegistrationRayon() {
    return registrationRayon;
  }

  public void setRegistrationRayon(String registrationRayon) {
    this.registrationRayon = registrationRayon;
  }

  public MtsbPerson registrationCitytype(String registrationCitytype) {
    this.registrationCitytype = registrationCitytype;
    return this;
  }

  /**
   * Get registrationCitytype
   *
   * @return registrationCitytype
   */
  @ApiModelProperty(value = "")


  public String getRegistrationCitytype() {
    return registrationCitytype;
  }

  public void setRegistrationCitytype(String registrationCitytype) {
    this.registrationCitytype = registrationCitytype;
  }

  public MtsbPerson registrationCity(String registrationCity) {
    this.registrationCity = registrationCity;
    return this;
  }

  /**
   * Get registrationCity
   *
   * @return registrationCity
   */
  @ApiModelProperty(value = "")


  public String getRegistrationCity() {
    return registrationCity;
  }

  public void setRegistrationCity(String registrationCity) {
    this.registrationCity = registrationCity;
  }

  public MtsbPerson registrationSettlementtype(String registrationSettlementtype) {
    this.registrationSettlementtype = registrationSettlementtype;
    return this;
  }

  /**
   * Get registrationSettlementtype
   *
   * @return registrationSettlementtype
   */
  @ApiModelProperty(value = "")


  public String getRegistrationSettlementtype() {
    return registrationSettlementtype;
  }

  public void setRegistrationSettlementtype(String registrationSettlementtype) {
    this.registrationSettlementtype = registrationSettlementtype;
  }

  public MtsbPerson registrationSettlement(String registrationSettlement) {
    this.registrationSettlement = registrationSettlement;
    return this;
  }

  /**
   * Get registrationSettlement
   *
   * @return registrationSettlement
   */
  @ApiModelProperty(value = "")


  public String getRegistrationSettlement() {
    return registrationSettlement;
  }

  public void setRegistrationSettlement(String registrationSettlement) {
    this.registrationSettlement = registrationSettlement;
  }

  public MtsbPerson registrationStreettype(String registrationStreettype) {
    this.registrationStreettype = registrationStreettype;
    return this;
  }

  /**
   * Get registrationStreettype
   *
   * @return registrationStreettype
   */
  @ApiModelProperty(value = "")


  public String getRegistrationStreettype() {
    return registrationStreettype;
  }

  public void setRegistrationStreettype(String registrationStreettype) {
    this.registrationStreettype = registrationStreettype;
  }

  public MtsbPerson registrationStreet(String registrationStreet) {
    this.registrationStreet = registrationStreet;
    return this;
  }

  /**
   * Get registrationStreet
   *
   * @return registrationStreet
   */
  @ApiModelProperty(value = "")


  public String getRegistrationStreet() {
    return registrationStreet;
  }

  public void setRegistrationStreet(String registrationStreet) {
    this.registrationStreet = registrationStreet;
  }

  public MtsbPerson registrationHousenumber(String registrationHousenumber) {
    this.registrationHousenumber = registrationHousenumber;
    return this;
  }

  /**
   * Get registrationHousenumber
   *
   * @return registrationHousenumber
   */
  @ApiModelProperty(value = "")


  public String getRegistrationHousenumber() {
    return registrationHousenumber;
  }

  public void setRegistrationHousenumber(String registrationHousenumber) {
    this.registrationHousenumber = registrationHousenumber;
  }

  public MtsbPerson registrationKorpus(String registrationKorpus) {
    this.registrationKorpus = registrationKorpus;
    return this;
  }

  /**
   * Get registrationKorpus
   *
   * @return registrationKorpus
   */
  @ApiModelProperty(value = "")


  public String getRegistrationKorpus() {
    return registrationKorpus;
  }

  public void setRegistrationKorpus(String registrationKorpus) {
    this.registrationKorpus = registrationKorpus;
  }

  public MtsbPerson registrationStroenie(String registrationStroenie) {
    this.registrationStroenie = registrationStroenie;
    return this;
  }

  /**
   * Get registrationStroenie
   *
   * @return registrationStroenie
   */
  @ApiModelProperty(value = "")


  public String getRegistrationStroenie() {
    return registrationStroenie;
  }

  public void setRegistrationStroenie(String registrationStroenie) {
    this.registrationStroenie = registrationStroenie;
  }

  public MtsbPerson registrationFlat(String registrationFlat) {
    this.registrationFlat = registrationFlat;
    return this;
  }

  /**
   * Get registrationFlat
   *
   * @return registrationFlat
   */
  @ApiModelProperty(value = "")


  public String getRegistrationFlat() {
    return registrationFlat;
  }

  public void setRegistrationFlat(String registrationFlat) {
    this.registrationFlat = registrationFlat;
  }

  public MtsbPerson registrationKladrcode(String registrationKladrcode) {
    this.registrationKladrcode = registrationKladrcode;
    return this;
  }

  /**
   * Get registrationKladrcode
   *
   * @return registrationKladrcode
   */
  @ApiModelProperty(value = "")


  public String getRegistrationKladrcode() {
    return registrationKladrcode;
  }

  public void setRegistrationKladrcode(String registrationKladrcode) {
    this.registrationKladrcode = registrationKladrcode;
  }

  public MtsbPerson registrationOkatocode(String registrationOkatocode) {
    this.registrationOkatocode = registrationOkatocode;
    return this;
  }

  /**
   * Get registrationOkatocode
   *
   * @return registrationOkatocode
   */
  @ApiModelProperty(value = "")


  public String getRegistrationOkatocode() {
    return registrationOkatocode;
  }

  public void setRegistrationOkatocode(String registrationOkatocode) {
    this.registrationOkatocode = registrationOkatocode;
  }

  public MtsbPerson registrationFiasCode(String registrationFiasCode) {
    this.registrationFiasCode = registrationFiasCode;
    return this;
  }

  /**
   * Get registrationFiasCode
   *
   * @return registrationFiasCode
   */
  @ApiModelProperty(value = "")


  public String getRegistrationFiasCode() {
    return registrationFiasCode;
  }

  public void setRegistrationFiasCode(String registrationFiasCode) {
    this.registrationFiasCode = registrationFiasCode;
  }

  public MtsbPerson registrationFiasLevel(String registrationFiasLevel) {
    this.registrationFiasLevel = registrationFiasLevel;
    return this;
  }

  /**
   * Get registrationFiasLevel
   *
   * @return registrationFiasLevel
   */
  @ApiModelProperty(value = "")


  public String getRegistrationFiasLevel() {
    return registrationFiasLevel;
  }

  public void setRegistrationFiasLevel(String registrationFiasLevel) {
    this.registrationFiasLevel = registrationFiasLevel;
  }

  public MtsbPerson constantRegistrationActualityDate(Date constantRegistrationActualityDate) {
    this.constantRegistrationActualityDate = constantRegistrationActualityDate;
    return this;
  }

  /**
   * Get constantRegistrationActualityDate
   *
   * @return constantRegistrationActualityDate
   */
  @ApiModelProperty(value = "")

  @Valid

  public Date getConstantRegistrationActualityDate() {
    return constantRegistrationActualityDate;
  }

  public void setConstantRegistrationActualityDate(Date constantRegistrationActualityDate) {
    this.constantRegistrationActualityDate = constantRegistrationActualityDate;
  }

  public MtsbPerson constantRegistrationPrimaryFlag(Integer constantRegistrationPrimaryFlag) {
    this.constantRegistrationPrimaryFlag = constantRegistrationPrimaryFlag;
    return this;
  }

  /**
   * Get constantRegistrationPrimaryFlag
   *
   * @return constantRegistrationPrimaryFlag
   */
  @ApiModelProperty(value = "")


  public Integer getConstantRegistrationPrimaryFlag() {
    return constantRegistrationPrimaryFlag;
  }

  public void setConstantRegistrationPrimaryFlag(Integer constantRegistrationPrimaryFlag) {
    this.constantRegistrationPrimaryFlag = constantRegistrationPrimaryFlag;
  }

  public MtsbPerson constantRegistrationRawSource(String constantRegistrationRawSource) {
    this.constantRegistrationRawSource = constantRegistrationRawSource;
    return this;
  }

  /**
   * Get constantRegistrationRawSource
   *
   * @return constantRegistrationRawSource
   */
  @ApiModelProperty(value = "")


  public String getConstantRegistrationRawSource() {
    return constantRegistrationRawSource;
  }

  public void setConstantRegistrationRawSource(String constantRegistrationRawSource) {
    this.constantRegistrationRawSource = constantRegistrationRawSource;
  }

  public MtsbPerson constantRegistrationPostalcode(String constantRegistrationPostalcode) {
    this.constantRegistrationPostalcode = constantRegistrationPostalcode;
    return this;
  }

  /**
   * Get constantRegistrationPostalcode
   *
   * @return constantRegistrationPostalcode
   */
  @ApiModelProperty(value = "")


  public String getConstantRegistrationPostalcode() {
    return constantRegistrationPostalcode;
  }

  public void setConstantRegistrationPostalcode(String constantRegistrationPostalcode) {
    this.constantRegistrationPostalcode = constantRegistrationPostalcode;
  }

  public MtsbPerson constantRegistrationCountry(String constantRegistrationCountry) {
    this.constantRegistrationCountry = constantRegistrationCountry;
    return this;
  }

  /**
   * Get constantRegistrationCountry
   *
   * @return constantRegistrationCountry
   */
  @ApiModelProperty(value = "")


  public String getConstantRegistrationCountry() {
    return constantRegistrationCountry;
  }

  public void setConstantRegistrationCountry(String constantRegistrationCountry) {
    this.constantRegistrationCountry = constantRegistrationCountry;
  }

  public MtsbPerson constantRegistrationRegiontype(String constantRegistrationRegiontype) {
    this.constantRegistrationRegiontype = constantRegistrationRegiontype;
    return this;
  }

  /**
   * Get constantRegistrationRegiontype
   *
   * @return constantRegistrationRegiontype
   */
  @ApiModelProperty(value = "")


  public String getConstantRegistrationRegiontype() {
    return constantRegistrationRegiontype;
  }

  public void setConstantRegistrationRegiontype(String constantRegistrationRegiontype) {
    this.constantRegistrationRegiontype = constantRegistrationRegiontype;
  }

  public MtsbPerson constantRegistrationRegion(String constantRegistrationRegion) {
    this.constantRegistrationRegion = constantRegistrationRegion;
    return this;
  }

  /**
   * Get constantRegistrationRegion
   *
   * @return constantRegistrationRegion
   */
  @ApiModelProperty(value = "")


  public String getConstantRegistrationRegion() {
    return constantRegistrationRegion;
  }

  public void setConstantRegistrationRegion(String constantRegistrationRegion) {
    this.constantRegistrationRegion = constantRegistrationRegion;
  }

  public MtsbPerson constantRegistrationRayontype(String constantRegistrationRayontype) {
    this.constantRegistrationRayontype = constantRegistrationRayontype;
    return this;
  }

  /**
   * Get constantRegistrationRayontype
   *
   * @return constantRegistrationRayontype
   */
  @ApiModelProperty(value = "")


  public String getConstantRegistrationRayontype() {
    return constantRegistrationRayontype;
  }

  public void setConstantRegistrationRayontype(String constantRegistrationRayontype) {
    this.constantRegistrationRayontype = constantRegistrationRayontype;
  }

  public MtsbPerson constantRegistrationRayon(String constantRegistrationRayon) {
    this.constantRegistrationRayon = constantRegistrationRayon;
    return this;
  }

  /**
   * Get constantRegistrationRayon
   *
   * @return constantRegistrationRayon
   */
  @ApiModelProperty(value = "")


  public String getConstantRegistrationRayon() {
    return constantRegistrationRayon;
  }

  public void setConstantRegistrationRayon(String constantRegistrationRayon) {
    this.constantRegistrationRayon = constantRegistrationRayon;
  }

  public MtsbPerson constantRegistrationCitytype(String constantRegistrationCitytype) {
    this.constantRegistrationCitytype = constantRegistrationCitytype;
    return this;
  }

  /**
   * Get constantRegistrationCitytype
   *
   * @return constantRegistrationCitytype
   */
  @ApiModelProperty(value = "")


  public String getConstantRegistrationCitytype() {
    return constantRegistrationCitytype;
  }

  public void setConstantRegistrationCitytype(String constantRegistrationCitytype) {
    this.constantRegistrationCitytype = constantRegistrationCitytype;
  }

  public MtsbPerson constantRegistrationCity(String constantRegistrationCity) {
    this.constantRegistrationCity = constantRegistrationCity;
    return this;
  }

  /**
   * Get constantRegistrationCity
   *
   * @return constantRegistrationCity
   */
  @ApiModelProperty(value = "")


  public String getConstantRegistrationCity() {
    return constantRegistrationCity;
  }

  public void setConstantRegistrationCity(String constantRegistrationCity) {
    this.constantRegistrationCity = constantRegistrationCity;
  }

  public MtsbPerson constantRegistrationSettlementtype(String constantRegistrationSettlementtype) {
    this.constantRegistrationSettlementtype = constantRegistrationSettlementtype;
    return this;
  }

  /**
   * Get constantRegistrationSettlementtype
   *
   * @return constantRegistrationSettlementtype
   */
  @ApiModelProperty(value = "")


  public String getConstantRegistrationSettlementtype() {
    return constantRegistrationSettlementtype;
  }

  public void setConstantRegistrationSettlementtype(String constantRegistrationSettlementtype) {
    this.constantRegistrationSettlementtype = constantRegistrationSettlementtype;
  }

  public MtsbPerson constantRegistrationSettlement(String constantRegistrationSettlement) {
    this.constantRegistrationSettlement = constantRegistrationSettlement;
    return this;
  }

  /**
   * Get constantRegistrationSettlement
   *
   * @return constantRegistrationSettlement
   */
  @ApiModelProperty(value = "")


  public String getConstantRegistrationSettlement() {
    return constantRegistrationSettlement;
  }

  public void setConstantRegistrationSettlement(String constantRegistrationSettlement) {
    this.constantRegistrationSettlement = constantRegistrationSettlement;
  }

  public MtsbPerson constantRegistrationStreettype(String constantRegistrationStreettype) {
    this.constantRegistrationStreettype = constantRegistrationStreettype;
    return this;
  }

  /**
   * Get constantRegistrationStreettype
   *
   * @return constantRegistrationStreettype
   */
  @ApiModelProperty(value = "")


  public String getConstantRegistrationStreettype() {
    return constantRegistrationStreettype;
  }

  public void setConstantRegistrationStreettype(String constantRegistrationStreettype) {
    this.constantRegistrationStreettype = constantRegistrationStreettype;
  }

  public MtsbPerson constantRegistrationStreet(String constantRegistrationStreet) {
    this.constantRegistrationStreet = constantRegistrationStreet;
    return this;
  }

  /**
   * Get constantRegistrationStreet
   *
   * @return constantRegistrationStreet
   */
  @ApiModelProperty(value = "")


  public String getConstantRegistrationStreet() {
    return constantRegistrationStreet;
  }

  public void setConstantRegistrationStreet(String constantRegistrationStreet) {
    this.constantRegistrationStreet = constantRegistrationStreet;
  }

  public MtsbPerson constantRegistrationHousenumber(String constantRegistrationHousenumber) {
    this.constantRegistrationHousenumber = constantRegistrationHousenumber;
    return this;
  }

  /**
   * Get constantRegistrationHousenumber
   *
   * @return constantRegistrationHousenumber
   */
  @ApiModelProperty(value = "")


  public String getConstantRegistrationHousenumber() {
    return constantRegistrationHousenumber;
  }

  public void setConstantRegistrationHousenumber(String constantRegistrationHousenumber) {
    this.constantRegistrationHousenumber = constantRegistrationHousenumber;
  }

  public MtsbPerson constantRegistrationKorpus(String constantRegistrationKorpus) {
    this.constantRegistrationKorpus = constantRegistrationKorpus;
    return this;
  }

  /**
   * Get constantRegistrationKorpus
   *
   * @return constantRegistrationKorpus
   */
  @ApiModelProperty(value = "")


  public String getConstantRegistrationKorpus() {
    return constantRegistrationKorpus;
  }

  public void setConstantRegistrationKorpus(String constantRegistrationKorpus) {
    this.constantRegistrationKorpus = constantRegistrationKorpus;
  }

  public MtsbPerson constantRegistrationStroenie(String constantRegistrationStroenie) {
    this.constantRegistrationStroenie = constantRegistrationStroenie;
    return this;
  }

  /**
   * Get constantRegistrationStroenie
   *
   * @return constantRegistrationStroenie
   */
  @ApiModelProperty(value = "")


  public String getConstantRegistrationStroenie() {
    return constantRegistrationStroenie;
  }

  public void setConstantRegistrationStroenie(String constantRegistrationStroenie) {
    this.constantRegistrationStroenie = constantRegistrationStroenie;
  }

  public MtsbPerson constantRegistrationFlat(String constantRegistrationFlat) {
    this.constantRegistrationFlat = constantRegistrationFlat;
    return this;
  }

  /**
   * Get constantRegistrationFlat
   *
   * @return constantRegistrationFlat
   */
  @ApiModelProperty(value = "")


  public String getConstantRegistrationFlat() {
    return constantRegistrationFlat;
  }

  public void setConstantRegistrationFlat(String constantRegistrationFlat) {
    this.constantRegistrationFlat = constantRegistrationFlat;
  }

  public MtsbPerson constantRegistrationKladrcode(String constantRegistrationKladrcode) {
    this.constantRegistrationKladrcode = constantRegistrationKladrcode;
    return this;
  }

  /**
   * Get constantRegistrationKladrcode
   *
   * @return constantRegistrationKladrcode
   */
  @ApiModelProperty(value = "")


  public String getConstantRegistrationKladrcode() {
    return constantRegistrationKladrcode;
  }

  public void setConstantRegistrationKladrcode(String constantRegistrationKladrcode) {
    this.constantRegistrationKladrcode = constantRegistrationKladrcode;
  }

  public MtsbPerson constantRegistrationOkatocode(String constantRegistrationOkatocode) {
    this.constantRegistrationOkatocode = constantRegistrationOkatocode;
    return this;
  }

  /**
   * Get constantRegistrationOkatocode
   *
   * @return constantRegistrationOkatocode
   */
  @ApiModelProperty(value = "")


  public String getConstantRegistrationOkatocode() {
    return constantRegistrationOkatocode;
  }

  public void setConstantRegistrationOkatocode(String constantRegistrationOkatocode) {
    this.constantRegistrationOkatocode = constantRegistrationOkatocode;
  }

  public MtsbPerson constantRegistrationFiasCode(String constantRegistrationFiasCode) {
    this.constantRegistrationFiasCode = constantRegistrationFiasCode;
    return this;
  }

  /**
   * Get constantRegistrationFiasCode
   *
   * @return constantRegistrationFiasCode
   */
  @ApiModelProperty(value = "")


  public String getConstantRegistrationFiasCode() {
    return constantRegistrationFiasCode;
  }

  public void setConstantRegistrationFiasCode(String constantRegistrationFiasCode) {
    this.constantRegistrationFiasCode = constantRegistrationFiasCode;
  }

  public MtsbPerson constantRegistrationFiasLevel(String constantRegistrationFiasLevel) {
    this.constantRegistrationFiasLevel = constantRegistrationFiasLevel;
    return this;
  }

  /**
   * Get constantRegistrationFiasLevel
   *
   * @return constantRegistrationFiasLevel
   */
  @ApiModelProperty(value = "")


  public String getConstantRegistrationFiasLevel() {
    return constantRegistrationFiasLevel;
  }

  public void setConstantRegistrationFiasLevel(String constantRegistrationFiasLevel) {
    this.constantRegistrationFiasLevel = constantRegistrationFiasLevel;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MtsbPerson mtsbPerson = (MtsbPerson) o;
    return Objects.equals(this.docType, mtsbPerson.docType) &&
            Objects.equals(this.docSeries, mtsbPerson.docSeries) &&
            Objects.equals(this.docNumber, mtsbPerson.docNumber) &&
            Objects.equals(this.docIssueDt, mtsbPerson.docIssueDt) &&
            Objects.equals(this.docIssueAuthority, mtsbPerson.docIssueAuthority) &&
            Objects.equals(this.docDepartmentCode, mtsbPerson.docDepartmentCode) &&
            Objects.equals(this.innNumber, mtsbPerson.innNumber) &&
            Objects.equals(this.cntSim, mtsbPerson.cntSim) &&
            Objects.equals(this.birthdateDt, mtsbPerson.birthdateDt) &&
            Objects.equals(this.birthPlace, mtsbPerson.birthPlace) &&
            Objects.equals(this.lastNameMdm, mtsbPerson.lastNameMdm) &&
            Objects.equals(this.firstNameMdm, mtsbPerson.firstNameMdm) &&
            Objects.equals(this.midNameMdm, mtsbPerson.midNameMdm) &&
            Objects.equals(this.lastNameSpgr, mtsbPerson.lastNameSpgr) &&
            Objects.equals(this.firstNameSpgr, mtsbPerson.firstNameSpgr) &&
            Objects.equals(this.midNameSpgr, mtsbPerson.midNameSpgr) &&
            Objects.equals(this.isFirstNameEqual, mtsbPerson.isFirstNameEqual) &&
            Objects.equals(this.isFirstLastNameEqual, mtsbPerson.isFirstLastNameEqual) &&
            Objects.equals(this.homeActualityDate, mtsbPerson.homeActualityDate) &&
            Objects.equals(this.homePrimaryFlag, mtsbPerson.homePrimaryFlag) &&
            Objects.equals(this.homeRawSource, mtsbPerson.homeRawSource) &&
            Objects.equals(this.homePostalcode, mtsbPerson.homePostalcode) &&
            Objects.equals(this.homeCountry, mtsbPerson.homeCountry) &&
            Objects.equals(this.homeRegiontype, mtsbPerson.homeRegiontype) &&
            Objects.equals(this.homeRegion, mtsbPerson.homeRegion) &&
            Objects.equals(this.homeRayontype, mtsbPerson.homeRayontype) &&
            Objects.equals(this.homeRayon, mtsbPerson.homeRayon) &&
            Objects.equals(this.homeCitytype, mtsbPerson.homeCitytype) &&
            Objects.equals(this.homeCity, mtsbPerson.homeCity) &&
            Objects.equals(this.homeSettlementtype, mtsbPerson.homeSettlementtype) &&
            Objects.equals(this.homeSettlement, mtsbPerson.homeSettlement) &&
            Objects.equals(this.homeStreettype, mtsbPerson.homeStreettype) &&
            Objects.equals(this.homeStreet, mtsbPerson.homeStreet) &&
            Objects.equals(this.homeHousenumber, mtsbPerson.homeHousenumber) &&
            Objects.equals(this.homeKorpus, mtsbPerson.homeKorpus) &&
            Objects.equals(this.homeStroenie, mtsbPerson.homeStroenie) &&
            Objects.equals(this.homeFlat, mtsbPerson.homeFlat) &&
            Objects.equals(this.homeKladrcode, mtsbPerson.homeKladrcode) &&
            Objects.equals(this.homeOkatocode, mtsbPerson.homeOkatocode) &&
            Objects.equals(this.homeFiasCode, mtsbPerson.homeFiasCode) &&
            Objects.equals(this.homeFiasLevel, mtsbPerson.homeFiasLevel) &&
            Objects.equals(this.registrationActualityDate, mtsbPerson.registrationActualityDate) &&
            Objects.equals(this.registrationPrimaryFlag, mtsbPerson.registrationPrimaryFlag) &&
            Objects.equals(this.registrationRawSource, mtsbPerson.registrationRawSource) &&
            Objects.equals(this.registrationPostalcode, mtsbPerson.registrationPostalcode) &&
            Objects.equals(this.registrationCountry, mtsbPerson.registrationCountry) &&
            Objects.equals(this.registrationRegiontype, mtsbPerson.registrationRegiontype) &&
            Objects.equals(this.registrationRegion, mtsbPerson.registrationRegion) &&
            Objects.equals(this.registrationRayontype, mtsbPerson.registrationRayontype) &&
            Objects.equals(this.registrationRayon, mtsbPerson.registrationRayon) &&
            Objects.equals(this.registrationCitytype, mtsbPerson.registrationCitytype) &&
            Objects.equals(this.registrationCity, mtsbPerson.registrationCity) &&
            Objects.equals(this.registrationSettlementtype, mtsbPerson.registrationSettlementtype) &&
            Objects.equals(this.registrationSettlement, mtsbPerson.registrationSettlement) &&
            Objects.equals(this.registrationStreettype, mtsbPerson.registrationStreettype) &&
            Objects.equals(this.registrationStreet, mtsbPerson.registrationStreet) &&
            Objects.equals(this.registrationHousenumber, mtsbPerson.registrationHousenumber) &&
            Objects.equals(this.registrationKorpus, mtsbPerson.registrationKorpus) &&
            Objects.equals(this.registrationStroenie, mtsbPerson.registrationStroenie) &&
            Objects.equals(this.registrationFlat, mtsbPerson.registrationFlat) &&
            Objects.equals(this.registrationKladrcode, mtsbPerson.registrationKladrcode) &&
            Objects.equals(this.registrationOkatocode, mtsbPerson.registrationOkatocode) &&
            Objects.equals(this.registrationFiasCode, mtsbPerson.registrationFiasCode) &&
            Objects.equals(this.registrationFiasLevel, mtsbPerson.registrationFiasLevel) &&
            Objects.equals(this.constantRegistrationActualityDate, mtsbPerson.constantRegistrationActualityDate) &&
            Objects.equals(this.constantRegistrationPrimaryFlag, mtsbPerson.constantRegistrationPrimaryFlag) &&
            Objects.equals(this.constantRegistrationRawSource, mtsbPerson.constantRegistrationRawSource) &&
            Objects.equals(this.constantRegistrationPostalcode, mtsbPerson.constantRegistrationPostalcode) &&
            Objects.equals(this.constantRegistrationCountry, mtsbPerson.constantRegistrationCountry) &&
            Objects.equals(this.constantRegistrationRegiontype, mtsbPerson.constantRegistrationRegiontype) &&
            Objects.equals(this.constantRegistrationRegion, mtsbPerson.constantRegistrationRegion) &&
            Objects.equals(this.constantRegistrationRayontype, mtsbPerson.constantRegistrationRayontype) &&
            Objects.equals(this.constantRegistrationRayon, mtsbPerson.constantRegistrationRayon) &&
            Objects.equals(this.constantRegistrationCitytype, mtsbPerson.constantRegistrationCitytype) &&
            Objects.equals(this.constantRegistrationCity, mtsbPerson.constantRegistrationCity) &&
            Objects.equals(this.constantRegistrationSettlementtype, mtsbPerson.constantRegistrationSettlementtype) &&
            Objects.equals(this.constantRegistrationSettlement, mtsbPerson.constantRegistrationSettlement) &&
            Objects.equals(this.constantRegistrationStreettype, mtsbPerson.constantRegistrationStreettype) &&
            Objects.equals(this.constantRegistrationStreet, mtsbPerson.constantRegistrationStreet) &&
            Objects.equals(this.constantRegistrationHousenumber, mtsbPerson.constantRegistrationHousenumber) &&
            Objects.equals(this.constantRegistrationKorpus, mtsbPerson.constantRegistrationKorpus) &&
            Objects.equals(this.constantRegistrationStroenie, mtsbPerson.constantRegistrationStroenie) &&
            Objects.equals(this.constantRegistrationFlat, mtsbPerson.constantRegistrationFlat) &&
            Objects.equals(this.constantRegistrationKladrcode, mtsbPerson.constantRegistrationKladrcode) &&
            Objects.equals(this.constantRegistrationOkatocode, mtsbPerson.constantRegistrationOkatocode) &&
            Objects.equals(this.constantRegistrationFiasCode, mtsbPerson.constantRegistrationFiasCode) &&
            Objects.equals(this.constantRegistrationFiasLevel, mtsbPerson.constantRegistrationFiasLevel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(docType, docSeries, docNumber, docIssueDt, docIssueAuthority, docDepartmentCode, innNumber, cntSim, birthdateDt, birthPlace, lastNameMdm, firstNameMdm, midNameMdm, lastNameSpgr, firstNameSpgr, midNameSpgr, isFirstNameEqual, isFirstLastNameEqual, homeActualityDate, homePrimaryFlag, homeRawSource, homePostalcode, homeCountry, homeRegiontype, homeRegion, homeRayontype, homeRayon, homeCitytype, homeCity, homeSettlementtype, homeSettlement, homeStreettype, homeStreet, homeHousenumber, homeKorpus, homeStroenie, homeFlat, homeKladrcode, homeOkatocode, homeFiasCode, homeFiasLevel, registrationActualityDate, registrationPrimaryFlag, registrationRawSource, registrationPostalcode, registrationCountry, registrationRegiontype, registrationRegion, registrationRayontype, registrationRayon, registrationCitytype, registrationCity, registrationSettlementtype, registrationSettlement, registrationStreettype, registrationStreet, registrationHousenumber, registrationKorpus, registrationStroenie, registrationFlat, registrationKladrcode, registrationOkatocode, registrationFiasCode, registrationFiasLevel, constantRegistrationActualityDate, constantRegistrationPrimaryFlag, constantRegistrationRawSource, constantRegistrationPostalcode, constantRegistrationCountry, constantRegistrationRegiontype, constantRegistrationRegion, constantRegistrationRayontype, constantRegistrationRayon, constantRegistrationCitytype, constantRegistrationCity, constantRegistrationSettlementtype, constantRegistrationSettlement, constantRegistrationStreettype, constantRegistrationStreet, constantRegistrationHousenumber, constantRegistrationKorpus, constantRegistrationStroenie, constantRegistrationFlat, constantRegistrationKladrcode, constantRegistrationOkatocode, constantRegistrationFiasCode, constantRegistrationFiasLevel);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MtsbPerson {\n");

    sb.append("    docType: ").append(toIndentedString(docType)).append("\n");
    sb.append("    docSeries: ").append(toIndentedString(docSeries)).append("\n");
    sb.append("    docNumber: ").append(toIndentedString(docNumber)).append("\n");
    sb.append("    docIssueDt: ").append(toIndentedString(docIssueDt)).append("\n");
    sb.append("    docIssueAuthority: ").append(toIndentedString(docIssueAuthority)).append("\n");
    sb.append("    docDepartmentCode: ").append(toIndentedString(docDepartmentCode)).append("\n");
    sb.append("    innNumber: ").append(toIndentedString(innNumber)).append("\n");
    sb.append("    cntSim: ").append(toIndentedString(cntSim)).append("\n");
    sb.append("    birthdateDt: ").append(toIndentedString(birthdateDt)).append("\n");
    sb.append("    birthPlace: ").append(toIndentedString(birthPlace)).append("\n");
    sb.append("    lastNameMdm: ").append(toIndentedString(lastNameMdm)).append("\n");
    sb.append("    firstNameMdm: ").append(toIndentedString(firstNameMdm)).append("\n");
    sb.append("    midNameMdm: ").append(toIndentedString(midNameMdm)).append("\n");
    sb.append("    lastNameSpgr: ").append(toIndentedString(lastNameSpgr)).append("\n");
    sb.append("    firstNameSpgr: ").append(toIndentedString(firstNameSpgr)).append("\n");
    sb.append("    midNameSpgr: ").append(toIndentedString(midNameSpgr)).append("\n");
    sb.append("    isFirstNameEqual: ").append(toIndentedString(isFirstNameEqual)).append("\n");
    sb.append("    isFirstLastNameEqual: ").append(toIndentedString(isFirstLastNameEqual)).append("\n");
    sb.append("    homeActualityDate: ").append(toIndentedString(homeActualityDate)).append("\n");
    sb.append("    homePrimaryFlag: ").append(toIndentedString(homePrimaryFlag)).append("\n");
    sb.append("    homeRawSource: ").append(toIndentedString(homeRawSource)).append("\n");
    sb.append("    homePostalcode: ").append(toIndentedString(homePostalcode)).append("\n");
    sb.append("    homeCountry: ").append(toIndentedString(homeCountry)).append("\n");
    sb.append("    homeRegiontype: ").append(toIndentedString(homeRegiontype)).append("\n");
    sb.append("    homeRegion: ").append(toIndentedString(homeRegion)).append("\n");
    sb.append("    homeRayontype: ").append(toIndentedString(homeRayontype)).append("\n");
    sb.append("    homeRayon: ").append(toIndentedString(homeRayon)).append("\n");
    sb.append("    homeCitytype: ").append(toIndentedString(homeCitytype)).append("\n");
    sb.append("    homeCity: ").append(toIndentedString(homeCity)).append("\n");
    sb.append("    homeSettlementtype: ").append(toIndentedString(homeSettlementtype)).append("\n");
    sb.append("    homeSettlement: ").append(toIndentedString(homeSettlement)).append("\n");
    sb.append("    homeStreettype: ").append(toIndentedString(homeStreettype)).append("\n");
    sb.append("    homeStreet: ").append(toIndentedString(homeStreet)).append("\n");
    sb.append("    homeHousenumber: ").append(toIndentedString(homeHousenumber)).append("\n");
    sb.append("    homeKorpus: ").append(toIndentedString(homeKorpus)).append("\n");
    sb.append("    homeStroenie: ").append(toIndentedString(homeStroenie)).append("\n");
    sb.append("    homeFlat: ").append(toIndentedString(homeFlat)).append("\n");
    sb.append("    homeKladrcode: ").append(toIndentedString(homeKladrcode)).append("\n");
    sb.append("    homeOkatocode: ").append(toIndentedString(homeOkatocode)).append("\n");
    sb.append("    homeFiasCode: ").append(toIndentedString(homeFiasCode)).append("\n");
    sb.append("    homeFiasLevel: ").append(toIndentedString(homeFiasLevel)).append("\n");
    sb.append("    registrationActualityDate: ").append(toIndentedString(registrationActualityDate)).append("\n");
    sb.append("    registrationPrimaryFlag: ").append(toIndentedString(registrationPrimaryFlag)).append("\n");
    sb.append("    registrationRawSource: ").append(toIndentedString(registrationRawSource)).append("\n");
    sb.append("    registrationPostalcode: ").append(toIndentedString(registrationPostalcode)).append("\n");
    sb.append("    registrationCountry: ").append(toIndentedString(registrationCountry)).append("\n");
    sb.append("    registrationRegiontype: ").append(toIndentedString(registrationRegiontype)).append("\n");
    sb.append("    registrationRegion: ").append(toIndentedString(registrationRegion)).append("\n");
    sb.append("    registrationRayontype: ").append(toIndentedString(registrationRayontype)).append("\n");
    sb.append("    registrationRayon: ").append(toIndentedString(registrationRayon)).append("\n");
    sb.append("    registrationCitytype: ").append(toIndentedString(registrationCitytype)).append("\n");
    sb.append("    registrationCity: ").append(toIndentedString(registrationCity)).append("\n");
    sb.append("    registrationSettlementtype: ").append(toIndentedString(registrationSettlementtype)).append("\n");
    sb.append("    registrationSettlement: ").append(toIndentedString(registrationSettlement)).append("\n");
    sb.append("    registrationStreettype: ").append(toIndentedString(registrationStreettype)).append("\n");
    sb.append("    registrationStreet: ").append(toIndentedString(registrationStreet)).append("\n");
    sb.append("    registrationHousenumber: ").append(toIndentedString(registrationHousenumber)).append("\n");
    sb.append("    registrationKorpus: ").append(toIndentedString(registrationKorpus)).append("\n");
    sb.append("    registrationStroenie: ").append(toIndentedString(registrationStroenie)).append("\n");
    sb.append("    registrationFlat: ").append(toIndentedString(registrationFlat)).append("\n");
    sb.append("    registrationKladrcode: ").append(toIndentedString(registrationKladrcode)).append("\n");
    sb.append("    registrationOkatocode: ").append(toIndentedString(registrationOkatocode)).append("\n");
    sb.append("    registrationFiasCode: ").append(toIndentedString(registrationFiasCode)).append("\n");
    sb.append("    registrationFiasLevel: ").append(toIndentedString(registrationFiasLevel)).append("\n");
    sb.append("    constantRegistrationActualityDate: ").append(toIndentedString(constantRegistrationActualityDate)).append("\n");
    sb.append("    constantRegistrationPrimaryFlag: ").append(toIndentedString(constantRegistrationPrimaryFlag)).append("\n");
    sb.append("    constantRegistrationRawSource: ").append(toIndentedString(constantRegistrationRawSource)).append("\n");
    sb.append("    constantRegistrationPostalcode: ").append(toIndentedString(constantRegistrationPostalcode)).append("\n");
    sb.append("    constantRegistrationCountry: ").append(toIndentedString(constantRegistrationCountry)).append("\n");
    sb.append("    constantRegistrationRegiontype: ").append(toIndentedString(constantRegistrationRegiontype)).append("\n");
    sb.append("    constantRegistrationRegion: ").append(toIndentedString(constantRegistrationRegion)).append("\n");
    sb.append("    constantRegistrationRayontype: ").append(toIndentedString(constantRegistrationRayontype)).append("\n");
    sb.append("    constantRegistrationRayon: ").append(toIndentedString(constantRegistrationRayon)).append("\n");
    sb.append("    constantRegistrationCitytype: ").append(toIndentedString(constantRegistrationCitytype)).append("\n");
    sb.append("    constantRegistrationCity: ").append(toIndentedString(constantRegistrationCity)).append("\n");
    sb.append("    constantRegistrationSettlementtype: ").append(toIndentedString(constantRegistrationSettlementtype)).append("\n");
    sb.append("    constantRegistrationSettlement: ").append(toIndentedString(constantRegistrationSettlement)).append("\n");
    sb.append("    constantRegistrationStreettype: ").append(toIndentedString(constantRegistrationStreettype)).append("\n");
    sb.append("    constantRegistrationStreet: ").append(toIndentedString(constantRegistrationStreet)).append("\n");
    sb.append("    constantRegistrationHousenumber: ").append(toIndentedString(constantRegistrationHousenumber)).append("\n");
    sb.append("    constantRegistrationKorpus: ").append(toIndentedString(constantRegistrationKorpus)).append("\n");
    sb.append("    constantRegistrationStroenie: ").append(toIndentedString(constantRegistrationStroenie)).append("\n");
    sb.append("    constantRegistrationFlat: ").append(toIndentedString(constantRegistrationFlat)).append("\n");
    sb.append("    constantRegistrationKladrcode: ").append(toIndentedString(constantRegistrationKladrcode)).append("\n");
    sb.append("    constantRegistrationOkatocode: ").append(toIndentedString(constantRegistrationOkatocode)).append("\n");
    sb.append("    constantRegistrationFiasCode: ").append(toIndentedString(constantRegistrationFiasCode)).append("\n");
    sb.append("    constantRegistrationFiasLevel: ").append(toIndentedString(constantRegistrationFiasLevel)).append("\n");
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

