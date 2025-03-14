package ru.mtsbank.autocomplete.customer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Тело запроса нечеткого поиска
 */
@ApiModel(description = "Тело запроса нечеткого поиска")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-06-28T14:03:31.041683100+03:00[Europe/Moscow]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerFuzzySearchRequest extends Customer {
  private static final long serialVersionUID = -7280145969796730664L;
  @JsonProperty("minMatchScope")
  private Integer minMatchScope;

  /**
   * Gets or Sets includeSourceData
   */
  public enum IncludeSourceDataEnum {
    RBO("RBO");

    private String value;

    IncludeSourceDataEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static IncludeSourceDataEnum fromValue(String text) {
      for (IncludeSourceDataEnum b : IncludeSourceDataEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + text + "'");
    }
  }

  @JsonProperty("includeSourceData")
  @Valid
  private List<IncludeSourceDataEnum> includeSourceData = null;

  public CustomerFuzzySearchRequest minMatchScope(Integer minMatchScope) {
    this.minMatchScope = minMatchScope;
    return this;
  }

  /**
   * Мин. коэффициент соответствия. Если не передан, используется значение по умолчанию
   *
   * @return minMatchScope
   */
  @ApiModelProperty(value = "Мин. коэффициент соответствия. Если не передан, используется значение по умолчанию")


  public Integer getMinMatchScope() {
    return minMatchScope;
  }

  public void setMinMatchScope(Integer minMatchScope) {
    this.minMatchScope = minMatchScope;
  }

  public CustomerFuzzySearchRequest includeSourceData(List<IncludeSourceDataEnum> includeSourceData) {
    this.includeSourceData = includeSourceData;
    return this;
  }

  public CustomerFuzzySearchRequest addIncludeSourceDataItem(IncludeSourceDataEnum includeSourceDataItem) {
    if (this.includeSourceData == null) {
      this.includeSourceData = new ArrayList<IncludeSourceDataEnum>();
    }
    this.includeSourceData.add(includeSourceDataItem);
    return this;
  }

  /**
   * Признак включения в ответ исходных данных систем-источников
   *
   * @return includeSourceData
   */
  @ApiModelProperty(value = "Признак включения в ответ исходных данных систем-источников")


  public List<IncludeSourceDataEnum> getIncludeSourceData() {
    return includeSourceData;
  }

  public void setIncludeSourceData(List<IncludeSourceDataEnum> includeSourceData) {
    this.includeSourceData = includeSourceData;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerFuzzySearchRequest customerFuzzySearchRequest = (CustomerFuzzySearchRequest) o;
    return Objects.equals(this.minMatchScope, customerFuzzySearchRequest.minMatchScope) &&
            Objects.equals(this.includeSourceData, customerFuzzySearchRequest.includeSourceData) &&
            super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(minMatchScope, includeSourceData, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomerFuzzySearchRequest {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    minMatchScope: ").append(toIndentedString(minMatchScope)).append("\n");
    sb.append("    includeSourceData: ").append(toIndentedString(includeSourceData)).append("\n");
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

