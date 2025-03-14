package ru.mtsbank.autocomplete.service.service;


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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import ru.mtsbank.autocomplete.customer.model.CustomerFuzzySearchRequest;
import ru.mtsbank.autocomplete.customer.model.Document;
import ru.mtsbank.autocomplete.customer.model.InlineResponse200;
import ru.mtsbank.autocomplete.customer.model.Phone;
import ru.mtsbank.autocomplete.service.Application;
import ru.mtsbank.autocomplete.service.XMLGregorianCalendarRandomizer;
import ru.mtsbank.autocomplete.service.constant.AutoCompleteConstants;
import ru.mtsbank.autocomplete.service.exception.constant.CustomerIssueError;
import ru.mtsbank.client.MqGatewayClient;
import ru.mtsbank.integration.mts.xsd.fuzzy.CustFuzzySearchInqRs;
import ru.mtsbank.integration.mts.xsd.fuzzy.CustInfoType2;
import ru.mtsbank.lib.web.model.context.MtsBankHttpRequestContext;
import ru.mtsbank.lib.web.model.exception.MtsBankException;
import ru.mtsbank.starter.xml.model.exception.MtsBankMarshallingException;
import ru.mtsbank.starter.xml.service.MtsBankXmlMessageService;

import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("junit")
public class CustFussySearchingMockTest {
    public static final String FEMALE = "FEMALE";

    @Autowired
    MtsBankXmlMessageService xmlMessageService;

    @Autowired
    CustFussySearching custFussySearching;
    @Autowired
    CustSearchList custSearchList;

    @Autowired
    MQClient mqClient;
    @MockBean
    MtsBankHttpRequestContext requestContext;

    @BeforeEach
    public void init() {
        when(requestContext.getRequestId()).thenReturn(UUID.randomUUID().toString());
    }

    @Test
    public void sendFuzzySearch() throws MtsBankMarshallingException {
        EasyRandom generation = getEasyRandom(3);
        CustFuzzySearchInqRs response1 = getCustFuzzySearchInqRs(generation, "0");
        String result = xmlMessageService.marshal(response1);
        CustFuzzySearchInqRs response2 = getCustFuzzySearchInqRs(generation, "0");
        String result2 = xmlMessageService.marshal(response2);
        MqGatewayClient mqGatewayClient2 = mock(MqGatewayClient.class);
        MqGatewayClient mqGatewayClient1 = mock(MqGatewayClient.class);
        when(mqGatewayClient2.sendInfoMessageWithAnswer(anyString(), anyString())).thenReturn(result);
        when(mqGatewayClient1.sendInfoMessageWithAnswer(anyString(), anyString())).thenReturn(result2);

        custFussySearching.custSearchList = custSearchList;
        custFussySearching.mqClient = mqClient;
        custFussySearching.custSearchList.mQClient = getmQClient();
        MqGatewayClient old1 = custFussySearching.custSearchList.mQClient.mqGatewayClient;
        MqGatewayClient old2 = custFussySearching.mqClient.mqGatewayClient;
        custFussySearching.custSearchList.mQClient.mqGatewayClient = mqGatewayClient1;
        custFussySearching.mqClient.mqGatewayClient = mqGatewayClient2;
        MtsBankException tt = Assertions.assertThrows(MtsBankException.class, () ->
                        custFussySearching.sendFuzzySearch(createRequestDataNotNull3(), getRequestId())
                , "abc msg");

        custFussySearching.custSearchList.mQClient.mqGatewayClient = old1;
        custFussySearching.mqClient.mqGatewayClient = old2;
        Assertions.assertEquals(tt.getTitle(), CustomerIssueError.UNPROCESSABLE_ENTITY.getTitle());


    }

    @Test
    public void sendFuzzySearch1() throws MtsBankMarshallingException, ParseException {
        EasyRandom generation = getEasyRandom(2);
        CustFuzzySearchInqRs response1 = getCustFuzzySearchInqRs(generation, "0");
        String result = xmlMessageService.marshal(response1);
        CustFuzzySearchInqRs response2 = getCustFuzzySearchInqRs(generation, "0");
        String result2 = xmlMessageService.marshal(response2);
        MqGatewayClient mqGatewayClient = mock(MqGatewayClient.class);
        MqGatewayClient mqGatewayClient1 = mock(MqGatewayClient.class);
        when(mqGatewayClient.sendInfoMessageWithAnswer(anyString(), anyString())).thenReturn(result);
        when(mqGatewayClient1.sendInfoMessageWithAnswer(anyString(), anyString())).thenReturn(result2);
        custFussySearching.custSearchList = custSearchList;
        custFussySearching.mqClient = mqClient;

        custFussySearching.custSearchList.mQClient = getmQClient();

        MqGatewayClient old1 = custFussySearching.custSearchList.mQClient.mqGatewayClient;
        MqGatewayClient old2 = custFussySearching.mqClient.mqGatewayClient;
        custFussySearching.custSearchList.mQClient.mqGatewayClient = mqGatewayClient1;
        custFussySearching.mqClient.mqGatewayClient = mqGatewayClient;
        InlineResponse200 result1 = custFussySearching.sendFuzzySearch(createRequestDataNotNull4(), getRequestId());
        custFussySearching.custSearchList.mQClient.mqGatewayClient = old1;
        custFussySearching.mqClient.mqGatewayClient = old2;
        assertNotNull(result1);

    }

