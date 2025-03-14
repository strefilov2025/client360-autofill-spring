package ru.mtsbank.autocomplete.service.service;


import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.jetbrains.annotations.NotNull;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import ru.mtsbank.autocomplete.customer.model.*;
import ru.mtsbank.autocomplete.service.Application;
import ru.mtsbank.client.MqGatewayClient;
import ru.mtsbank.lib.web.model.context.MtsBankHttpRequestContext;
import ru.mtsbank.lib.web.model.exception.MtsBankException;
import ru.mtsbank.starter.xml.model.exception.MtsBankMarshallingException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("junit")
public class RestMockTest {
    @Value("${mtsbank.app.name}")
    private String app;

    private static final String API_V_1_CUSTOMER_PHONE_MAIN_PHONE_NUMBER = "/api/v1.1/customer?phone={mainPhoneNumber}";
    private static final String API_V_1_CUSTOMER_FUZZY = "/api/v1.1/customer/fuzzy-search";
    private String url;
    private String urlPost;
    @Autowired
    private TestRestTemplate restTemplate;
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
        urlPost = "/" + app + API_V_1_CUSTOMER_FUZZY;
        url = "/" + app + API_V_1_CUSTOMER_PHONE_MAIN_PHONE_NUMBER;
    }




    @NotNull
    private CustomerFuzzySearchRequest createRequestData() {
        CustomerFuzzySearchRequest data = new CustomerFuzzySearchRequest();
        data.setFirstName("Sergey");
        data.setMiddleName("Ivanovis");
        data.setLastName("Trefilov");
        Phone phone = new Phone();
        phone.setNumber("99999");
        List<Phone> items = new ArrayList<>();
        items.add(phone);
        data.setPhones(items);
        data.setBirthDate(new Date());
        return data;
    }

    private CustomerFuzzySearchRequest createRequestDataNotNull3() throws ParseException {
        CustomerFuzzySearchRequest data = new CustomerFuzzySearchRequest();
        data.setFirstName("Александр");
        data.setMiddleName("Борисович");
        data.setLastName("Гикал");
        Phone phone = new Phone();
        phone.setNumber("7 965 397-37-91");
        List<Phone> items = new ArrayList<>();
        items.add(phone);
        data.setPhones(items);

        SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy");
        String dateInString = "22.08.1987";
        Date date = sdf.parse(dateInString);
        data.setBirthDate(date);
        Document doc = new Document();
        doc.setNumber("986610");
        doc.setSeries("4607");
        doc.setType("PASSPORT_RU1");
        List<Document> itemsd = new ArrayList<>();
        itemsd.add(doc);
        data.setDocuments(itemsd);
        List<CustomerFuzzySearchRequest.IncludeSourceDataEnum> includeSourceData = new ArrayList<>();
        includeSourceData.add(CustomerFuzzySearchRequest.IncludeSourceDataEnum.RBO);
        data.setIncludeSourceData(includeSourceData);
        return data;
    }

    @Test
    public void testPostOK() {

        CustFussySearching custFussySearching = mock(CustFussySearching.class);
        Customer customer = new Customer();
        customer.firstName("ЕКАТЕРИНА").middleName("СЕРГЕЕВНА").lastName("ЛЕБЕДЕВА");
        Document doc = new Document();
        doc.number("425282").series("4111");
        List<Document> listDoc = new ArrayList<>();
        listDoc.add(doc);
        customer.documents(listDoc);
        InlineResponse200 result = new InlineResponse200();
        result.customer(customer);
        when(custFussySearching.sendFuzzySearch(any(CustomerFuzzySearchRequest.class), anyString())).thenReturn(result);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        CustomerFuzzySearchRequest data = createRequestData();
        HttpEntity<?> entity = new HttpEntity<>(data, headers);


        CustFussySearching old = customerService.custFussySearching;
        customerService.custFussySearching = custFussySearching;
        ResponseEntity<InlineResponse200> response = restTemplate.exchange(
                urlPost,
                HttpMethod.POST,
                entity,
                InlineResponse200.class
        );

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        customerService.custFussySearching = old;

    }

    @Test
    public void testOK5() {
        CustomerDomainGrpcClientSAO clientSAO = mock(CustomerDomainGrpcClientSAO.class);
        when(clientSAO.getCustomerFull(anyString())).thenThrow(new StatusRuntimeException(Status.NOT_FOUND));
        CustSearchList clientSearchSAO = mock(CustSearchList.class);

        Customer customer = new Customer();
        customer.firstName("ЕКАТЕРИНА").middleName("СЕРГЕЕВНА").lastName("ЛЕБЕДЕВА");
        Document doc = new Document();
        doc.number("425282").series("4111");
        List<Document> listDoc = new ArrayList<>();
        listDoc.add(doc);
        customer.documents(listDoc);
        when(clientSearchSAO.sendSearch(anyString(), anyString())).thenReturn(customer);


        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        Map<String, String> params = new HashMap<>();
        params.put("mainPhoneNumber", "+79522700430");
        CustomerDomainGrpcClientSAO old = customerService.customerDomainClient;
        CustSearchList oldSearch = customerService.custSearchList;
        customerService.customerDomainClient = clientSAO;
        customerService.custSearchList = clientSearchSAO;
        ResponseEntity<Customer> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                Customer.class,
                params
        );

        assertEquals(response.getStatusCode(), HttpStatus.OK);

        assertAll("consumer",
                () -> assertEquals(response.getBody().getDocuments().get(0).getNumber(), "425282"),
                () -> assertEquals(response.getBody().getDocuments().get(0).getSeries(), "4111"),
                () -> assertEquals(response.getBody().getFirstName(), "ЕКАТЕРИНА"),
                () -> assertEquals(response.getBody().getLastName(), "ЛЕБЕДЕВА"),
                () -> assertEquals(response.getBody().getMiddleName(), "СЕРГЕЕВНА"));
        customerService.customerDomainClient = old;
        customerService.custSearchList = oldSearch;
    }

    @Test
    public void testBAD() {
        CustomerDomainGrpcClientSAO clientSAO = mock(CustomerDomainGrpcClientSAO.class);
        when(clientSAO.getCustomerFull(anyString())).thenThrow(new MtsBankException(HttpStatus.BAD_REQUEST, "", ""));

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        Map<String, String> params = new HashMap<>();
        params.put("mainPhoneNumber", "+79522700430--");
        CustomerDomainGrpcClientSAO old = customerService.customerDomainClient;
        customerService.customerDomainClient = clientSAO;


        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class,
                params
        );

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);

        params.put("mainPhoneNumber", "");


        response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class,
                params
        );

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);

        customerService.customerDomainClient = old;

    }

    @Test
    public void testPostBAD() {
        CustFussySearching custFussySearching = mock(CustFussySearching.class);
        when(custFussySearching.sendFuzzySearch(any(CustomerFuzzySearchRequest.class), anyString())).thenThrow(new MtsBankException(HttpStatus.BAD_REQUEST, "", ""));

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        CustomerFuzzySearchRequest data = createRequestData();
        HttpEntity<?> entity = new HttpEntity<>(data, headers);


        CustFussySearching old = customerService.custFussySearching;
        customerService.custFussySearching = custFussySearching;


        ResponseEntity<String> response = restTemplate.exchange(
                urlPost,
                HttpMethod.POST,
                entity,
                String.class
        );


        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);

        customerService.custFussySearching = old;

    }

    @Test
    public void testPostBAD1() throws ParseException {
        CustFussySearching custFussySearching = mock(CustFussySearching.class);
        when(custFussySearching.sendFuzzySearch(any(CustomerFuzzySearchRequest.class), anyString())).thenThrow(new MtsBankException(HttpStatus.BAD_REQUEST, "", ""));

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        CustomerFuzzySearchRequest data = createRequestDataNotNull3();
        HttpEntity<?> entity = new HttpEntity<>(data, headers);


        CustFussySearching old = customerService.custFussySearching;
        customerService.custFussySearching = custFussySearching;


        ResponseEntity<String> response = restTemplate.exchange(
                urlPost,
                HttpMethod.POST,
                entity,
                String.class
        );


        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);

        customerService.custFussySearching = old;

    }

    @Test
    public void testPostRestClientException() {
        CustFussySearching custFussySearching = mock(CustFussySearching.class);
        when(custFussySearching.sendFuzzySearch(any(CustomerFuzzySearchRequest.class), anyString())).thenThrow(new RestClientException(""));

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        CustomerFuzzySearchRequest data = createRequestData();
        HttpEntity<?> entity = new HttpEntity<>(data, headers);


        CustFussySearching old = customerService.custFussySearching;
        customerService.custFussySearching = custFussySearching;


        ResponseEntity<String> response = restTemplate.exchange(
                urlPost,
                HttpMethod.POST,
                entity,
                String.class
        );


        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);

        customerService.custFussySearching = old;

    }

    @Test
    public void testMtsBankMarshallingException() {
        CustFussySearching custFussySearching = mock(CustFussySearching.class);
        when(custFussySearching.sendFuzzySearch(any(CustomerFuzzySearchRequest.class), anyString())).thenThrow(new MtsBankException(HttpStatus.INTERNAL_SERVER_ERROR, "", ""));

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        CustomerFuzzySearchRequest data = createRequestData();
        HttpEntity<?> entity = new HttpEntity<>(data, headers);


        CustFussySearching old = customerService.custFussySearching;
        customerService.custFussySearching = custFussySearching;


        ResponseEntity<String> response = restTemplate.exchange(
                urlPost,
                HttpMethod.POST,
                entity,
                String.class
        );


        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);

        customerService.custFussySearching = old;

    }

    @Test
    //@Disabled
    public void testMtsBankMarshallingException1() {
        CustomerDomainGrpcClientSAO clientSAO = mock(CustomerDomainGrpcClientSAO.class);
        when(clientSAO.getCustomerFull(anyString())).thenThrow(new StatusRuntimeException(Status.NOT_FOUND));
        MqGatewayClient mqGatewayClient1 = mock(MqGatewayClient.class);
        given(mqGatewayClient1.sendInfoMessageWithAnswer(anyString(), anyString())).willAnswer(invocation -> {
            throw new MtsBankMarshallingException("abc msg");
        });
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        CustomerFuzzySearchRequest data = createRequestData();
        HttpEntity<?> entity = new HttpEntity<>(data, headers);


        CustomerDomainGrpcClientSAO old = customerService.customerDomainClient;
        MqGatewayClient oldSearch = customerService.custSearchList.mQClient.mqGatewayClient;
        customerService.customerDomainClient = clientSAO;
        customerService.custSearchList.mQClient.mqGatewayClient = mqGatewayClient1;
        Map<String, String> params = new HashMap<>();
        params.put("mainPhoneNumber", "+79522700430");

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class, params
        );


        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
        customerService.customerDomainClient = old;
        customerService.custSearchList.mQClient.mqGatewayClient = oldSearch;


    }

    @Test
    public void testNotOK() {

        CustomerDomainGrpcClientSAO clientSAO = mock(CustomerDomainGrpcClientSAO.class);
        when(clientSAO.getCustomerFull(anyString())).thenThrow(new StatusRuntimeException(Status.NOT_FOUND));
        CustSearchList clientSearchSAO = mock(CustSearchList.class);
        when(clientSearchSAO.sendSearch(anyString(), anyString())).thenReturn(null);
        CustomerDomainGrpcClientSAO old = customerService.customerDomainClient;
        CustSearchList oldSearch = customerService.custSearchList;
        customerService.customerDomainClient = clientSAO;
        customerService.custSearchList = clientSearchSAO;
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        Map<String, String> params = new HashMap<>();
        params.put("mainPhoneNumber", "+795227004301");

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class,
                params
        );

        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
        customerService.customerDomainClient = old;
        customerService.custSearchList = oldSearch;
    }

    @Test
    public void testPostNotOK() {

        CustFussySearching custFussySearching = mock(CustFussySearching.class);
        when(custFussySearching.sendFuzzySearch(any(CustomerFuzzySearchRequest.class), anyString())).thenReturn(null);


        CustFussySearching old = customerService.custFussySearching;

        customerService.custFussySearching = custFussySearching;

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        CustomerFuzzySearchRequest data = createRequestData();
        HttpEntity<?> entity = new HttpEntity<>(data, headers);


        ResponseEntity<String> response = restTemplate.exchange(
                urlPost,
                HttpMethod.POST,
                entity,
                String.class
        );

        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
        customerService.custFussySearching = old;

    }

}
