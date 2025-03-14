package ru.mtsbank.autocomplete.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import ru.mtsbank.lib.web.annotations.MtsBankResponse;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * InlineResponse200
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-06-29T10:18:52.756722600+03:00[Europe/Moscow]")
@JsonInclude(JsonInclude.Include.NON_NULL)
@MtsBankResponse
public class InlineResponse200 implements Serializable {
    private static final long serialVersionUID = -3463534838679236991L;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @JsonProperty("requestId")
    private String requestId;
    @JsonProperty("customer")
    private Customer customer = null;

    @JsonProperty("sources")
    @Valid
    private List<Customer> sources = null;

    public InlineResponse200 customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    /**
     * Get customer
     *
     * @return customer
     */
    @ApiModelProperty(value = "")

    @Valid

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public InlineResponse200 sources(List<Customer> sources) {
        this.sources = sources;
        return this;
    }

    public InlineResponse200 addSourcesItem(Customer sourcesItem) {
        if (this.sources == null) {
            this.sources = new ArrayList<Customer>();
        }
        this.sources.add(sourcesItem);
        return this;
    }

    /**
     * Get sources
     *
     * @return sources
     */
    @ApiModelProperty(value = "")

    @Valid

    public List<Customer> getSources() {
        return sources;
    }

    public void setSources(List<Customer> sources) {
        this.sources = sources;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InlineResponse200 inlineResponse200 = (InlineResponse200) o;
        return Objects.equals(this.customer, inlineResponse200.customer) &&
                Objects.equals(this.sources, inlineResponse200.sources);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, sources);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class InlineResponse200 {\n");

        sb.append("    customer: ").append(toIndentedString(customer)).append("\n");
        sb.append("    sources: ").append(toIndentedString(sources)).append("\n");
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

