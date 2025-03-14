package ru.mtsbank.autocomplete.service.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mtsbank.autocomplete.customer.model.Customer;
import ru.mtsbank.autocomplete.service.Application;
import ru.mtsbank.lib.web.model.context.MtsBankHttpRequestContext;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("junit")
public class CustSearchingRealTest {

    @Autowired
    CustSearchList custSearchList;

    @MockBean
    MtsBankHttpRequestContext requestContext;

    @BeforeEach
    public void init() {
        when(requestContext.getRequestId()).thenReturn(UUID.randomUUID().toString());
    }

    @Test
    @Disabled
    void sendSearchNull() {
        Customer result = custSearchList.sendSearch("+79260783935", getRequestId());
        assertNotNull(result);
    }

    @Test
    @Disabled
    void sendSearchNullRBO() {
        Customer result = custSearchList.sendSearchRBO("2094521", getRequestId());
        assertNotNull(result);
    }

    private String getRequestId() {
        return requestContext.getRequestId();
    }

}