package ru.mtsbank.integration.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class XMLGregorianCalendarAdapter extends XmlAdapter<String, XMLGregorianCalendar> {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

    @Override
    public XMLGregorianCalendar unmarshal(String dateString) throws Exception {
        if (dateString == null) {
            return null;
        }

        LocalDateTime dateTime = LocalDateTime.parse(dateString, DTF);
        XMLGregorianCalendar calendar = DatatypeFactory.newInstance()
                .newXMLGregorianCalendar(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        calendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
        calendar.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);

        return calendar;
    }

    @Override
    public String marshal(XMLGregorianCalendar calendar) throws Exception {
        return calendar == null ? null
                : DTF.withZone(ZoneId.systemDefault()).format(calendar.toGregorianCalendar().toInstant());
    }

}
