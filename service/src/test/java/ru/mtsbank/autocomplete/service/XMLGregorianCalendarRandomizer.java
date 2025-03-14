package ru.mtsbank.autocomplete.service;

import org.jeasy.random.api.Randomizer;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.ZoneOffset;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class XMLGregorianCalendarRandomizer implements Randomizer<XMLGregorianCalendar> {

    @Override
    public XMLGregorianCalendar getRandomValue() {
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar((GregorianCalendar) GregorianCalendar.getInstance(TimeZone.getTimeZone(ZoneOffset.UTC)));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
