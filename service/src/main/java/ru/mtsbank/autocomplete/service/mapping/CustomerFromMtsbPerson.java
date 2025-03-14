package ru.mtsbank.autocomplete.service.mapping;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.mtsbank.autocomplete.customer.model.Address;
import ru.mtsbank.autocomplete.customer.model.Customer;
import ru.mtsbank.autocomplete.customer.model.Document;
import ru.mtsbank.autocomplete.customer.model.Phone;
import ru.mtsbank.autocomplete.mtsb.model.MtsbPerson;

import java.util.ArrayList;
import java.util.List;


@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CustomerFromMtsbPerson {

    @Mapping(target = "fullName", expression = "java(mtsbPerson.getLastNameMdm()+\" \"+mtsbPerson.getFirstNameMdm()+\" \"+mtsbPerson.getMidNameMdm())")
    @Mapping(target = "lastName", source = "lastNameMdm")
    @Mapping(target = "firstName", source = "firstNameMdm")
    @Mapping(target = "middleName", source = "midNameMdm")
    @Mapping(target = "inn", source = "innNumber")
    @Mapping(target = "birthDate", source = "birthdateDt")
    @Mapping(target = "birthPlace", source = "birthPlace")
    @Mapping(target = "sourceSystem", constant = "GR HUB")
    Customer toDto(MtsbPerson mtsbPerson);

    default Customer toDto(final MtsbPerson mtsbPerson, final String phone) {
        Customer result = toDto(mtsbPerson);
        List<Address> addresses = new ArrayList<>(3);
        Address item = new Address();

        Address home = toAddressHome(mtsbPerson);
        item.setAddressType(home.getAddressType());
        if (!item.equals(home)) {
            addresses.add(home);
        }
        Address reg = toAddressRegistration(mtsbPerson);
        item.setAddressType(reg.getAddressType());
        if (!item.equals(reg)) {
            addresses.add(reg);
        }
        Address conreg = toaddressConstantRegistration(mtsbPerson);
        item.setAddressType(conreg.getAddressType());
        if (!item.equals(conreg)) {
            addresses.add(conreg);
        }

        result.addresses(addresses);
        if (mtsbPerson.getInnNumber() != null && !mtsbPerson.getInnNumber().isBlank()) {
            Document doc = toDocument(mtsbPerson);
            List<Document> listDoc = new ArrayList<>();
            listDoc.add(doc);
            result.setDocuments(listDoc);
        }
        List<Phone> listPhones = new ArrayList<>(1);
        Phone phoneItem = toPhone(phone);
        listPhones.add(phoneItem);
        result.setPhones(listPhones);
        return result;
    }

    @Mapping(target = "series", source = "docSeries")
    @Mapping(target = "number", source = "docNumber")
    @Mapping(target = "issueDate", source = "docIssueDt")
    @Mapping(target = "issuedBy", source = "docIssueAuthority")
    @Mapping(target = "departCode", source = "docDepartmentCode")
    @Mapping(target = "type", constant = "PASSPORT_RU")
    Document toDocument(MtsbPerson mtsbPerson);

    @Mapping(target = "phoneType", constant = "MOBILE")
    @Mapping(target = "number", source = "phone")
    Phone toPhone(String phone);

    @Mapping(target = "addressType", constant = "HOME")
    @Mapping(target = "fullAddress", source = "homeRawSource")
    @Mapping(target = "postalCode", source = "homePostalcode")
    @Mapping(target = "country", source = "homeCountry")
    @Mapping(target = "region", source = "homeRegion")
    @Mapping(target = "regionType", source = "homeRegiontype")
    @Mapping(target = "rayon", source = "homeRayon")
    @Mapping(target = "rayonType", source = "homeRayontype")
    @Mapping(target = "city", source = "homeCity")
    @Mapping(target = "cityType", source = "homeCitytype")
    @Mapping(target = "settlement", source = "homeSettlement")
    @Mapping(target = "settlementType", source = "homeSettlementtype")
    @Mapping(target = "street", source = "homeStreet")
    @Mapping(target = "streetType", source = "homeStreettype")
    @Mapping(target = "house", source = "homeHousenumber")
    @Mapping(target = "stroenie", source = "homeStroenie")
    @Mapping(target = "korpus", source = "homeKorpus")
    @Mapping(target = "flat", source = "homeFlat")
    @Mapping(target = "fiasCode", source = "homeFiasCode")
    @Mapping(target = "fiasLevel", source = "homeFiasLevel")
    Address toAddressHome(MtsbPerson mtsbPerson);

    @Mapping(target = "addressType", constant = "REGISTRATION")
    @Mapping(target = "fullAddress", source = "registrationRawSource")
    @Mapping(target = "postalCode", source = "registrationPostalcode")
    @Mapping(target = "country", source = "registrationCountry")
    @Mapping(target = "region", source = "registrationRegion")
    @Mapping(target = "regionType", source = "registrationRegiontype")
    @Mapping(target = "rayon", source = "registrationRayon")
    @Mapping(target = "rayonType", source = "registrationRayontype")
    @Mapping(target = "city", source = "registrationCity")
    @Mapping(target = "cityType", source = "registrationCitytype")
    @Mapping(target = "settlement", source = "registrationSettlement")
    @Mapping(target = "settlementType", source = "registrationSettlementtype")
    @Mapping(target = "street", source = "registrationStreet")
    @Mapping(target = "streetType", source = "registrationStreettype")
    @Mapping(target = "house", source = "registrationHousenumber")
    @Mapping(target = "stroenie", source = "registrationStroenie")
    @Mapping(target = "korpus", source = "registrationKorpus")
    @Mapping(target = "flat", source = "registrationFlat")
    @Mapping(target = "fiasCode", source = "registrationFiasCode")
    @Mapping(target = "fiasLevel", source = "registrationFiasLevel")
    Address toAddressRegistration(MtsbPerson mtsbPerson);

    @Mapping(target = "addressType", constant = "CONSTANT_REGISTRATION")
    @Mapping(target = "fullAddress", source = "constantRegistrationRawSource")
    @Mapping(target = "postalCode", source = "constantRegistrationPostalcode")
    @Mapping(target = "country", source = "constantRegistrationCountry")
    @Mapping(target = "region", source = "constantRegistrationRegion")
    @Mapping(target = "regionType", source = "constantRegistrationRegiontype")
    @Mapping(target = "rayon", source = "constantRegistrationRayon")
    @Mapping(target = "rayonType", source = "constantRegistrationRayontype")
    @Mapping(target = "city", source = "constantRegistrationCity")
    @Mapping(target = "cityType", source = "constantRegistrationCitytype")
    @Mapping(target = "settlement", source = "constantRegistrationSettlement")
    @Mapping(target = "settlementType", source = "constantRegistrationSettlementtype")
    @Mapping(target = "street", source = "constantRegistrationStreet")
    @Mapping(target = "streetType", source = "constantRegistrationStreettype")
    @Mapping(target = "house", source = "constantRegistrationHousenumber")
    @Mapping(target = "stroenie", source = "constantRegistrationStroenie")
    @Mapping(target = "korpus", source = "constantRegistrationKorpus")
    @Mapping(target = "flat", source = "constantRegistrationFlat")
    @Mapping(target = "fiasCode", source = "constantRegistrationFiasCode")
    @Mapping(target = "fiasLevel", source = "constantRegistrationFiasLevel")
    Address toaddressConstantRegistration(MtsbPerson mtsbPerson);
}