    @Test
    public void sendFuzzySearch2() throws MtsBankMarshallingException, ParseException {
        EasyRandom generation = getEasyRandom(2);
        CustFuzzySearchInqRs response1 = getCustFuzzySearchInqRsNullRBO(generation);
        String result = xmlMessageService.marshal(response1);
        CustFuzzySearchInqRs response2 = getCustFuzzySearchInqRs(generation, "0");
        String result2 = xmlMessageService.marshal(response2);
        MqGatewayClient mqGatewayClient = mock(MqGatewayClient.class);
        MqGatewayClient mqGatewayClient1 = mock(MqGatewayClient.class);
        when(mqGatewayClient.sendInfoMessageWithAnswer(anyString(), anyString())).thenReturn(result);
        when(mqGatewayClient1.sendInfoMessageWithAnswer(anyString(), anyString())).thenReturn(result2);
        custFussySearching.custSearchList = custSearchList;
        custFussySearching.mqClient = mqClient;
        custFussySearching.custSearchList.mQClient = getmQClient();

        MqGatewayClient old1 = custFussySearching.custSearchList.mQClient.mqGatewayClient;
        MqGatewayClient old2 = custFussySearching.mqClient.mqGatewayClient;
        custFussySearching.custSearchList.mQClient.mqGatewayClient = mqGatewayClient1;
        custFussySearching.mqClient.mqGatewayClient = mqGatewayClient;
        InlineResponse200 result1 = custFussySearching.sendFuzzySearch(createRequestDataNotNull7(), getRequestId());
        custFussySearching.custSearchList.mQClient.mqGatewayClient = old1;
        custFussySearching.mqClient.mqGatewayClient = old2;
        assertNotNull(result1);

    }

    @Test
    public void sendFuzzySearchMtsBankException() throws MtsBankMarshallingException {
        EasyRandom generation = getEasyRandom(2);
        CustFuzzySearchInqRs response1 = getCustFuzzySearchInqRs(generation, "0");
        String result = xmlMessageService.marshal(response1);
        CustFuzzySearchInqRs response2 = getCustFuzzySearchInqRs(generation, "1");
        String result2 = xmlMessageService.marshal(response2);
        MqGatewayClient mqGatewayClient = mock(MqGatewayClient.class);
        MqGatewayClient mqGatewayClient1 = mock(MqGatewayClient.class);
        when(mqGatewayClient.sendInfoMessageWithAnswer(anyString(), anyString())).thenReturn(result);
        when(mqGatewayClient1.sendInfoMessageWithAnswer(anyString(), anyString())).thenReturn(result2);
        custFussySearching.custSearchList = custSearchList;
        custFussySearching.mqClient = mqClient;
        custFussySearching.custSearchList.mQClient = mqClient;

        MqGatewayClient old1 = custFussySearching.custSearchList.mQClient.mqGatewayClient;
        MqGatewayClient old2 = custFussySearching.mqClient.mqGatewayClient;
        custFussySearching.mqClient.mqGatewayClient = mqGatewayClient;
        custFussySearching.custSearchList.mQClient.mqGatewayClient = mqGatewayClient1;

        MtsBankException tt = Assertions.assertThrows(MtsBankException.class, () ->
                        custFussySearching.sendFuzzySearch(createRequestDataNotNull5(), getRequestId())
                , "abc msg");

        custFussySearching.custSearchList.mQClient.mqGatewayClient = old1;
        custFussySearching.mqClient.mqGatewayClient = old2;
        Assertions.assertEquals(tt.getTitle(), CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getTitle());

    }

    @NotNull
    private MQClient getmQClient() {
        MQClient result = new MQClient();
        result.xmlMessageService = xmlMessageService;
        return result;
    }

