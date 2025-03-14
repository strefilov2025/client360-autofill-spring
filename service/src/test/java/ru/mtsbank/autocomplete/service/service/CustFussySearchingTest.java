package ru.mtsbank.autocomplete.service.service;

import customer_proto.CustomerFull;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mtsbank.autocomplete.customer.model.*;
import ru.mtsbank.autocomplete.service.Application;
import ru.mtsbank.autocomplete.service.XMLGregorianCalendarRandomizer;
import ru.mtsbank.autocomplete.service.exception.NotFoundRuntimeException;
import ru.mtsbank.autocomplete.service.mapping.*;
import ru.mtsbank.autocomplete.service.model.RequestCust;
import ru.mtsbank.integration.mts.xsd.fuzzy.CustFuzzySearchInqRq;
import ru.mtsbank.integration.mts.xsd.fuzzy.CustFuzzySearchInqRs;
import ru.mtsbank.integration.mts.xsd.search.CustSearchListInqRq;
import ru.mtsbank.integration.mts.xsd.search.CustSearchListInqRs;
import ru.mtsbank.lib.web.model.context.MtsBankHttpRequestContext;
import ru.mtsbank.lib.web.model.exception.MtsBankException;
import ru.mtsbank.starter.xml.model.exception.MtsBankMarshallingException;
import ru.mtsbank.starter.xml.model.exception.MtsBankUnmarshallingException;
import ru.mtsbank.starter.xml.service.MtsBankXmlMessageService;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("junit")
public
class CustFussySearchingTest {
    public static final String FEMALE = "FEMALE";


    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    MtsBankXmlMessageService xmlMessageService;
    @Autowired
    CustomerFromCustFuzzySearchInqRsMapper customerFromCustFuzzySearchInqRsMapper;

    @Autowired
    CustFuzzySearchInqRqMapper custFuzzySearchInqRqMapper;
    @Autowired
    CustomerFromCustSearchInqRsMapper customerFromCustSearchInqRsMapper;
    @Autowired
    CustSearchInqRqMapper custSearchInqRqMapper;
    @MockBean
    MtsBankHttpRequestContext requestContext;

    @BeforeEach
    public void init() {
        when(requestContext.getRequestId()).thenReturn(UUID.randomUUID().toString());
    }

    @Test
    public void getCustomer() {

        CustFussySearching custFussySearching = mock(CustFussySearching.class);
        given(custFussySearching.sendFuzzySearch(any(CustomerFuzzySearchRequest.class), anyString())).willAnswer(invocation -> {
            throw new MtsBankException(HttpStatus.BAD_REQUEST, "", "");
        });
        customerService.custFussySearching = custFussySearching;
        CustomerFuzzySearchRequest data = createRequestData();

        MtsBankException tt = Assertions.assertThrows(MtsBankException.class, () -> customerService.getCustomer(data), "abc msg");
        Assertions.assertEquals("", tt.getTitle());

    }

    @Test
    public void getCustomerRep() {

        CustFussySearching custFussySearching = mock(CustFussySearching.class);
        InlineResponse200 list = new InlineResponse200();
        Customer customer = new Customer();
        customer.firstName("ЕКАТЕРИНА").middleName("СЕРГЕЕВНА").lastName("ЛЕБЕДЕВА");
        Document doc = new Document();
        doc.number("425282").series("4111");
        List<Document> listDoc = new ArrayList<>();
        listDoc.add(doc);
        customer.documents(listDoc);
        list.customer(customer);
        when(custFussySearching.sendFuzzySearch(any(CustomerFuzzySearchRequest.class), anyString())).thenReturn(list);
        customerService.custFussySearching = custFussySearching;
        CustomerFuzzySearchRequest data = createRequestData();
        InlineResponse200 resp = customerService.getCustomer(data);
        assertNotNull(resp);
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

    @NotNull
    private RequestCust createDataSet(final CustomerFuzzySearchRequest data, final String requestId) {
        return RequestCust.builder()
                .serverInfo(RequestCust.ServerInfo.builder()
                        .serviceDate(getXmlGregorianCalendar())
                        .requestId(requestId).build())
                .customerFuzzySearchRequest(data).build();

    }

    @NotNull
    private XMLGregorianCalendar getXmlGregorianCalendar() {
        final XMLGregorianCalendar calendar;
        LocalDateTime dateTime = LocalDateTime.now(ZoneId.systemDefault());
        try {
            calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(dateTime));
            calendar.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
        } catch (DatatypeConfigurationException e) {
            throw new MtsBankException(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR.toString(),
                    e.getMessage(),
                    e.getMessage());
        }

        return calendar;
    }

