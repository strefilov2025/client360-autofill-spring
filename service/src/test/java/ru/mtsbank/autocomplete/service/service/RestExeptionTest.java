package ru.mtsbank.autocomplete.service.service;


import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mtsbank.autocomplete.service.Application;
import ru.mtsbank.client.MqGatewayClient;
import ru.mtsbank.lib.web.model.context.MtsBankHttpRequestContext;
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
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class RestExeptionTest {

    @Value("${mtsbank.app.name}")
    private String app;


    private static final String API_V_1_CUSTOMER_PHONE_MAIN_PHONE_NUMBER = "/api/v1.1/customer?phone={mainPhoneNumber}";
    private String url;
    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    CustSearchList custSearchList;
    @Autowired
    MQClient mQClient;
    @Autowired
    CustomerService customerService;
    @MockBean
    MtsBankHttpRequestContext requestContext;
    @MockBean
    MtsbClientSAO mtsbClientSAO;

    @BeforeEach
    void init() {
        when(requestContext.getRequestId()).thenReturn(UUID.randomUUID().toString());
        when(mtsbClientSAO.getPerson(anyString())).thenReturn(null);
        url = "/" + app + API_V_1_CUSTOMER_PHONE_MAIN_PHONE_NUMBER;
    }


    @Test
    //@Disabled
    public void testException() {

        CustomerDomainGrpcClientSAO clientSAO = mock(CustomerDomainGrpcClientSAO.class);
        when(clientSAO.getCustomerFull(anyString())).thenThrow(new StatusRuntimeException(Status.NOT_FOUND));

        MqGatewayClient mqGatewayClient1 = mock(MqGatewayClient.class);
        given(mqGatewayClient1.sendInfoMessageWithAnswer(anyString(), anyString())).willAnswer(invocation -> {
            throw new MtsBankMarshallingException("abc msg");
        });
        CustomerDomainGrpcClientSAO old = customerService.customerDomainClient;
        customerService.customerDomainClient = clientSAO;
        customerService.custSearchList.mQClient = mQClient;
        MqGatewayClient oldSearch = customerService.custSearchList.mQClient.mqGatewayClient;

        customerService.custSearchList.mQClient.mqGatewayClient = mqGatewayClient1;
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        Map<String, String> params = new HashMap<>();
        params.put("mainPhoneNumber", "+795227004303");
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class,
                params
        );
        customerService.custSearchList.mQClient.mqGatewayClient = oldSearch;
        customerService.customerDomainClient = old;
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);

    }

}
