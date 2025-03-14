package ru.mtsbank.autocomplete.service.mapping;

import customer_proto.CustomerFull;
import customer_proto.CustomerShort;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mtsbank.autocomplete.customer.model.*;
import ru.mtsbank.autocomplete.service.component.NriComponent;
import ru.mtsbank.autocomplete.service.constant.AutoCompleteConstants;
import ru.mtsbank.autocomplete.service.exception.constant.CustomerIssueError;
import ru.mtsbank.lib.web.model.exception.MtsBankException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public abstract class CustomerMapper {
    @Autowired
    protected NriComponent nriComponent;
    private static final String YYYY_MM_DD = "yyyy-MM-dd";


    @Mapping(source = "patronymicName", target = "middleName")
    @Mapping(target = "lastName", source = "surName")
    @Mapping(target = "taxResident", expression = "java(taxResident(customerShort.getTaxResident()))")
    @Mapping(target = "phones", expression = "java(phone(customerShort.getMainMobilePhone()))")
    @Mapping(target = "extIds", expression = "java(extIds(customerShort))")
    @Mapping(target = "documents", expression = "java(toDocuments(customerShort))")
    @Mapping(target = "emails", expression = "java(emails(customerShort))")
    @Mapping(target = "sourceSystem", constant = "CD")
    abstract Customer toDto(CustomerShort customerShort);

    public Customer toDto(final CustomerFull customerFull) {
        Customer result = toDto(customerFull.getMainInfo());
        result.addresses(toAddress(customerFull.getAddressesList()));
        result.setSourceSystem("CD");
        return result;
    }

    @Mapping(target = "series", source = "docSeries")
    @Mapping(target = "number", source = "docNumber")
    @Mapping(target = "issueDate", source = "docIssueDate")
    @Mapping(target = "issuedBy", source = "docIssuedBy")
    @Mapping(target = "departCode", source = "docDepartCode")
    @Mapping(target = "type", expression = "java(toTypeDoc(customerShort))")
    abstract Document toDocument(CustomerShort customerShort);

    @Mapping(target = "addressType", expression = "java(toTypeDocAddress(address))")
    @Mapping(target = "fullAddress", source = "addrString")
    @Mapping(target = "postalCode", source = "postalCode.value")
    @Mapping(target = "country", source = "countryName.value")
    @Mapping(target = "countryCode", source = "countryCode.value")
    @Mapping(target = "region", source = "region.value")
    @Mapping(target = "regionType", source = "regionType.value")
    @Mapping(target = "rayon", source = "district.value")
    @Mapping(target = "rayonType", source = "districtType.value")
    @Mapping(target = "city", source = "city.value")
    @Mapping(target = "cityType", source = "cityType.value")
    @Mapping(target = "cityArea", source = "intracity.value")
    @Mapping(target = "cityAreaType", source = "intracityType.value")
    @Mapping(target = "settlement", source = "settlement.value")
    @Mapping(target = "settlementType", source = "settlementType.value")
    @Mapping(target = "street", source = "streetName.value")
    @Mapping(target = "streetType", source = "streetType.value")
    @Mapping(target = "house", source = "houseNum.value")
    @Mapping(target = "stroenie", source = "building.value")
    @Mapping(target = "korpus", source = "korpus.value")
    @Mapping(target = "flat", source = "flat.value")
    @Mapping(target = "fiasCode", source = "fiasCode.value")
    @Mapping(target = "fiasLevel", source = "fiasLevel.value")
    abstract Address toAddress(customer_proto.Address address);

    abstract List<Address> toAddress(List<customer_proto.Address> addresses);


    String toTypeDocAddress(final customer_proto.Address item) {
        Map<String, String> typeAddress = nriComponent.getTypeAddress();
        String typeAdr = typeAddress.get(item.getAddrType());
        if (typeAdr == null) {
            typeAdr = typeAddress.get(AutoCompleteConstants.EMPTY_KEY);
        }
        return typeAdr;
    }

    List<Document> toDocuments(final CustomerShort customerShort) {
        Document doc = toDocument(customerShort);
        List<Document> listDoc = new ArrayList<>();
        listDoc.add(doc);
        return listDoc;
    }

    String toTypeDoc(final CustomerShort customerShort) {
        Map<String, String> typeDoc = nriComponent.getTypeDoc();
        String type = typeDoc.get(customerShort.getDocType());
        if (type == null) {
            type = typeDoc.get(AutoCompleteConstants.EMPTY_KEY);
        }
        return type;
    }

    List<Phone> phone(final String value) {
        List<Phone> listPhones = new ArrayList<>(1);
        Phone phone = new Phone();
        phone.phoneType(AutoCompleteConstants.MOBILE)
                .primary(true)
                .number(value);
        listPhones.add(phone);
        return listPhones;
    }

    Date mapDate(final String value) {
        if (value != null && !value.isBlank()) {
            try {
                return new SimpleDateFormat(YYYY_MM_DD).parse(value);
            } catch (ParseException e) {
                throw new MtsBankException(CustomerIssueError.INTERNAL_SERVER_ERROR.getHttpStatus(),
                        CustomerIssueError.INTERNAL_SERVER_ERROR.getCode(),
                        CustomerIssueError.INTERNAL_SERVER_ERROR.getTitle(),
                        e.getMessage());
            }
        }
        return null;
    }

    Boolean taxResident(final long value) {
        return value == 1;
    }

    Customer.GenderEnum mapGenderEnum(final String value) {
        Map<String, String> gender = nriComponent.getGender();
        return Customer.GenderEnum.fromValue(value.equals("F") || value.equals("M") ? gender.get(value) : gender.get(AutoCompleteConstants.EMPTY_KEY));
    }

    List<ExtId> extIds(final CustomerShort customerShort) {
        List<ExtId> extIds = new ArrayList<>(2);
        if (customerShort.getSiebelid() != null && !customerShort.getSiebelid().isEmpty()) {
            ExtId extId = new ExtId();
            extId.system(AutoCompleteConstants.SIEBEL);
            extId.id(customerShort.getSiebelid());
            extIds.add(extId);
        }
        if (customerShort.getRboid() != null && !customerShort.getRboid().isEmpty()) {
            ExtId extId = new ExtId();
            extId.system(AutoCompleteConstants.RBO);
            extId.id(customerShort.getRboid());
            extIds.add(extId);
        }
        return extIds;
    }

    List<Email> emails(final CustomerShort customerShort) {
        List<Email> emails = new ArrayList<>(customerShort.getEmailCount());
        for (int i = 0; i < customerShort.getEmailCount(); i++) {
            String email = customerShort.getEmail(i);
            if (!email.isBlank()) {
                Email item = new Email();
                item.email(email);
                emails.add(item);
            }
        }
        if (emails.isEmpty()) {
            return null;
        }
        return emails;
    }
}
