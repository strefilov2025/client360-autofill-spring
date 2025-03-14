package ru.mtsbank.autocomplete.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * Внешний идентификатор клиента
 */
@ApiModel(description = "Внешний идентификатор клиента")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-06-28T14:03:31.041683100+03:00[Europe/Moscow]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExtId implements Serializable {
    private static final long serialVersionUID = -6347384175288363962L;
    @JsonProperty("system")
    private String system;

    @JsonProperty("id")
    private String id;

    public ExtId system(String system) {
        this.system = system;
        return this;
    }

  /**
   * Система
   * @return system
  */
  @ApiModelProperty(value = "Система")


  public String getSystem() {
    return system;
  }

  public void setSystem(String system) {
    this.system = system;
  }

  public ExtId id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Идентификатор клиента в системе
   * @return id
  */
  @ApiModelProperty(value = "Идентификатор клиента в системе")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExtId extId = (ExtId) o;
    return Objects.equals(this.system, extId.system) &&
        Objects.equals(this.id, extId.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(system, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExtId {\n");
    
    sb.append("    system: ").append(toIndentedString(system)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

