package ru.mtsbank.autocomplete.service.service;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mtsbank.autocomplete.customer.model.CustomerFuzzySearchRequest;
import ru.mtsbank.autocomplete.customer.model.Document;
import ru.mtsbank.autocomplete.customer.model.InlineResponse200;
import ru.mtsbank.autocomplete.customer.model.Phone;
import ru.mtsbank.autocomplete.service.Application;
import ru.mtsbank.lib.web.model.context.MtsBankHttpRequestContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("junit")

public class CustFussySearchingRealTest {
    @Autowired
    CustFussySearching custFussySearching;

    @MockBean
    MtsBankHttpRequestContext requestContext;

    @BeforeEach
    public void init() {
        when(requestContext.getRequestId()).thenReturn(UUID.randomUUID().toString());
    }

    @Test
    @Disabled
    void sendFuzzySearchNull() {
        InlineResponse200 result = custFussySearching.sendFuzzySearch(createRequestData(), getRequestId());
        assertNull(result);
    }

    @Test
    @Disabled
    void sendFuzzySearchNotNull() throws ParseException {
        InlineResponse200 result = custFussySearching.sendFuzzySearch(createRequestDataNotNull3(), getRequestId());
        assertNotNull(result);
    }


    @NotNull
    private CustomerFuzzySearchRequest createRequestData() {
        CustomerFuzzySearchRequest data = new CustomerFuzzySearchRequest();
        data.setFirstName("Sergey");
        data.setMiddleName("Ivanovis");
        data.setLastName("Trefilov");
        Phone phone = new Phone();
        phone.setNumber("999999");
        List<Phone> items = new ArrayList<>();
        items.add(phone);
        data.setPhones(items);

        data.setBirthDate(new Date());
        return data;
    }




    /**
     * {
     * "firstName": "Александр",
     * "middleName": "Борисович",
     * "lastName": "Гикал",
     * "birthDate": "1987-08-22",
     * "phone": {
     * "phoneType": "MOBILE" ,
     * "number": "7 965 397-37-91"
     * },
     * "document":{
     * "series": "4607",
     * "number": "986610",
     * "type": "PASSPORT_RU"
     * }
     * <p>
     * }
     */
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
        doc.setType("PASSPORT_RU");
        List<Document> itemsd = new ArrayList<>();
        itemsd.add(doc);
        data.setDocuments(itemsd);
        List<CustomerFuzzySearchRequest.IncludeSourceDataEnum> includeSourceData = new ArrayList<>();
        includeSourceData.add(CustomerFuzzySearchRequest.IncludeSourceDataEnum.RBO);
        data.setIncludeSourceData(includeSourceData);
        return data;
    }

    private String getRequestId() {
        return requestContext.getRequestId();
    }
}