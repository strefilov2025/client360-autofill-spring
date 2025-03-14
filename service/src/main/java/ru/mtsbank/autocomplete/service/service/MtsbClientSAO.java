package ru.mtsbank.autocomplete.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import ru.mtsbank.autocomplete.customer.model.Customer;
import ru.mtsbank.autocomplete.mtsb.model.MtsbPerson;
import ru.mtsbank.autocomplete.service.exception.constant.CustomerIssueError;
import ru.mtsbank.autocomplete.service.mapping.CustomerFromMtsbPerson;
import ru.mtsbank.autocomplete.service.model.logging.params.RqLoggingParams;
import ru.mtsbank.autocomplete.service.model.logging.params.RsLoggingParams;
import ru.mtsbank.autocomplete.service.utils.RqRsLoggingUtils;
import ru.mtsbank.lib.web.model.exception.MtsBankException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
@Slf4j
public class MtsbClientSAO {
    public static final String GET_PERSON = "getPerson";
    public static final String API_V_1_MTSB_PERSON = "/api/v1/mtsb/person/";
    @Value("${mtsbank.mtsb.url}")
    private String urlService;
    private final static String API_V_1_MTSB_PERSON_MSISDN = "/api/v1/mtsb/person/{msisdn}";
    @Autowired
    protected RestTemplate restTemplate;
    @Autowired
    private CustomerFromMtsbPerson customerFromMtsbPerson;
    @Autowired
    private ObjectMapper objectMapper;

    public Customer getPerson(final String msisdn) {
        Customer resultReturn = null;
        final HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        final HttpEntity<?> entity = new HttpEntity<>(headers);
        final Map<String, String> params = new ConcurrentHashMap<>();
        params.put("msisdn", msisdn);
        final HttpMethod httpMethod = HttpMethod.GET;
        final Map<String, String> requestHeaders = Map.of(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        RqRsLoggingUtils.logRequest(
                RqLoggingParams.builder()
                        .serviceName(GET_PERSON)
                        .method(httpMethod.name())
                        .headers(requestHeaders)
                        .url(urlService + API_V_1_MTSB_PERSON + msisdn)
                        .build()
        );
        try {
            final ResponseEntity<MtsbPerson> response = restTemplate.exchange(
                    urlService + API_V_1_MTSB_PERSON_MSISDN,
                    HttpMethod.GET,
                    entity,
                    MtsbPerson.class,
                    params
            );
            if (HttpStatus.OK == response.getStatusCode()) {
                final MtsbPerson result = response.getBody();
                final String responseBody = objectMapper.writeValueAsString(result);
                RqRsLoggingUtils.logResponse(
                        RsLoggingParams.builder()
                                .serviceName(GET_PERSON)
                                .body(responseBody)
                                .httpCode(200)
                                .headers(requestHeaders)
                                .url(urlService + API_V_1_MTSB_PERSON + msisdn)
                                .build()
                );

                if (result != null && validation(result)) {
                    resultReturn = customerFromMtsbPerson.toDto(result, msisdn);
                } else {
                    log.error("validation Error for msisdn :{}", msisdn);
                }
            } else if (HttpStatus.NOT_FOUND != response.getStatusCode()) {
                throw new MtsBankException(response.getStatusCode(),
                        CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getCode(),
                        CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getTitle(),
                        "");

            } else {
                log.error("HttpStatus.NOT_FOUND :{}", msisdn);
            }
        } catch (HttpStatusCodeException exception) {
            if (HttpStatus.NOT_FOUND != exception.getStatusCode()) {
                log.error("callToRestService Error :{} {}", exception.getStatusCode(), exception.getResponseBodyAsString());
                throw new MtsBankException(CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getHttpStatus(),
                        CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getCode(),
                        CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getTitle(),
                        exception.getResponseBodyAsString());
            } else {
                log.error("HttpStatus.NOT_FOUND :{} with Error {}", msisdn, exception.getResponseBodyAsString());
            }
        } catch (JsonProcessingException e) {
            log.error("callToRestService Error :{}", e.getMessage());
            throw new MtsBankException(CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getHttpStatus(),
                    CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getCode(),
                    CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getTitle(),
                    e.getMessage());
        }
        return resultReturn;

    }

    private boolean validation(final MtsbPerson result) {
        return (result.getCntSim() <= 3 && result.getCntSim() > 0) &&
                (result.getFirstNameMdm().equals(result.getFirstNameSpgr()) ||
                        result.getFirstNameSpgr() == null ||
                        result.getFirstNameSpgr().isBlank()) &&
                (result.getLastNameMdm().equals(result.getLastNameSpgr()) ||
                        result.getLastNameSpgr() == null ||
                        result.getLastNameSpgr().isBlank());

    }
}
