package ru.mtsbank.autocomplete.service.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mtsbank.autocomplete.customer.model.Customer;
import ru.mtsbank.autocomplete.service.Application;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("junit")
public class RestRealTest {

    @Value("${mtsbank.app.name}")
    private String app;


    @Autowired
    private TestRestTemplate restTemplate;
    private static final String API_V_1_CUSTOMER_PHONE_MAIN_PHONE_NUMBER = "/api/v1.1/customer?phone={mainPhoneNumber}";
    private String url;

    @BeforeEach
    void init() {
        url = "/" + app + API_V_1_CUSTOMER_PHONE_MAIN_PHONE_NUMBER;
    }

    @Test
    @Disabled
    void testMDMOK() {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        Map<String, String> params = new HashMap<>();
        params.put("mainPhoneNumber", "79965930905");


        ResponseEntity<Customer> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                Customer.class,
                params
        );

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        params.put("mainPhoneNumber", "79802034128");


        response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                Customer.class,
                params
        );

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        params.put("mainPhoneNumber", "79165182744");


        response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                Customer.class,
                params
        );

        assertEquals(response.getStatusCode(), HttpStatus.OK);

        /*assertAll("consumer",
                () -> assertEquals(response.getBody().getDocuments().get(0).getNumber(), "425282"),
                () -> assertEquals(response.getBody().getDocuments().get(0).getSeries(), "4111"),
                () -> assertEquals(response.getBody().getFirstName(), "ЕКАТЕРИНА"),
                () -> assertEquals(response.getBody().getLastName(), "ЛЕБЕДЕВА"),
                () -> assertEquals(response.getBody().getMiddleName(), "СЕРГЕЕВНА"));
*/

    }

    @Test
    @Disabled
    void testOK() {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        Map<String, String> params = new HashMap<>();
        params.put("mainPhoneNumber", "+79522700430");


        ResponseEntity<Customer> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                Customer.class,
                params
        );

        assertEquals(response.getStatusCode(), HttpStatus.OK);

        /*assertAll("consumer",
                () -> assertEquals(response.getBody().getDocuments().get(0).getNumber(), "425282"),
                () -> assertEquals(response.getBody().getDocuments().get(0).getSeries(), "4111"),
                () -> assertEquals(response.getBody().getFirstName(), "ЕКАТЕРИНА"),
                () -> assertEquals(response.getBody().getLastName(), "ЛЕБЕДЕВА"),
                () -> assertEquals(response.getBody().getMiddleName(), "СЕРГЕЕВНА"));
*/

    }

    @Test
    @Disabled
    void testNotOK() {

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

    }


}
