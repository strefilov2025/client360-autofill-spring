package ru.mtsbank.autocomplete.service.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.ResourceUtils;
import ru.mtsbank.autocomplete.service.Application;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("junit")
public class TypeAllFromJsonTest {
    @Autowired
    protected NriComponent nriComponent;

    @Test
    public void testMapJson() throws IOException {
        Map<String, Map<String, String>> result =
                new ObjectMapper().readValue(ResourceUtils.getFile("classpath:data/map.json"), ConcurrentHashMap.class);
        Map<String, String> gender = result.get(NriComponent.GENDER);
        Map<String, String> typeDoc = result.get(NriComponent.TYPE_DOC);
        Map<String, String> typeAddress = result.get(NriComponent.TYPE_ADDRESS);
        Map<String, Map<String, String>> all = nriComponent.getTypeAll();
        assertEquals(32, typeDoc.size());
        assertEquals(6, typeAddress.size());
        assertEquals(3, gender.size());
        assertEquals(3, all.size());
    }

    @Test
    public void testMapBean() {
        Map<String, Map<String, String>> all = nriComponent.getTypeAll();
        Map<String, String> gender = nriComponent.getGender();
        Map<String, String> typeDoc = nriComponent.getTypeDoc();
        Map<String, String> typeAddress = nriComponent.getTypeAddress();
        assertEquals(32, typeDoc.size());
        assertEquals(6, typeAddress.size());
        assertEquals(3, gender.size());
        assertEquals(3, all.size());
    }

    @Test
    public void testMapBeanAlte() throws Exception {
        nriComponent.path = "classpath:data/map1.json";
        nriComponent.afterPropertiesSet();
        Map<String, Map<String, String>> all = nriComponent.getTypeAll();
        Map<String, String> gender = nriComponent.getGender();
        Map<String, String> typeDoc = nriComponent.getTypeDoc();
        Map<String, String> typeAddress = nriComponent.getTypeAddress();
        assertEquals(32, typeDoc.size());
        assertEquals(6, typeAddress.size());
        assertEquals(3, gender.size());
        assertEquals(3, all.size());
    }
}