    @Test
    public void sendFuzzySearchMtsBankException1() throws MtsBankMarshallingException {
        EasyRandom generation = getEasyRandom(2);
        CustFuzzySearchInqRs response1 = getCustFuzzySearchInqRs(generation, "1");
        String result = xmlMessageService.marshal(response1);
        CustFuzzySearchInqRs response2 = getCustFuzzySearchInqRs(generation, "1");
        String result2 = xmlMessageService.marshal(response2);
        MqGatewayClient mqGatewayClient = mock(MqGatewayClient.class);
        MqGatewayClient mqGatewayClient1 = mock(MqGatewayClient.class);
        when(mqGatewayClient.sendInfoMessageWithAnswer(anyString(), anyString())).thenReturn(result);
        when(mqGatewayClient1.sendInfoMessageWithAnswer(anyString(), anyString())).thenReturn(result2);
        custFussySearching.custSearchList = custSearchList;
        custFussySearching.mqClient = mqClient;
        custFussySearching.custSearchList.mQClient = getmQClient();

        MqGatewayClient old1 = custFussySearching.custSearchList.mQClient.mqGatewayClient;
        MqGatewayClient old2 = custFussySearching.mqClient.mqGatewayClient;
        custFussySearching.custSearchList.mQClient.mqGatewayClient = mqGatewayClient1;
        custFussySearching.mqClient.mqGatewayClient = mqGatewayClient;
        MtsBankException tt = Assertions.assertThrows(MtsBankException.class, () ->
                        custFussySearching.sendFuzzySearch(createRequestDataNotNull5(), getRequestId())
                , "abc msg");

        custFussySearching.custSearchList.mQClient.mqGatewayClient = old1;
        custFussySearching.mqClient.mqGatewayClient = old2;
        Assertions.assertEquals(tt.getTitle(), CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getTitle());
    }

    @Test
    public void sendFuzzySearchMtsBankException2() throws MtsBankMarshallingException {
        EasyRandom generation = getEasyRandom(2);

        CustFuzzySearchInqRs response2 = getCustFuzzySearchInqRs(generation, "1");
        String result2 = xmlMessageService.marshal(response2);


        MqGatewayClient mqGatewayClient = mock(MqGatewayClient.class);
        MqGatewayClient mqGatewayClient1 = mock(MqGatewayClient.class);
        given(mqGatewayClient.sendInfoMessageWithAnswer(anyString(), anyString())).willAnswer(invocation -> {
            throw new RestClientException("abc msg");
        });
        when(mqGatewayClient1.sendInfoMessageWithAnswer(anyString(), anyString())).thenReturn(result2);
        custFussySearching.custSearchList = custSearchList;
        custFussySearching.mqClient = mqClient;
        custFussySearching.custSearchList.mQClient = getmQClient();

        MqGatewayClient old1 = custFussySearching.custSearchList.mQClient.mqGatewayClient;
        MqGatewayClient old2 = custFussySearching.mqClient.mqGatewayClient;
        custFussySearching.custSearchList.mQClient.mqGatewayClient = mqGatewayClient1;
        custFussySearching.mqClient.mqGatewayClient = mqGatewayClient;
        MtsBankException tt = Assertions.assertThrows(MtsBankException.class, () ->
                        custFussySearching.sendFuzzySearch(createRequestDataNotNull5(), getRequestId())
                , "abc msg");
        custFussySearching.custSearchList.mQClient.mqGatewayClient = old1;
        custFussySearching.mqClient.mqGatewayClient = old2;

        Assertions.assertEquals(tt.getTitle(), CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getTitle());
    }

    @Test
    public void sendFuzzySearchMtsBankException3() throws MtsBankMarshallingException {
        EasyRandom generation = getEasyRandom(2);

        CustFuzzySearchInqRs response2 = getCustFuzzySearchInqRs(generation, "0");
        String result2 = xmlMessageService.marshal(response2);


        MqGatewayClient mqGatewayClient = mock(MqGatewayClient.class);
        MqGatewayClient mqGatewayClient1 = mock(MqGatewayClient.class);
        when(mqGatewayClient.sendInfoMessageWithAnswer(anyString(), anyString())).thenReturn(result2);
        given(mqGatewayClient1.sendInfoMessageWithAnswer(anyString(), anyString())).willAnswer(invocation -> {
            throw new RestClientException("abc msg");
        });

        custFussySearching.custSearchList = custSearchList;
        custFussySearching.mqClient = mqClient;
        custFussySearching.custSearchList.mQClient = getmQClient();

        MqGatewayClient old1 = custFussySearching.custSearchList.mQClient.mqGatewayClient;
        MqGatewayClient old2 = custFussySearching.mqClient.mqGatewayClient;

        custFussySearching.mqClient.mqGatewayClient = mqGatewayClient;
        custFussySearching.custSearchList.mQClient.mqGatewayClient = mqGatewayClient1;
        MtsBankException tt = Assertions.assertThrows(MtsBankException.class, () ->
                        custFussySearching.sendFuzzySearch(createRequestDataNotNull5(), getRequestId())
                , "abc msg");

        custFussySearching.custSearchList.mQClient.mqGatewayClient = old1;
        custFussySearching.mqClient.mqGatewayClient = old2;
        Assertions.assertEquals(tt.getTitle(), CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getTitle());
    }

