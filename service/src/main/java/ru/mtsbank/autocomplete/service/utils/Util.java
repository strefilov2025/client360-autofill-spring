package ru.mtsbank.autocomplete.service.utils;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import ru.mtsbank.autocomplete.service.exception.constant.CustomerIssueError;
import ru.mtsbank.lib.web.model.exception.MtsBankException;

import javax.xml.bind.DatatypeConverter;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class Util {

    @NotNull
    public XMLGregorianCalendar getXmlGregorianCalendar() {
        final XMLGregorianCalendar calendar;
        LocalDateTime dateTime = LocalDateTime.now(ZoneId.systemDefault());
        try {
            calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(dateTime));
            calendar.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
        } catch (DatatypeConfigurationException e) {
            throw new MtsBankException(CustomerIssueError.INTERNAL_SERVER_ERROR.getHttpStatus(), CustomerIssueError.INTERNAL_SERVER_ERROR.getCode(),
                    CustomerIssueError.INTERNAL_SERVER_ERROR.getTitle(),
                    e.getMessage());
        }

        return calendar;
    }

    public long hashCode(final String phone) {
        String inputx = phone;
        boolean flagPlus7 = phone.startsWith("+7");
        boolean flag7 = phone.startsWith("7");
        if (flagPlus7) {
            inputx = phone.substring(2);
        }
        if (flag7) {
            inputx = phone.substring(1);
        }
        String input = inputx + "CLIENT360";
        try {
            byte[] hashBytes = MessageDigest.getInstance("MD5")
                    .digest(input.getBytes(StandardCharsets.UTF_8));
            String hash = DatatypeConverter.printHexBinary(hashBytes);
            String hashs = hash.substring(hash.length() - 3);
            long code = Long.parseLong(hashs, 16);
            return code;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
}
