package ru.mtsbank.autocomplete.service.service;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mtsbank.autocomplete.customer.model.Customer;
import ru.mtsbank.autocomplete.customer.model.CustomerFuzzySearchRequest;
import ru.mtsbank.autocomplete.customer.model.ExtId;
import ru.mtsbank.autocomplete.customer.model.Phone;
import ru.mtsbank.autocomplete.service.constant.AutoCompleteConstants;
import ru.mtsbank.autocomplete.service.exception.constant.CustomerIssueError;
import ru.mtsbank.autocomplete.service.mapping.CustSearchInqRqMapper;
import ru.mtsbank.autocomplete.service.mapping.CustomerFromCustSearchInqRsMapper;
import ru.mtsbank.autocomplete.service.model.RequestCust;
import ru.mtsbank.autocomplete.service.utils.Util;
import ru.mtsbank.integration.mts.xsd.search.CustInfoType2;
import ru.mtsbank.integration.mts.xsd.search.CustSearchListInqRs;
import ru.mtsbank.integration.mts.xsd.search.PhoneNum;
import ru.mtsbank.lib.web.model.exception.MtsBankException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CustSearchList {
    private static final String GET_BY_HID = "getByHID";
    private static final String CUST_SEARCH_INQ_RQ = "CustSearchListInqRq";
    private static final String SEARCH = "search";
    public static final int ONE = 1;

    @Autowired
    private CustomerFromCustSearchInqRsMapper customerFromCustSearchInqRsMapper;

    private static final String SUCCESS_STATUS = "0";
    @Autowired
    protected MQClient mQClient;
    @Autowired
    private CustSearchInqRqMapper custSearchInqRqMapper;


    private Customer sendSearch(final CustomerFuzzySearchRequest data, final String method, final String requestId) {

        Customer resultReturn = null;
        final CustSearchListInqRs response = mQClient.sendMQ(requestId, custSearchInqRqMapper.toFdx(createDataSetSearch(data, requestId, method)));
        if (response != null && response.getBankSvcRs() != null) {
            if (SUCCESS_STATUS.equals(response.getBankSvcRs().getStatus().getStatusCode())) {
                if (response.getBankSvcRs().getCustList() != null && !response.getBankSvcRs().getCustList().getCustRec().isEmpty()) {
                    final List<CustInfoType2> list = response.getBankSvcRs().getCustList().getCustRec();
                    List<CustInfoType2> result;
                    if (SEARCH.equals(method))
                        result = getCustInfoType2sPhone(list, getPhone(data));
                    else {
                        result = list;
                    }
                    if (result.size() == ONE) {
                        resultReturn = customerFromCustSearchInqRsMapper.toDto(result.get(0));
                    } else if (result.size() > ONE) {
                        result.clear();
                        throw new MtsBankException(CustomerIssueError.UNPROCESSABLE_ENTITY.getHttpStatus(),
                                CustomerIssueError.UNPROCESSABLE_ENTITY.getCode(),
                                CustomerIssueError.UNPROCESSABLE_ENTITY.getTitle(),
                                CustomerIssueError.UNPROCESSABLE_ENTITY.getDetails());
                    }
                }
            } else {

                log.error(response.getBankSvcRs().getStatus().getStatusDesc());

                throw new MtsBankException(CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getHttpStatus(),
                        response.getBankSvcRs().getStatus().getStatusCode(),
                        CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getTitle(),
                        response.getBankSvcRs().getStatus().getStatusDesc());

            }
        }
        return resultReturn;
    }

    private String getPhone(final CustomerFuzzySearchRequest data) {
        if (!data.getPhones().isEmpty()) {
            return data.getPhones().get(0).getNumber();
        }
        return null;
    }


    private RequestCust createDataSetSearch(final CustomerFuzzySearchRequest data, final String requestId, final String method) {
        return RequestCust.builder()
                .method(method)
                .serverInfo(RequestCust.ServerInfo.builder()
                        .messageType(CUST_SEARCH_INQ_RQ)
                        .serviceDate(Util.getXmlGregorianCalendar())
                        .requestId(requestId).build())
                .customerFuzzySearchRequest(data).build();
    }


    public Customer sendSearch(final String phone, final String requestId) {
        return sendSearch(createDataSetPhone(phone), SEARCH, requestId);
    }


    public Customer sendSearchRBO(final String hid, final String requestId) {
        return sendSearch(createDataSetID(hid), GET_BY_HID, requestId);
    }

    @NotNull
    private CustomerFuzzySearchRequest createDataSetPhone(final String phone) {
        final CustomerFuzzySearchRequest data = new CustomerFuzzySearchRequest();
        final Phone phones = new Phone();
        phones.setNumber(replace(phone));
        final List<Phone> items = new ArrayList<>();
        items.add(phones);
        data.setPhones(items);
        return data;
    }

    @NotNull
    private String replace(final String phone) {
        return phone.replace("+", "").replace(" ", "").replace("(", "").replace(")", "");
    }


    @NotNull
    private CustomerFuzzySearchRequest createDataSetID(final String hid) {
        final CustomerFuzzySearchRequest data = new CustomerFuzzySearchRequest();
        final ExtId item = new ExtId();
        item.setSystem(AutoCompleteConstants.RBO);
        item.setId(hid);
        final List<ExtId> items = new ArrayList<>();
        items.add(item);
        data.setExtIds(items);
        return data;
    }


    @NotNull
    private List<CustInfoType2> getCustInfoType2sPhone(final List<CustInfoType2> listSource, final String phone) {
        if (listSource.size() == ONE) {
            return getCustInfoType2sPhone(listSource, getMobile(phone));
        } else {
            return getCustInfoType2sPhone(listSource, getPhoneNumPredicate(phone));
        }

    }

    @NotNull
    private List<CustInfoType2> getCustInfoType2sPhone(final List<CustInfoType2> listSource, final Predicate<PhoneNum> predicate) {
        return listSource.stream().filter(
                        item -> item.getPersonInfo().getContactInfo() != null &&
                                item.getPersonInfo().getContactInfo().getPhoneNums() != null &&
                                item.getPersonInfo().getContactInfo().getPhoneNums().getPhoneNum().stream().filter(
                                        predicate).count() > 0)
                .collect(Collectors.toList());
    }

    @NotNull
    private Predicate<PhoneNum> getPhoneNumPredicate(final String phone) {
        return itemp -> itemp.isPrimary() && itemp.getPhone().contains(phone);
    }

    @NotNull
    private Predicate<PhoneNum> getMobile(final String phone) {
        return itemp -> itemp.isPrimary() && itemp.getPhone().contains(phone) ||
                AutoCompleteConstants.MOBILE.equals(itemp.getPhoneType()) && itemp.getPhone().contains(phone);
    }
}