    @Test
    public void sendFuzzySearchMtsBankException4() throws MtsBankMarshallingException {
        EasyRandom generation = getEasyRandom(3);
        CustFuzzySearchInqRs response1 = getCustFuzzySearchInqRsRow(generation);
        String result = xmlMessageService.marshal(response1);
        CustFuzzySearchInqRs response2 = getCustFuzzySearchInqRs(generation, "0");
        String result2 = xmlMessageService.marshal(response2);


        MqGatewayClient mqGatewayClient = mock(MqGatewayClient.class);
        MqGatewayClient mqGatewayClient1 = mock(MqGatewayClient.class);
        when(mqGatewayClient.sendInfoMessageWithAnswer(anyString(), anyString())).thenReturn(result);
        when(mqGatewayClient1.sendInfoMessageWithAnswer(anyString(), anyString())).thenReturn(result2);


        custFussySearching.custSearchList = custSearchList;
        custFussySearching.mqClient = mqClient;
        custFussySearching.custSearchList.mQClient = getmQClient();

        MqGatewayClient old1 = custFussySearching.custSearchList.mQClient.mqGatewayClient;
        MqGatewayClient old2 = custFussySearching.mqClient.mqGatewayClient;
        custFussySearching.custSearchList.mQClient.mqGatewayClient = mqGatewayClient1;
        custFussySearching.mqClient.mqGatewayClient = mqGatewayClient;
        MtsBankException tt = Assertions.assertThrows(MtsBankException.class, () ->
                        custFussySearching.sendFuzzySearch(createRequestDataNotNull5(), getRequestId())
                , "abc msg");

        custFussySearching.custSearchList.mQClient.mqGatewayClient = old1;
        custFussySearching.mqClient.mqGatewayClient = old2;
        Assertions.assertEquals(tt.getTitle(), CustomerIssueError.UNPROCESSABLE_ENTITY.getTitle());
    }

    @Test
    public void sendFuzzySearchMtsBankException5() throws MtsBankMarshallingException {
        EasyRandom generation = getEasyRandom(3);
        CustFuzzySearchInqRs response1 = getCustFuzzySearchInqRsRBO(generation);
        String result = xmlMessageService.marshal(response1);
        CustFuzzySearchInqRs response2 = getCustFuzzySearchInqRs(generation, "0");
        String result2 = xmlMessageService.marshal(response2);


        MqGatewayClient mqGatewayClient = mock(MqGatewayClient.class);
        MqGatewayClient mqGatewayClient1 = mock(MqGatewayClient.class);
        when(mqGatewayClient.sendInfoMessageWithAnswer(anyString(), anyString())).thenReturn(result);
        when(mqGatewayClient1.sendInfoMessageWithAnswer(anyString(), anyString())).thenReturn(result2);


        custFussySearching.custSearchList = custSearchList;
        custFussySearching.mqClient = mqClient;
        custFussySearching.custSearchList.mQClient = getmQClient();

        MqGatewayClient old1 = custFussySearching.custSearchList.mQClient.mqGatewayClient;
        MqGatewayClient old2 = custFussySearching.mqClient.mqGatewayClient;
        custFussySearching.custSearchList.mQClient.mqGatewayClient = mqGatewayClient1;
        custFussySearching.mqClient.mqGatewayClient = mqGatewayClient;
        MtsBankException tt = Assertions.assertThrows(MtsBankException.class, () ->
                        custFussySearching.sendFuzzySearch(createRequestDataNotNull5(), getRequestId())
                , "abc msg");
        custFussySearching.custSearchList.mQClient.mqGatewayClient = old1;
        custFussySearching.mqClient.mqGatewayClient = old2;

        Assertions.assertEquals(tt.getTitle(), CustomerIssueError.UNPROCESSABLE_RBO.getTitle());
    }

