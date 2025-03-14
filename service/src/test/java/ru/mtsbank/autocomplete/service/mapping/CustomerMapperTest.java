package ru.mtsbank.autocomplete.service.mapping;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mtsbank.autocomplete.service.Application;
import ru.mtsbank.autocomplete.service.XMLGregorianCalendarRandomizer;

import javax.xml.datatype.XMLGregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("junit")
public
class CustomerMapperTest {
    @Autowired
    CustomerMapper customerMapper;

    @Test
    public void toAddress() {
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
        customer_proto.Address addr = generation.nextObject(customer_proto.Address.class);
        assertNotNull(customerMapper.toAddress(addr));
    }
}