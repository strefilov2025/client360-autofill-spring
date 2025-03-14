package ru.mtsbank.autocomplete.service.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import ru.mtsbank.autocomplete.customer.model.Customer;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * normative reference information for types from Customer to MDM
 */
@Getter
@Component
@Scope("singleton")
public class NriComponent implements InitializingBean {
    public static final String GENDER = "gender";
    public static final String TYPE_DOC = "typeDoc";
    public static final String TYPE_ADDRESS = "typeAddress";
    protected String path = "classpath:data/map.json";
    private Map<String, String> gender;
    private Map<String, String> typeDoc;
    private Map<String, String> typeAddress;
    private Map<String, Map<String, String>> typeAll;

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            typeAll = new ObjectMapper().readValue(ResourceUtils.getFile(path), ConcurrentHashMap.class);
            gender = typeAll.get(GENDER);
            typeDoc = typeAll.get(TYPE_DOC);
            typeAddress = typeAll.get(TYPE_ADDRESS);
        } catch (IOException e) {
            gender = new ConcurrentHashMap<>();
            typeDoc = new ConcurrentHashMap<>();
            typeAddress = new ConcurrentHashMap<>();
            typeAll = new ConcurrentHashMap<>();
            typeAll.put(GENDER, gender);
            typeAll.put(TYPE_DOC, typeDoc);
            typeAll.put(TYPE_ADDRESS, typeAddress);

            gender.put("M", Customer.GenderEnum.MALE.toString());
            gender.put("F", Customer.GenderEnum.FEMALE.toString());
            gender.put("", Customer.GenderEnum.UNKNOWN.toString());
            typeDoc.put("02", "OVERSEAS_PASSPORT_USSR");
            typeDoc.put("03", "BIRTH_CERTIFICATE");
            typeDoc.put("04", "OFFICER_CERTIFICATE");
            typeDoc.put("05", "PRISON_CERTIFICATE");
            typeDoc.put("06", "PASSPORT_MINMORFLOTA");
            // typeDoc.put("08", "TEMPORARY_MILITARY_TICKET");
            typeDoc.put("09", "DIPLOMATIC_PASSPORT_RU");
            typeDoc.put("10", "PASSPORT_FOREIGNER");
            typeDoc.put("010", "RESIDENCY");
            //typeDoc.put("11", "REFUGEE_IMMIGRANTS_CERTIFICATE");
            typeDoc.put("12", "RESIDENCY");
            typeDoc.put("13", "REFUGEE_CERTIFICATE");
            typeDoc.put("14", "TEMPORARY_CERTIFICATE");
            typeDoc.put("21", "PASSPORT_RU");
            typeDoc.put("22", "OVERSEAS_PASSPORT_RU");
            //typeDoc.put("23", "FOREIGN_BIRTH_CERT");
            typeDoc.put("26", "PASSPORT_SAILOR");
            typeDoc.put("27", "MILITARY_TICKET_OFFICER");
            //typeDoc.put("29", "TEMP_REFUGE");
            //typeDoc.put("32", "TEMPORARY_STATELESS_CERTIFICATE");
            //typeDoc.put("40", "VISA");
            typeDoc.put("42", "APP_TEMP_ASYLUM");
            typeDoc.put("1607", "PATENT");
            typeDoc.put("ARMY_DOC", "MILITARY_TICKET");
            typeDoc.put("DRIVER_DOC", "DRIVING_LICENSE");
            typeDoc.put("FORCE_REPATR", "IDP_CERTIFICATE");
            typeDoc.put("MIGR", "MIGRATION_CARD");
            typeDoc.put("PASPORT", "PASSPORT_USSR");
            typeDoc.put("PENSIONER", "PENSION_CERTIFICATE");
            typeDoc.put("PERMISSION_N", "TEMPORARY_RESIDENCE");
            typeDoc.put("TEMP_ASYLUM", "TEMP_REFUGE");
            typeDoc.put("UDOST_DEAD", "DEATH_CERT");
            typeDoc.put("VISA_N", "VISA");
            typeDoc.put("ИНН", "INN_REGISTRATION");
            typeDoc.put("Свидетельство", "TEMP_REFUGE");
            typeDoc.put("УКФМС", "FMS_CODE");
            typeDoc.put("УЛВ", "MILITARY_CERTIFICATE");

            typeDoc.put("", "MISSED");
            //typeDoc.put("Паспорт гражданина СССР", "PASSPORT_USSR");
            //typeDoc.put("Военный билет", "MILITARY_TICKET");
            //typeDoc.put("99", "TEMP_NATIONAL_CERTIFICATE");
            typeAddress.put("REAL_LIVE", "HOME");
            typeAddress.put("POST", "POSTAL");
            typeAddress.put("REGISTRATION", "CONSTANT_REGISTRATION");
            typeAddress.put("TEMPORARY_REG", "REGISTRATION");
            typeAddress.put("CONTACT", "POSTAL");
            typeAddress.put("", "OTHER");
        }
    }
}