    @Test
    public void sendMtsBankMarshallingException() {

        MqGatewayClient mqGatewayClient = mock(MqGatewayClient.class);

        given(mqGatewayClient.sendInfoMessageWithAnswer(anyString(), anyString())).willAnswer(invocation -> {
            throw new MtsBankMarshallingException("abc msg");
        });

        custFussySearching.custSearchList = custSearchList;
        custFussySearching.mqClient = mqClient;
        custFussySearching.custSearchList.mQClient = getmQClient();


        MqGatewayClient old2 = custFussySearching.mqClient.mqGatewayClient;

        custFussySearching.mqClient.mqGatewayClient = mqGatewayClient;
        MtsBankException tt = Assertions.assertThrows(MtsBankException.class, () ->
                        custFussySearching.sendFuzzySearch(createRequestDataNotNull5(), getRequestId())
                , "abc msg");


        custFussySearching.mqClient.mqGatewayClient = old2;
        Assertions.assertEquals(tt.getTitle(), CustomerIssueError.INTERNAL_SERVER_ERROR.getTitle());
    }

    @NotNull
    private EasyRandom getEasyRandom(final int maxCollectionSize) {
        EasyRandomParameters param = new EasyRandomParameters().collectionSizeRange(1, maxCollectionSize).randomize(XMLGregorianCalendar.class, new XMLGregorianCalendarRandomizer()).excludeField(
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


        return new EasyRandom(param);
    }

    @NotNull
    private CustFuzzySearchInqRs getCustFuzzySearchInqRs(final EasyRandom generation, final String value) {
        CustFuzzySearchInqRs response1 = generation.nextObject(CustFuzzySearchInqRs.class);
        response1.getBankSvcRs().getStatus().setStatusCode(value);
        for (CustInfoType2 item : response1.getBankSvcRs().getCustList().getCustRec()) {
            item.setMatchScope("" + Math.round(Math.random() * 100));
            item.getPersonInfo().setGender(FEMALE);
            for (CustInfoType2.Sources.Source itemSource : item.getSources().getSource()) {
                itemSource.setSourceSystem(AutoCompleteConstants.RBO);
                break;
            }
        }
        return response1;
    }

    @NotNull
    private CustFuzzySearchInqRs getCustFuzzySearchInqRsRow(final EasyRandom generation) {
        CustFuzzySearchInqRs response1 = generation.nextObject(CustFuzzySearchInqRs.class);
        response1.getBankSvcRs().getStatus().setStatusCode("0");
        for (CustInfoType2 item : response1.getBankSvcRs().getCustList().getCustRec()) {
            item.setMatchScope("1");
            item.getPersonInfo().setGender(FEMALE);
            for (CustInfoType2.Sources.Source itemSource : item.getSources().getSource()) {
                itemSource.setSourceSystem(AutoCompleteConstants.RBO);
                break;
            }
        }
        return response1;
    }

    @NotNull
    private CustFuzzySearchInqRs getCustFuzzySearchInqRsRBO(final EasyRandom generation) {
        CustFuzzySearchInqRs response1 = generation.nextObject(CustFuzzySearchInqRs.class);
        response1.getBankSvcRs().getStatus().setStatusCode("0");
        for (CustInfoType2 item : response1.getBankSvcRs().getCustList().getCustRec()) {
            item.setMatchScope("" + Math.round(Math.random() * 100));
            item.getPersonInfo().setGender(FEMALE);
            for (CustInfoType2.Sources.Source itemSource : item.getSources().getSource()) {
                itemSource.setSourceSystem(AutoCompleteConstants.RBO);
            }
        }
        return response1;
    }

    @NotNull
    private CustFuzzySearchInqRs getCustFuzzySearchInqRsNullRBO(final EasyRandom generation) {
        CustFuzzySearchInqRs response1 = generation.nextObject(CustFuzzySearchInqRs.class);
        response1.getBankSvcRs().getStatus().setStatusCode("0");
        for (CustInfoType2 item : response1.getBankSvcRs().getCustList().getCustRec()) {
            item.setMatchScope("" + Math.round(Math.random() * 100));
            item.getPersonInfo().setGender(FEMALE);

        }
        return response1;
    }

    private CustomerFuzzySearchRequest createRequestDataNotNull5() throws ParseException {
        CustomerFuzzySearchRequest data = new CustomerFuzzySearchRequest();
        data.setFirstName("Александрww");
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

    private CustomerFuzzySearchRequest createRequestDataNotNull4() throws ParseException {
        CustomerFuzzySearchRequest data = new CustomerFuzzySearchRequest();
        data.setFirstName("Александр22");
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

    private CustomerFuzzySearchRequest createRequestDataNotNull7() throws ParseException {
        CustomerFuzzySearchRequest data = new CustomerFuzzySearchRequest();
        data.setFirstName("Александр2222");
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