    @Test
    public void marshal() throws MtsBankMarshallingException, MtsBankUnmarshallingException {
        EasyRandomParameters param = new EasyRandomParameters().collectionSizeRange(1, 3).randomize(XMLGregorianCalendar.class, new XMLGregorianCalendarRandomizer()).excludeField(
                FieldPredicates.named("fullNameSrc")
                        .or(FieldPredicates.named("payerCode"))
                        .or(FieldPredicates.named("prodType"))
                        .or(FieldPredicates.named("prodName"))
                        .or(FieldPredicates.named("prodCode"))
                        .or(FieldPredicates.named("supplAgreemtNum"))
                        .or(FieldPredicates.named("branchNum"))
                        .or(FieldPredicates.named("freq"))
                        .or(FieldPredicates.named("agencyNum"))
                        .or(FieldPredicates.named("purposeDesc"))
                        .or(FieldPredicates.named("fullNameSrc"))
                        .or(FieldPredicates.named("summ"))
                        .or(FieldPredicates.named("currency"))
                        .or(FieldPredicates.named("xferCode"))
        );

        EasyRandom generation = new EasyRandom(param);

        CustomerFuzzySearchRequest data = generation.nextObject(CustomerFuzzySearchRequest.class);
        RequestCust requestCust = createDataSet(data, "" + Math.round(Math.random() * 100000));
        CustFuzzySearchInqRq request = custFuzzySearchInqRqMapper.toFdx(requestCust);
        assertNotNull(request);
        CustFuzzySearchInqRs response1 = generation.nextObject(CustFuzzySearchInqRs.class);
        response1.getBankSvcRs().getStatus().setStatusCode("0");
        response1.getBankSvcRs().getCustList().getCustRec().get(0).getPersonInfo().setGender(FEMALE);
        String xml = xmlMessageService.marshal(response1);
        assertNotNull(xml);
        CustFuzzySearchInqRs response = xmlMessageService.unmarshal(CustFuzzySearchInqRs.class, xml);
        Customer cd = customerFromCustFuzzySearchInqRsMapper.toDto(response.getBankSvcRs().getCustList().getCustRec().get(0));
        assertNotNull(cd);
        CustSearchListInqRs response2 = generation.nextObject(CustSearchListInqRs.class);
        response2.getBankSvcRs().getCustList().getCustRec().get(0).getPersonInfo().setGender(FEMALE);
        Customer cd1 = customerFromCustSearchInqRsMapper.toDto(response2.getBankSvcRs().getCustList().getCustRec().get(0));
        assertNotNull(cd1);
        CustSearchListInqRq request1 = custSearchInqRqMapper.toFdx(requestCust);
        assertNotNull(request1);
        CustomerFull resp = generation.nextObject(CustomerFull.class);
        assertNotNull(customerMapper.toDto(resp));
    }

    @Test
    public void getCustomerRepNull() {
        CustFussySearching custFussySearching = mock(CustFussySearching.class);
        when(custFussySearching.sendFuzzySearch(any(CustomerFuzzySearchRequest.class), anyString())).thenReturn(null);
        customerService.custFussySearching = custFussySearching;
        CustomerFuzzySearchRequest data = createRequestData();
        NotFoundRuntimeException tt = Assertions.assertThrows(NotFoundRuntimeException.class, () ->
                        customerService.getCustomer(data)
                , "abc msg");

        Assertions.assertEquals(tt.getTitle(), "");
    }
}