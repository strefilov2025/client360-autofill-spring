package ru.mtsbank.autocomplete.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * Адрес
 */
@ApiModel(description = "Адрес")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-06-28T14:03:31.041683100+03:00[Europe/Moscow]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address implements Serializable {

  private static final long serialVersionUID = -1019146510137253970L;
  @JsonProperty("addressType")
  private String addressType;

  @JsonProperty("fullAddress")
  private String fullAddress;

  @JsonProperty("postalCode")
  private String postalCode;

  @JsonProperty("countryCode")
  private String countryCode;

  @JsonProperty("country")
  private String country;

  @JsonProperty("region")
  private String region;

  @JsonProperty("regionType")
  private String regionType;

  @JsonProperty("rayon")
  private String rayon;

  @JsonProperty("rayonType")
  private String rayonType;

  @JsonProperty("city")
  private String city;

  @JsonProperty("cityType")
  private String cityType;

  @JsonProperty("cityArea")
  private String cityArea;

  @JsonProperty("cityAreaType")
  private String cityAreaType;

  @JsonProperty("settlement")
  private String settlement;

  @JsonProperty("settlementType")
  private String settlementType;

  @JsonProperty("street")
  private String street;

  @JsonProperty("streetType")
  private String streetType;

  @JsonProperty("house")
  private String house;

  @JsonProperty("stroenie")
  private String stroenie;

  @JsonProperty("korpus")
  private String korpus;

  @JsonProperty("flat")
  private String flat;

  @JsonProperty("kladrCode")
  private String kladrCode;

  @JsonProperty("fiasCode")
  private String fiasCode;

  @JsonProperty("fiasLevel")
  private String fiasLevel;

  public Address addressType(String addressType) {
    this.addressType = addressType;
    return this;
  }

  /**
   * Тип адреса
   * @return addressType
  */
  @ApiModelProperty(readOnly = true, value = "Тип адреса")


  public String getAddressType() {
    return addressType;
  }

  public void setAddressType(String addressType) {
    this.addressType = addressType;
  }

  public Address fullAddress(String fullAddress) {
    this.fullAddress = fullAddress;
    return this;
  }

  /**
   * Полный адрес одной строкой
   * @return fullAddress
  */
  @ApiModelProperty(value = "Полный адрес одной строкой")


  public String getFullAddress() {
    return fullAddress;
  }

  public void setFullAddress(String fullAddress) {
    this.fullAddress = fullAddress;
  }

  public Address postalCode(String postalCode) {
    this.postalCode = postalCode;
    return this;
  }

  /**
   * Индекс
   * @return postalCode
  */
  @ApiModelProperty(value = "Индекс")


  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public Address countryCode(String countryCode) {
    this.countryCode = countryCode;
    return this;
  }

  /**
   * Код страны
   *
   * @return countryCode
   */
  @ApiModelProperty(value = "Код страны")


  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public Address country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Страна
   *
   * @return country
   */
  @ApiModelProperty(value = "Страна")


  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Address region(String region) {
    this.region = region;
    return this;
  }

  /**
   * Регион
   * @return region
  */
  @ApiModelProperty(value = "Регион")


  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public Address regionType(String regionType) {
    this.regionType = regionType;
    return this;
  }

  /**
   * Тип региона
   * @return regionType
  */
  @ApiModelProperty(value = "Тип региона")


  public String getRegionType() {
    return regionType;
  }

  public void setRegionType(String regionType) {
    this.regionType = regionType;
  }

  public Address rayon(String rayon) {
    this.rayon = rayon;
    return this;
  }

  /**
   * Район
   * @return rayon
  */
  @ApiModelProperty(value = "Район")


  public String getRayon() {
    return rayon;
  }

  public void setRayon(String rayon) {
    this.rayon = rayon;
  }

  public Address rayonType(String rayonType) {
    this.rayonType = rayonType;
    return this;
  }

  /**
   * Тип района
   * @return rayonType
  */
  @ApiModelProperty(value = "Тип района")


  public String getRayonType() {
    return rayonType;
  }

  public void setRayonType(String rayonType) {
    this.rayonType = rayonType;
  }

  public Address city(String city) {
    this.city = city;
    return this;
  }

  /**
   * Город
   * @return city
  */
  @ApiModelProperty(value = "Город")


  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Address cityType(String cityType) {
    this.cityType = cityType;
    return this;
  }

  /**
   * Тип города
   * @return cityType
  */
  @ApiModelProperty(value = "Тип города")


  public String getCityType() {
    return cityType;
  }

  public void setCityType(String cityType) {
    this.cityType = cityType;
  }

  public Address cityArea(String cityArea) {
    this.cityArea = cityArea;
    return this;
  }

  /**
   * Район города
   * @return cityArea
  */
  @ApiModelProperty(value = "Район города")


  public String getCityArea() {
    return cityArea;
  }

  public void setCityArea(String cityArea) {
    this.cityArea = cityArea;
  }

  public Address cityAreaType(String cityAreaType) {
    this.cityAreaType = cityAreaType;
    return this;
  }

  /**
   * Тип района города
   * @return cityAreaType
  */
  @ApiModelProperty(value = "Тип района города")


  public String getCityAreaType() {
    return cityAreaType;
  }

  public void setCityAreaType(String cityAreaType) {
    this.cityAreaType = cityAreaType;
  }

  public Address settlement(String settlement) {
    this.settlement = settlement;
    return this;
  }

  /**
   * Населенный пункт
   * @return settlement
  */
  @ApiModelProperty(value = "Населенный пункт")


  public String getSettlement() {
    return settlement;
  }

  public void setSettlement(String settlement) {
    this.settlement = settlement;
  }

  public Address settlementType(String settlementType) {
    this.settlementType = settlementType;
    return this;
  }

  /**
   * Тип населенного пункта
   * @return settlementType
  */
  @ApiModelProperty(value = "Тип населенного пункта")


  public String getSettlementType() {
    return settlementType;
  }

  public void setSettlementType(String settlementType) {
    this.settlementType = settlementType;
  }

  public Address street(String street) {
    this.street = street;
    return this;
  }

  /**
   * Улица
   * @return street
  */
  @ApiModelProperty(value = "Улица")


  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public Address streetType(String streetType) {
    this.streetType = streetType;
    return this;
  }

  /**
   * Тип улицы
   * @return streetType
  */
  @ApiModelProperty(value = "Тип улицы")


  public String getStreetType() {
    return streetType;
  }

  public void setStreetType(String streetType) {
    this.streetType = streetType;
  }

  public Address house(String house) {
    this.house = house;
    return this;
  }

  /**
   * Дом
   * @return house
  */
  @ApiModelProperty(value = "Дом")


  public String getHouse() {
    return house;
  }

  public void setHouse(String house) {
    this.house = house;
  }

  public Address stroenie(String stroenie) {
    this.stroenie = stroenie;
    return this;
  }

  /**
   * Строение
   * @return stroenie
  */
  @ApiModelProperty(value = "Строение")


  public String getStroenie() {
    return stroenie;
  }

  public void setStroenie(String stroenie) {
    this.stroenie = stroenie;
  }

  public Address korpus(String korpus) {
    this.korpus = korpus;
    return this;
  }

  /**
   * Корпус
   * @return korpus
  */
  @ApiModelProperty(value = "Корпус")


  public String getKorpus() {
    return korpus;
  }

  public void setKorpus(String korpus) {
    this.korpus = korpus;
  }

  public Address flat(String flat) {
    this.flat = flat;
    return this;
  }

  /**
   * Квартира
   * @return flat
  */
  @ApiModelProperty(value = "Квартира")


  public String getFlat() {
    return flat;
  }

  public void setFlat(String flat) {
    this.flat = flat;
  }

  public Address kladrCode(String kladrCode) {
    this.kladrCode = kladrCode;
    return this;
  }

  /**
   * Код КЛАДР
   * @return kladrCode
  */
  @ApiModelProperty(value = "Код КЛАДР")


  public String getKladrCode() {
    return kladrCode;
  }

  public void setKladrCode(String kladrCode) {
    this.kladrCode = kladrCode;
  }

  public Address fiasCode(String fiasCode) {
    this.fiasCode = fiasCode;
    return this;
  }

  /**
   * Код ФИАС
   * @return fiasCode
  */
  @ApiModelProperty(value = "Код ФИАС")


  public String getFiasCode() {
    return fiasCode;
  }

  public void setFiasCode(String fiasCode) {
    this.fiasCode = fiasCode;
  }

  public Address fiasLevel(String fiasLevel) {
    this.fiasLevel = fiasLevel;
    return this;
  }

  /**
   * Уровень ФИАС
   * @return fiasLevel
  */
  @ApiModelProperty(value = "Уровень ФИАС")


  public String getFiasLevel() {
    return fiasLevel;
  }

  public void setFiasLevel(String fiasLevel) {
    this.fiasLevel = fiasLevel;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Address address = (Address) o;
    return Objects.equals(this.addressType, address.addressType) &&
            Objects.equals(this.fullAddress, address.fullAddress) &&
            Objects.equals(this.postalCode, address.postalCode) &&
            Objects.equals(this.countryCode, address.countryCode) &&
            Objects.equals(this.country, address.country) &&
            Objects.equals(this.region, address.region) &&
            Objects.equals(this.regionType, address.regionType) &&
            Objects.equals(this.rayon, address.rayon) &&
            Objects.equals(this.rayonType, address.rayonType) &&
            Objects.equals(this.city, address.city) &&
            Objects.equals(this.cityType, address.cityType) &&
            Objects.equals(this.cityArea, address.cityArea) &&
            Objects.equals(this.cityAreaType, address.cityAreaType) &&
            Objects.equals(this.settlement, address.settlement) &&
        Objects.equals(this.settlementType, address.settlementType) &&
        Objects.equals(this.street, address.street) &&
        Objects.equals(this.streetType, address.streetType) &&
        Objects.equals(this.house, address.house) &&
        Objects.equals(this.stroenie, address.stroenie) &&
        Objects.equals(this.korpus, address.korpus) &&
        Objects.equals(this.flat, address.flat) &&
        Objects.equals(this.kladrCode, address.kladrCode) &&
        Objects.equals(this.fiasCode, address.fiasCode) &&
        Objects.equals(this.fiasLevel, address.fiasLevel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(addressType, fullAddress, postalCode, countryCode, country, region, regionType, rayon, rayonType, city, cityType, cityArea, cityAreaType, settlement, settlementType, street, streetType, house, stroenie, korpus, flat, kladrCode, fiasCode, fiasLevel);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Address {\n");
    
    sb.append("    addressType: ").append(toIndentedString(addressType)).append("\n");
    sb.append("    fullAddress: ").append(toIndentedString(fullAddress)).append("\n");
    sb.append("    postalCode: ").append(toIndentedString(postalCode)).append("\n");
    sb.append("    countryCode: ").append(toIndentedString(countryCode)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    region: ").append(toIndentedString(region)).append("\n");
    sb.append("    regionType: ").append(toIndentedString(regionType)).append("\n");
    sb.append("    rayon: ").append(toIndentedString(rayon)).append("\n");
    sb.append("    rayonType: ").append(toIndentedString(rayonType)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    cityType: ").append(toIndentedString(cityType)).append("\n");
    sb.append("    cityArea: ").append(toIndentedString(cityArea)).append("\n");
    sb.append("    cityAreaType: ").append(toIndentedString(cityAreaType)).append("\n");
    sb.append("    settlement: ").append(toIndentedString(settlement)).append("\n");
    sb.append("    settlementType: ").append(toIndentedString(settlementType)).append("\n");
    sb.append("    street: ").append(toIndentedString(street)).append("\n");
    sb.append("    streetType: ").append(toIndentedString(streetType)).append("\n");
    sb.append("    house: ").append(toIndentedString(house)).append("\n");
    sb.append("    stroenie: ").append(toIndentedString(stroenie)).append("\n");
    sb.append("    korpus: ").append(toIndentedString(korpus)).append("\n");
    sb.append("    flat: ").append(toIndentedString(flat)).append("\n");
    sb.append("    kladrCode: ").append(toIndentedString(kladrCode)).append("\n");
    sb.append("    fiasCode: ").append(toIndentedString(fiasCode)).append("\n");
    sb.append("    fiasLevel: ").append(toIndentedString(fiasLevel)).append("\n");
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

