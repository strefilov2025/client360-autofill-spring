package ru.mtsbank.autocomplete.service.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.mtsbank.autocomplete.customer.model.*;
import ru.mtsbank.integration.mts.xsd.search.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CustomerFromCustSearchInqRsMapper {


    String MDM = "MDM";

    @Mapping(source = "personName.lastName", target = "lastName")
    @Mapping(source = "personName.firstName", target = "firstName")
    @Mapping(source = "personName.middleName", target = "middleName")
    @Mapping(source = "taxId", target = "inn")
    @Mapping(source = "insAcctId", target = "snils")
    @Mapping(target = "citizenship", source = "citizenship.country")
    @Mapping(source = "dependents", target = "dependantsCount")
    @Mapping(target = "birthDate", source = "birthday")
    @Mapping(target = "deathDate", source = "deathDt")
    @Mapping(target = "vip", source = "VIP")
    @Mapping(target = "taxResident", source = "resident")
    @Mapping(target = "monetaryResident", source = "curResident")
    @Mapping(target = "phones", expression = "java(toPhone(personInfo.getContactInfo()))")
    @Mapping(target = "documents", expression = "java(toDocuments(personInfo.getIdentityCards()))")
    @Mapping(target = "addresses", expression = "java(toAddress(personInfo.getContactInfo()))")
    @Mapping(target = "emails", expression = "java(toEmail(personInfo.getContactInfo()))")
    Customer toDto(PersonInfoType personInfo);

    @Mapping(target = "series", source = "idSeries")
    @Mapping(target = "number", source = "idNum")
    @Mapping(target = "issueDate", source = "issueDt")
    @Mapping(target = "departCode", source = "issuedByCode")
    @Mapping(target = "type", source = "idType")
    @Mapping(target = "expiryDate", source = "expDt")
    @Mapping(target = "valid", source = "valid")
    Document toDocument(IdentityCard identityCard);

    List<Document> toDocuments(List<IdentityCard> identityCard);

    default List<Document> toDocuments(final IdentityCards identityCards) {
        if (identityCards != null) {
            return toDocuments(identityCards.getIdentityCard());
        }
        return null;
    }

    @Mapping(target = "addressType", source = "addrType")
    @Mapping(target = "fullAddress", source = "addr")
    @Mapping(target = "regionType", source = "regionCode")
    @Mapping(target = "rayon", source = "district")
    @Mapping(target = "rayonType", source = "districtCode")
    @Mapping(target = "cityType", source = "cityCode")
    @Mapping(target = "settlement", source = "stateProv")
    @Mapping(target = "settlementType", source = "stateProvCode")
    @Mapping(target = "streetType", source = "streetCode")
    @Mapping(target = "house", source = "houseNum")
    @Mapping(target = "stroenie", source = "houseExt")
    @Mapping(target = "korpus", source = "houseBlock")
    @Mapping(target = "flat", source = "unitNum")
    @Mapping(target = "fiasCode", source = "FIASCode")
    @Mapping(target = "fiasLevel", source = "FIASLevel")
    Address toAddress(FIASAddrType address);

    List<Address> toAddress(List<FIASAddrType> addresses);

    default List<Address> toAddress(final PostAddrs addresses) {
        if (addresses != null) {
            return toAddress(addresses.getPostAddr());
        }
        return null;
    }

    default List<Address> toAddress(final ContactInfoType contactInfo) {
        if (contactInfo != null) {
            return toAddress(contactInfo.getPostAddrs());
        }
        return null;
    }

    @Mapping(target = "number", source = "phone")
    Phone toPhone(PhoneNum phoneNum);

    List<Phone> toPhone(List<PhoneNum> listPhoneNum);

    default List<Phone> toPhone(final PhoneNums phoneNums) {
        if (phoneNums != null) {
            return toPhone(phoneNums.getPhoneNum());
        }
        return null;
    }

    default List<Phone> toPhone(final ContactInfoType contactInfo) {
        if (contactInfo != null) {
            return toPhone(contactInfo.getPhoneNums());
        }
        return null;
    }

    @Mapping(target = "emailType", source = "type")
    @Mapping(target = "email", source = "addr")
    Email toEmail(EmailAddrType emailAddrType);

    default List<Email> toEmail(final ContactInfoType.EmailAddrs emailAdders) {
        if (emailAdders == null) {
            return null;
        }
        List<Email> emails = new ArrayList<>();
        emails.add(toEmail(emailAdders.getEmailAddr()));

        return emails;
    }

    default List<Email> toEmail(final ContactInfoType contactInfo) {
        if (contactInfo == null) {
            return null;
        }
        return toEmail(contactInfo.getEmailAddrs());
    }

    @Mapping(target = "system", source = "sourceSystem")
    @Mapping(target = "id", source = "custId")
    ExtId toExtId(CustInfoType2.Sources.Source source);

    default List<ExtId> extIds(final CustInfoType2 custInfoType2) {
        List<ExtId> extIds = new ArrayList<>(2);
        if (custInfoType2.getExtCustId() != null && !custInfoType2.getExtCustId().isEmpty()) {
            ExtId extId = new ExtId();
            extId.system(MDM);
            extId.id(custInfoType2.getExtCustId());
            extIds.add(extId);
        }
        for (CustInfoType2.Sources.Source item : custInfoType2.getSources().getSource()) {
            if (!item.getCustId().equals(custInfoType2.getCustId())) {
                extIds.add(toExtId(item));
            }
        }

        return extIds;
    }


    default Customer toDto(final CustInfoType2 custInfoType2) {
        Customer result = toDto(custInfoType2.getPersonInfo());
        result.setBranch(custInfoType2.getBankInfo().getBankId());
        result.setExtIds(extIds(custInfoType2));
        if (custInfoType2.getSourceSystem() == null || custInfoType2.getSourceSystem().isBlank()) {
            result.setSourceSystem(MDM);
        } else {
            result.setSourceSystem(custInfoType2.getSourceSystem());
        }
        List<EmploymentHistory> list = custInfoType2.getPersonInfo().getEmploymentHistoryList().getEmploymentHistory();
        if (!list.isEmpty()) {
            EmploymentHistory employmentHistory = list.get(0);
            if (employmentHistory != null) {
                if (employmentHistory.getOrgInfo() != null) {
                    result.setOrganization(employmentHistory.getOrgInfo().getName());
                }
                result.setJob(employmentHistory.getJobTitle());
            }
        }
        return result;
    }

    default java.util.Date map(final Date date) {
        if (date != null && date.getYear() > 0) {
            Calendar cal = Calendar.getInstance();
            cal.set(date.getYear(), date.getMonth() - 1, date.getDay(), 0, 0, 0);
            return cal.getTime();
        }
        return null;
    }

    default Boolean map(final String value) {
        if (value != null && !value.equals("UNKNOWN")) {
            return Boolean.valueOf(value);
        }
        return null;
    }
}
