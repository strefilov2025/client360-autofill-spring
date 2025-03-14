package ru.mtsbank.autocomplete.service;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import ru.mtsbank.autocomplete.service.controller.RestRealTest;
import ru.mtsbank.autocomplete.service.service.CustFussySearchingRealTest;
import ru.mtsbank.autocomplete.service.service.CustSearchingRealTest;
import ru.mtsbank.autocomplete.service.service.CustomerDomainGrpcClientSAORealTest;

@Suite
@SelectClasses({CustFussySearchingRealTest.class, CustSearchingRealTest.class, RestRealTest.class, CustomerDomainGrpcClientSAORealTest.class})
public class JUnit5TestRealSuite {
}
