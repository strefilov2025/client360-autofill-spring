package ru.mtsbank.autocomplete.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mtsbank.autocomplete.customer.model.CustomerFuzzySearchRequest;

import javax.xml.datatype.XMLGregorianCalendar;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCust {
    private ServerInfo serverInfo;
    private String method;
    private CustomerFuzzySearchRequest customerFuzzySearchRequest;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public final static class ServerInfo {
        private String requestId;
        @Builder.Default
        private String messageType = "CustFuzzySearchInqRq";
        @Builder.Default
        private String messageSender = "UMP_client360-autocomplete";
        @Builder.Default
        private String messageReceiver = "HF";
        private XMLGregorianCalendar serviceDate;
    }


}
