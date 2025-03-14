package ru.mtsbank.autocomplete.service.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mtsbank.autocomplete.customer.model.Customer;
import ru.mtsbank.autocomplete.service.Application;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("junit")
public class CustomerDomainGrpcClientSAORealTest {
    @Autowired
    CustomerDomainGrpcClientSAO clientSAO;


    @Test
 //   @Disabled
    void getCustomerFull() {
        String mainPhoneNumber = "+79522700430";
        Customer resp = clientSAO.getCustomerFull(mainPhoneNumber);
        assertNotNull(resp);
        assertAll("consumer",

                () -> assertEquals(resp.getFirstName(), "ЕКАТЕРИНА"),
                () -> assertEquals(resp.getLastName(), "ЛЕБЕДЕВА"),
                () -> assertEquals(resp.getMiddleName(), "ВЛАДИМИРОВНА"));

    }

}