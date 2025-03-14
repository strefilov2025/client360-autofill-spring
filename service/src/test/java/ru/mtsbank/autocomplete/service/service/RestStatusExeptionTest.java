package ru.mtsbank.autocomplete.service.service;


import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mtsbank.autocomplete.service.Application;
import ru.mtsbank.autocomplete.service.exception.constant.CustomerIssueError;
import ru.mtsbank.client.MqGatewayClient;
import ru.mtsbank.lib.web.model.context.MtsBankHttpRequestContext;
import ru.mtsbank.lib.web.model.exception.MtsBankException;
import ru.mtsbank.starter.xml.model.exception.MtsBankMarshallingException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("junit")
public class RestStatusExeptionTest {
    @Value("${mtsbank.app.name}")
    private String app;

    @Rule
    public ExpectedException exceptions = ExpectedException.none();
    @Autowired
    MQClient mQClient;
    @Autowired
    private TestRestTemplate restTemplate;
    private static final String API_V_1_CUSTOMER_PHONE_MAIN_PHONE_NUMBER = "/api/v1.1/customer?phone={mainPhoneNumber}";
    private String url;
    @Autowired
    CustomerService customerService;
    @MockBean
    MtsBankHttpRequestContext requestContext;
    @MockBean
    MtsbClientSAO mtsbClientSAO;

    @BeforeEach
    void init() {
        when(requestContext.getRequestId()).thenReturn(UUID.randomUUID().toString());

        given(mtsbClientSAO.getPerson(anyString())).willAnswer(invocation -> {
            throw new MtsBankException(CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getHttpStatus(),
                    CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getCode(),
                    CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getTitle(),
                    "");
        });
        url = "/" + app + API_V_1_CUSTOMER_PHONE_MAIN_PHONE_NUMBER;
    }


    @Test
    public void testStatusRuntimeException() {
        exceptions.expect(StatusRuntimeException.class);
        CustomerDomainGrpcClientSAO clientSAO = mock(CustomerDomainGrpcClientSAO.class);
        MqGatewayClient mqGatewayClient1 = mock(MqGatewayClient.class);
        given(mqGatewayClient1.sendInfoMessageWithAnswer(anyString(), anyString())).willAnswer(invocation -> {
            throw new MtsBankMarshallingException("abc msg");
        });
        when(clientSAO.getCustomerFull(anyString())).thenThrow(new StatusRuntimeException(Status.ABORTED));
        CustomerDomainGrpcClientSAO old = customerService.customerDomainClient;
        customerService.customerDomainClient = clientSAO;
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        customerService.customerDomainClient = clientSAO;
        customerService.custSearchList.mQClient = mQClient;
        MqGatewayClient oldSearch = customerService.custSearchList.mQClient.mqGatewayClient;

        customerService.custSearchList.mQClient.mqGatewayClient = mqGatewayClient1;
        Map<String, String> params = new HashMap<>();
        params.put("mainPhoneNumber", "+795227004302");

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class,
                params
        );
        customerService.customerDomainClient = old;
        customerService.custSearchList.mQClient.mqGatewayClient = oldSearch;

        assertEquals(response.getStatusCode(), HttpStatus.FAILED_DEPENDENCY);
    }

}
