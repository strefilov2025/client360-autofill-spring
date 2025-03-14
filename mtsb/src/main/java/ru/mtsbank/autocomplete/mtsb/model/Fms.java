package ru.mtsbank.autocomplete.mtsb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Объект FMS.  Описывает формат ответа для endpoint&#39;a по запросу кода подразделения по полю \&quot;кем выдан\&quot; паспорта.
 */
@ApiModel(description = "Объект FMS.  Описывает формат ответа для endpoint'a по запросу кода подразделения по полю \"кем выдан\" паспорта.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-07-13T16:33:10.240228500+03:00[Europe/Moscow]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Fms {
  @JsonProperty("fms_code")
  private String fmsCode;

  @JsonProperty("score")
  private BigDecimal score;

  public Fms fmsCode(String fmsCode) {
    this.fmsCode = fmsCode;
    return this;
  }

  /**
   * Get fmsCode
   *
   * @return fmsCode
   */
  @ApiModelProperty(example = "000-000", required = true, value = "")
  @NotNull


  public String getFmsCode() {
    return fmsCode;
  }

  public void setFmsCode(String fmsCode) {
    this.fmsCode = fmsCode;
  }

  public Fms score(BigDecimal score) {
    this.score = score;
    return this;
  }

  /**
   * Насколько близки запрос и эталонное значение
   * minimum: 0
   * maximum: 1
   *
   * @return score
   */
  @ApiModelProperty(example = "0.95", required = true, value = "Насколько близки запрос и эталонное значение")
  @NotNull

  @Valid
  @DecimalMin("0")
  @DecimalMax("1")
  public BigDecimal getScore() {
    return score;
  }

  public void setScore(BigDecimal score) {
    this.score = score;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Fms fms = (Fms) o;
    return Objects.equals(this.fmsCode, fms.fmsCode) &&
            Objects.equals(this.score, fms.score);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fmsCode, score);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Fms {\n");

    sb.append("    fmsCode: ").append(toIndentedString(fmsCode)).append("\n");
    sb.append("    score: ").append(toIndentedString(score)).append("\n");
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

