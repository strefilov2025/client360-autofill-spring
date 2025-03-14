package ru.mtsbank.autocomplete.service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import ru.mtsbank.autocomplete.customer.model.Customer;
import ru.mtsbank.autocomplete.mtsb.model.MtsbPerson;
import ru.mtsbank.autocomplete.service.Application;
import ru.mtsbank.autocomplete.service.XMLGregorianCalendarRandomizer;
import ru.mtsbank.autocomplete.service.exception.constant.CustomerIssueError;
import ru.mtsbank.lib.web.model.exception.MtsBankException;

import javax.xml.datatype.XMLGregorianCalendar;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("junit")
public class MtsbClientSAOTest {

    @Autowired
    MtsbClientSAO mtsbClientSAO;

    @Test
    public void getPersonFile() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("temp.json");
        String data = readFromInputStream(inputStream);
        ObjectMapper mapper = new ObjectMapper();
        MtsbPerson response1 = mapper.readValue(data, MtsbPerson.class);
        RestTemplate restTemplate = mock(RestTemplate.class);
        ResponseEntity<MtsbPerson> result = ResponseEntity.ok(response1);
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class), any(Map.class))).thenReturn(result);
        mtsbClientSAO.restTemplate = restTemplate;
        Customer tt = mtsbClientSAO.getPerson("122334");
        Assertions.assertNotNull(tt);
    }

    @Test
    public void getPerson() {
        EasyRandom generation = getEasyRandom(2);
        RestTemplate restTemplate = mock(RestTemplate.class);
        MtsbPerson response1 = generation.nextObject(MtsbPerson.class);
        response1.setCntSim(1);
        response1.setFirstNameSpgr(response1.getFirstNameMdm());
        response1.setLastNameSpgr(response1.getLastNameMdm());
        response1.setLastNameSpgr("");
        response1.setFirstNameSpgr("");
        ResponseEntity<MtsbPerson> result = ResponseEntity.ok(response1);
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class), any(Map.class))).thenReturn(result);
        mtsbClientSAO.restTemplate = restTemplate;
        Customer tt = mtsbClientSAO.getPerson("122334");
        Assertions.assertNotNull(tt);
    }

    @Test
    public void getPersonNull() {
        EasyRandom generation = getEasyRandom(2);
        RestTemplate restTemplate = mock(RestTemplate.class);
        MtsbPerson response1 = generation.nextObject(MtsbPerson.class);
        response1.setCntSim(4);
        response1.setFirstNameSpgr(response1.getFirstNameMdm());
        response1.setLastNameSpgr(response1.getLastNameMdm());
        ResponseEntity<MtsbPerson> result = ResponseEntity.ok(response1);
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class), any(Map.class))).thenReturn(result);
        mtsbClientSAO.restTemplate = restTemplate;

        Customer tt = mtsbClientSAO.getPerson("122334");
        Assertions.assertNull(tt);

    }

    @Test
    public void getPersonNotFount() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        ResponseEntity.HeadersBuilder result = ResponseEntity.notFound();
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class), any(Map.class))).thenReturn(result.build());
        mtsbClientSAO.restTemplate = restTemplate;
        Customer tt = mtsbClientSAO.getPerson("122334");
        Assertions.assertNull(tt);
    }

    @Test
    public void getPersonExption() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        ResponseEntity.HeadersBuilder result = ResponseEntity.badRequest();
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class), any(Map.class))).thenReturn(result.build());
        mtsbClientSAO.restTemplate = restTemplate;

        MtsBankException tt = Assertions.assertThrows(MtsBankException.class, () ->
                        mtsbClientSAO.getPerson("122334")
                , "abc msg");
        Assertions.assertEquals(tt.getTitle(), CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getTitle());

    }

    @Test
    public void getPersonHttpClientErrorException() {
        RestTemplate restTemplate = mock(RestTemplate.class);

        given(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class), any(Map.class))).willAnswer(invocation -> {
            throw new HttpClientErrorException(CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getHttpStatus());
        });

        mtsbClientSAO.restTemplate = restTemplate;

        MtsBankException tt = Assertions.assertThrows(MtsBankException.class, () ->
                        mtsbClientSAO.getPerson("122334")
                , "abc msg");
        Assertions.assertEquals(tt.getTitle(), CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getTitle());

    }

    @Test
    public void getPersonHttpStatusCodeException() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        HttpStatusCodeException ex1 = new HttpClientErrorException(
                HttpStatus.BAD_REQUEST, null, null, StandardCharsets.US_ASCII);
        given(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class), any(Map.class))).willAnswer(invocation -> {
            throw ex1;
        });

        mtsbClientSAO.restTemplate = restTemplate;

        MtsBankException tt = Assertions.assertThrows(MtsBankException.class, () ->
                        mtsbClientSAO.getPerson("122334")
                , "abc msg");
        Assertions.assertEquals(tt.getTitle(), CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getTitle());

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

    private String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}