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
import ru.mtsbank.autocomplete.customer.model.Customer;
import ru.mtsbank.autocomplete.service.Application;
import ru.mtsbank.autocomplete.service.XMLGregorianCalendarRandomizer;
import ru.mtsbank.autocomplete.service.constant.AutoCompleteConstants;
import ru.mtsbank.autocomplete.service.exception.constant.CustomerIssueError;
import ru.mtsbank.client.MqGatewayClient;
import ru.mtsbank.integration.mts.xsd.search.CustInfoType2;
import ru.mtsbank.integration.mts.xsd.search.CustSearchListInqRs;
import ru.mtsbank.integration.mts.xsd.search.PhoneNum;
import ru.mtsbank.lib.web.model.context.MtsBankHttpRequestContext;
import ru.mtsbank.lib.web.model.exception.MtsBankException;
import ru.mtsbank.starter.xml.model.exception.MtsBankMarshallingException;
import ru.mtsbank.starter.xml.service.MtsBankXmlMessageService;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("junit")
public class CustSearchListMockTest {
    public static final String FEMALE = "FEMALE";
    @Autowired
    CustSearchList custSearchList;

    @Autowired
    MQClient mqClient;
    @Autowired
    MtsBankXmlMessageService xmlMessageService;
    @MockBean
    MtsBankHttpRequestContext requestContext;

    @BeforeEach
    public void init() {
        when(requestContext.getRequestId()).thenReturn(UUID.randomUUID().toString());
    }

    @Test
    void sendSearch() throws MtsBankMarshallingException {
        EasyRandomParameters param = new EasyRandomParameters().collectionSizeRange(1, 2).randomize(XMLGregorianCalendar.class, new XMLGregorianCalendarRandomizer()).excludeField(
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
        CustSearchListInqRs response1 = getCustSearchListInqRs(generation, "12345676");
        String result = xmlMessageService.marshal(response1);
        MqGatewayClient mqGatewayClient = mock(MqGatewayClient.class);
        when(mqGatewayClient.sendInfoMessageWithAnswer(anyString(), anyString())).thenReturn(result);
        custSearchList.mQClient = mqClient;
        custSearchList.mQClient.mqGatewayClient = mqGatewayClient;
        Customer result1 = custSearchList.sendSearch("12345676", getRequestId());
        assertNotNull(result1);
    }

    @Test
    void sendSearchNull() throws MtsBankMarshallingException {
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
        CustSearchListInqRs response1 = getCustSearchListInqRs(generation, "123456726");
        String result = xmlMessageService.marshal(response1);
        MqGatewayClient mqGatewayClient = mock(MqGatewayClient.class);
        when(mqGatewayClient.sendInfoMessageWithAnswer(anyString(), anyString())).thenReturn(result);
        custSearchList.mQClient = mqClient;
        custSearchList.mQClient.mqGatewayClient = mqGatewayClient;
        MtsBankException tt = Assertions.assertThrows(MtsBankException.class, () ->
                        custSearchList.sendSearch("123456726", getRequestId())
                , "abc msg");

        Assertions.assertEquals(tt.getTitle(), CustomerIssueError.UNPROCESSABLE_ENTITY.getTitle());
    }

    @NotNull
    private CustSearchListInqRs getCustSearchListInqRs(final EasyRandom generation, String phone) {
        CustSearchListInqRs response1 = generation.nextObject(CustSearchListInqRs.class);
        response1.getBankSvcRs().getStatus().setStatusCode("0");
        for (CustInfoType2 item : response1.getBankSvcRs().getCustList().getCustRec()) {
            item.setMatchScope("" + Math.round(Math.random() * 100));
            item.getPersonInfo().setGender(FEMALE);
            for (CustInfoType2.Sources.Source itemSource : item.getSources().getSource()) {
                itemSource.setSourceSystem(AutoCompleteConstants.RBO);
                break;
            }
            for (PhoneNum itemSource : item.getPersonInfo().getContactInfo().getPhoneNums().getPhoneNum()) {
                itemSource.setPrimary(true);
                itemSource.setPhone(phone);
                break;
            }
        }
        return response1;
    }

    private String getRequestId() {
        return requestContext.getRequestId();
    }
}