package ru.mtsbank.autocomplete.service.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.mtsbank.autocomplete.customer.model.*;
import ru.mtsbank.autocomplete.service.constant.AutoCompleteConstants;
import ru.mtsbank.autocomplete.service.model.RequestCust;
import ru.mtsbank.integration.mts.xsd.fuzzy.*;

import java.util.Calendar;
import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CustFuzzySearchInqRqMapper {


    @Mapping(target = "serverInfo.msgUID", source = "requestCust.serverInfo.requestId")
    @Mapping(target = "serverInfo.rqUID", source = "requestCust.serverInfo.requestId")
    @Mapping(target = "serverInfo.SPName", source = "requestCust.serverInfo.messageSender")
    @Mapping(target = "serverInfo.msgReceiver", source = "requestCust.serverInfo.messageReceiver")
    @Mapping(target = "serverInfo.serverDt", source = "requestCust.serverInfo.serviceDate")
    @Mapping(target = "serverInfo.msgType", source = "requestCust.serverInfo.messageType")
    @Mapping(target = "bankSvcRq.custInfo.personInfo.fullName", source = "requestCust.customerFuzzySearchRequest.fullName")
    @Mapping(target = "bankSvcRq.custInfo.personInfo.personName.lastName", source = "requestCust.customerFuzzySearchRequest.lastName")
    @Mapping(target = "bankSvcRq.custInfo.personInfo.personName.firstName", source = "requestCust.customerFuzzySearchRequest.firstName")
    @Mapping(target = "bankSvcRq.custInfo.personInfo.personName.middleName", source = "requestCust.customerFuzzySearchRequest.middleName")
    @Mapping(target = "bankSvcRq.custInfo.personInfo.birthday", source = "requestCust.customerFuzzySearchRequest.birthDate")
    @Mapping(target = "bankSvcRq.custInfo.personInfo.contactInfo.phoneNums", expression = "java(toPhones(customerFuzzySearchRequest))")
    @Mapping(target = "bankSvcRq.custInfo.personInfo.contactInfo.postAddrs", expression = "java(toAddresses(customerFuzzySearchRequest))")
    @Mapping(target = "bankSvcRq.custInfo.personInfo.contactInfo.emailAddrs.emailAddr", expression = "java(toEmail(customerFuzzySearchRequest.getEmails()))")
    @Mapping(target = "bankSvcRq.custInfo.personInfo.identityCards", expression = "java(toIdentityCards(customerFuzzySearchRequest))")
    @Mapping(target = "bankSvcRq.minMatchScope", source = "requestCust.customerFuzzySearchRequest.minMatchScope")
    @Mapping(target = "bankSvcRq.custInfo.extCustId", expression = "java(toExtIds(customerFuzzySearchRequest.getExtIds()))")
    CustFuzzySearchInqRq toFdx(RequestCust requestCust);

    @Mapping(target = "idType", source = "type")
    @Mapping(target = "idSeries", source = "series")
    @Mapping(target = "idNum", source = "number")
    IdentityCard toIdentityCard(Document document);

    List<IdentityCard> toIdentityCard(List<Document> document);

    default IdentityCards toIdentityCards(final CustomerFuzzySearchRequest customerFuzzySearchRequest) {
        IdentityCards result = new IdentityCards();
        List<IdentityCard> list = result.getIdentityCard();
        if (customerFuzzySearchRequest.getDocuments() != null) {
            list.addAll(toIdentityCard(customerFuzzySearchRequest.getDocuments()));
        }
        if (list.isEmpty()) {
            return null;
        }
        return result;
    }

    @Mapping(source = "fullAddress", target = "addr")
    @Mapping(source = "regionType", target = "regionCode")
    @Mapping(source = "rayon", target = "district")
    @Mapping(source = "rayonType", target = "districtCode")
    @Mapping(source = "cityType", target = "cityCode")
    @Mapping(source = "settlement", target = "stateProv")
    @Mapping(source = "settlementType", target = "stateProvCode")
    @Mapping(source = "streetType", target = "streetCode")
    @Mapping(source = "house", target = "houseNum")
    @Mapping(source = "stroenie", target = "houseExt")
    @Mapping(source = "korpus", target = "houseBlock")
    @Mapping(source = "flat", target = "unitNum")
    @Mapping(source = "fiasCode", target = "FIASCode")
    @Mapping(source = "fiasLevel", target = "FIASLevel")
    FIASAddrType toAddress(Address address);

    List<FIASAddrType> toAddress(List<Address> address);

    default PostAddrs toAddresses(final CustomerFuzzySearchRequest customerFuzzySearchRequest) {
        PostAddrs result = new PostAddrs();
        List<FIASAddrType> list = result.getPostAddr();
        if (customerFuzzySearchRequest.getAddresses() != null) {
            list.addAll(toAddress(customerFuzzySearchRequest.getAddresses()));
        }
        if (list.isEmpty()) {
            return null;
        }
        return result;
    }


    @Mapping(source = "email", target = "addr")
    EmailAddrType toEmail(Email requestEmail);

    default EmailAddrType toEmail(final List<Email> requestEmail) {
        if (requestEmail == null || requestEmail.isEmpty()) {
            return null;
        }
        EmailAddrType result = toEmail(requestEmail.get(0));
        if (result.getAddr() != null) {
            return result;
        }
        return null;
    }

    default String toExtIds(final List<ExtId> list) {
        if (list != null) {
            for (ExtId item : list) {
                if (AutoCompleteConstants.RBO.equals(item.getSystem())) {
                    return item.getId();
                }
            }
        }
        return null;
    }

    @Mapping(target = "phone", source = "number")
    PhoneNum toPhone(Phone phone);

    List<PhoneNum> toPhone(List<Phone> phone);

    default PhoneNums toPhones(final CustomerFuzzySearchRequest customerFuzzySearchRequest) {
        PhoneNums result = new PhoneNums();
        List<PhoneNum> list = result.getPhoneNum();
        if (customerFuzzySearchRequest.getPhones() != null) {
            list.addAll(toPhone(customerFuzzySearchRequest.getPhones()));
        }
        return result;
    }

    default Date map(final java.util.Date date) {

        if (date != null) {
            Date result = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            result.setYear(cal.get(Calendar.YEAR));
            result.setMonth(cal.get(Calendar.MONTH) + 1);
            result.setDay(cal.get(Calendar.DAY_OF_MONTH));
            return result;
        }
        return null;
    }

}
