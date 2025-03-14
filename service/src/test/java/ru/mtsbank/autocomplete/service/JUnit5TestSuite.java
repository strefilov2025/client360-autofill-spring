package ru.mtsbank.autocomplete.service;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import ru.mtsbank.autocomplete.service.component.TypeAllFromJsonTest;
import ru.mtsbank.autocomplete.service.mapping.CustomerMapperTest;
import ru.mtsbank.autocomplete.service.service.*;

@Suite
@SelectClasses({MtsbClientSAOTest.class, CustomerMapperTest.class, RestMockTest.class, RestV2MockTest.class, CustFussySearchingTest.class, RestExeptionTest.class, RestStatusExeptionTest.class, TypeAllFromJsonTest.class, CustFussySearchingMockTest.class, CustSearchListMockTest.class})
public class JUnit5TestSuite {
}
