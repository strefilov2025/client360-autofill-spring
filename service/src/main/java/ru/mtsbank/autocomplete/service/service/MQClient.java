package ru.mtsbank.autocomplete.service.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import ru.mtsbank.autocomplete.service.exception.constant.CustomerIssueError;
import ru.mtsbank.client.MqGatewayClient;
import ru.mtsbank.integration.mts.xsd.fuzzy.CustFuzzySearchInqRq;
import ru.mtsbank.integration.mts.xsd.fuzzy.CustFuzzySearchInqRs;
import ru.mtsbank.integration.mts.xsd.search.CustSearchListInqRq;
import ru.mtsbank.integration.mts.xsd.search.CustSearchListInqRs;
import ru.mtsbank.lib.web.model.exception.MtsBankException;
import ru.mtsbank.starter.xml.model.exception.MtsBankMarshallingException;
import ru.mtsbank.starter.xml.model.exception.MtsBankUnmarshallingException;
import ru.mtsbank.starter.xml.service.MtsBankXmlMessageService;

@Component
@Slf4j
public class MQClient {

    @Autowired
    protected MqGatewayClient mqGatewayClient;
    @Autowired
    protected MtsBankXmlMessageService xmlMessageService;

    @Nullable
    public CustFuzzySearchInqRs sendMQ(final String requestId, final CustFuzzySearchInqRq request) {
        try {
            final String responseXml = mqGatewayClient.sendInfoMessageWithAnswer(xmlMessageService.marshal(request), requestId);
            return xmlMessageService.unmarshal(CustFuzzySearchInqRs.class, responseXml);
        } catch (RestClientException e) {

            log.error(ExceptionUtils.getStackTrace(e));

            throw new MtsBankException(CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getHttpStatus(),
                    CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getCode(),
                    CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getTitle(),
                    e.getMessage());
        } catch (MtsBankMarshallingException | MtsBankUnmarshallingException e) {

            log.error(ExceptionUtils.getStackTrace(e));

            throw new MtsBankException(CustomerIssueError.INTERNAL_SERVER_ERROR.getHttpStatus(),
                    CustomerIssueError.INTERNAL_SERVER_ERROR.getCode(),
                    CustomerIssueError.INTERNAL_SERVER_ERROR.getTitle(),
                    e.getMessage());
        }

    }

    @Nullable
    public CustSearchListInqRs sendMQ(final String requestId, final CustSearchListInqRq request) {
        try {
            final String responseXml = mqGatewayClient.sendInfoMessageWithAnswer(xmlMessageService.marshal(request), requestId);
            return xmlMessageService.unmarshal(CustSearchListInqRs.class, responseXml);
        } catch (RestClientException e) {

            log.error(ExceptionUtils.getStackTrace(e));

            throw new MtsBankException(CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getHttpStatus(),
                    CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getCode(),
                    CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getTitle(),
                    e.getMessage());
        } catch (MtsBankMarshallingException | MtsBankUnmarshallingException e) {

            log.error(ExceptionUtils.getStackTrace(e));

            throw new MtsBankException(CustomerIssueError.INTERNAL_SERVER_ERROR.getHttpStatus(),
                    CustomerIssueError.INTERNAL_SERVER_ERROR.getCode(),
                    CustomerIssueError.INTERNAL_SERVER_ERROR.getTitle(),
                    e.getMessage());
        }
    }

}
