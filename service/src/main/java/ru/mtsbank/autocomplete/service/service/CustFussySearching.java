package ru.mtsbank.autocomplete.service.service;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mtsbank.autocomplete.customer.model.Customer;
import ru.mtsbank.autocomplete.customer.model.CustomerFuzzySearchRequest;
import ru.mtsbank.autocomplete.customer.model.InlineResponse200;
import ru.mtsbank.autocomplete.service.constant.AutoCompleteConstants;
import ru.mtsbank.autocomplete.service.exception.constant.CustomerIssueError;
import ru.mtsbank.autocomplete.service.mapping.CustFuzzySearchInqRqMapper;
import ru.mtsbank.autocomplete.service.mapping.CustomerFromCustFuzzySearchInqRsMapper;
import ru.mtsbank.autocomplete.service.model.RequestCust;
import ru.mtsbank.autocomplete.service.utils.Util;
import ru.mtsbank.integration.mts.xsd.fuzzy.CustFuzzySearchInqRs;
import ru.mtsbank.integration.mts.xsd.fuzzy.CustInfoType2;
import ru.mtsbank.integration.mts.xsd.fuzzy.Status;
import ru.mtsbank.lib.web.model.exception.MtsBankException;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CustFussySearching {
    public static final int ONE = 1;
    @Autowired
    private CustFuzzySearchInqRqMapper custFuzzySearchInqRqMapper;
    @Autowired
    private CustomerFromCustFuzzySearchInqRsMapper customerFromCustFuzzySearchInqRsMapper;
    @Autowired
    protected CustSearchList custSearchList;
    @Autowired
    protected MQClient mqClient;


    private static final String SUCCESS_STATUS = "0";


    public InlineResponse200 sendFuzzySearch(final CustomerFuzzySearchRequest data, final String requestId) {
        InlineResponse200 result = null;
        final CustFuzzySearchInqRs response = mqClient.sendMQ(requestId, custFuzzySearchInqRqMapper.toFdx(createDataSet(data, requestId)));
        if (response != null && response.getBankSvcRs() != null) {
            final Status status = response.getBankSvcRs().getStatus();
            if (SUCCESS_STATUS.equals(status.getStatusCode())) {
                if (isHasList(response)) {
                    result = getInlineResponse200(data, response, requestId);
                }
            } else {
                log.error(status.getStatusDesc());
                throw new MtsBankException(CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getHttpStatus(),
                        status.getStatusCode(),
                        CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getTitle(),
                        status.getStatusDesc()
                );
            }
        }
        return result;
    }

    private InlineResponse200 getInlineResponse200(final CustomerFuzzySearchRequest data, final CustFuzzySearchInqRs response, final String requestId) {
        InlineResponse200 resultReturn = null;
        final List<CustInfoType2> result = getCustInfoType2sMaxMatchScope(response);
        if (result.size() == ONE) {
            resultReturn = createInlineResponse200(data, result, requestId);
        } else if (result.size() > ONE) {
            result.clear();
            throw new MtsBankException(CustomerIssueError.UNPROCESSABLE_ENTITY.getHttpStatus(),
                    CustomerIssueError.UNPROCESSABLE_ENTITY.getCode(),
                    CustomerIssueError.UNPROCESSABLE_ENTITY.getTitle(),
                    CustomerIssueError.UNPROCESSABLE_ENTITY.getDetails());
        }
        return resultReturn;
    }

    @NotNull
    private InlineResponse200 createInlineResponse200(final CustomerFuzzySearchRequest data, final List<CustInfoType2> result, final String requestId) {
        InlineResponse200 resultReturn;
        final CustInfoType2 rec = result.get(0);
        resultReturn = new InlineResponse200();
        resultReturn.customer(customerFromCustFuzzySearchInqRsMapper.toDto(rec));
        resultReturn.setSources(addSourceRBO(data, rec, requestId));
        return resultReturn;
    }

    private boolean isHasList(final CustFuzzySearchInqRs response) {
        return response.getBankSvcRs().getCustList() != null &&
                response.getBankSvcRs().getCustList().getCustRec() != null &&
                !response.getBankSvcRs().getCustList().getCustRec().isEmpty();
    }

    private List<Customer> addSourceRBO(final CustomerFuzzySearchRequest data, final CustInfoType2 rec, final String requestId) {
        List<Customer> sources = null;
        if (isHasSource(data, rec)) {
            final List<CustInfoType2.Sources.Source> resultSource = rec.getSources().getSource().stream()
                    .filter(n -> AutoCompleteConstants.RBO.equals(n.getSourceSystem()))
                    .collect(Collectors.toList());
            if (resultSource.size() == ONE) {
                sources = new ArrayList<>();
                final Customer item = custSearchList.sendSearchRBO(resultSource.get(0).getExtCustId(), requestId);
                if (item != null) {
                    sources.add(item);
                }
            } else if (resultSource.size() > ONE) {
                resultSource.clear();
                throw new MtsBankException(CustomerIssueError.UNPROCESSABLE_RBO.getHttpStatus(), CustomerIssueError.UNPROCESSABLE_RBO.getCode(),
                        CustomerIssueError.UNPROCESSABLE_RBO.getTitle(),
                        CustomerIssueError.UNPROCESSABLE_RBO.getDetails());
            }
        }
        return sources;
    }

    private boolean isHasSource(final CustomerFuzzySearchRequest data, final CustInfoType2 rec) {
        return data.getIncludeSourceData() != null &&
                data.getIncludeSourceData().contains(CustomerFuzzySearchRequest.IncludeSourceDataEnum.RBO) &&
                rec.getSources() != null &&
                rec.getSources().getSource() != null &&
                !rec.getSources().getSource().isEmpty();
    }

    @NotNull
    private List<CustInfoType2> getCustInfoType2sMaxMatchScope(final CustFuzzySearchInqRs response) {
        List<CustInfoType2> result;
        final List<CustInfoType2> list = response.getBankSvcRs().getCustList().getCustRec().stream().filter(item -> item.getMatchScope() != null && !item.getMatchScope().isBlank()).collect(Collectors.toList());
        if (list.isEmpty() && response.getBankSvcRs().getCustList().getCustRec().size() > 0) {
            result = response.getBankSvcRs().getCustList().getCustRec();
        } else {
            final OptionalInt max = list.stream().map(CustInfoType2::getMatchScope).mapToInt(Integer::valueOf).max();
            final String maxString = (max.isPresent() ? String.valueOf(max.getAsInt()) : "0");
            result = list.stream().filter(item -> item.getMatchScope().equals(maxString)).collect(Collectors.toList());
        }
        return result;
    }


    @NotNull
    private RequestCust createDataSet(final CustomerFuzzySearchRequest data, final String requestId) {
        return RequestCust.builder()
                .serverInfo(RequestCust.ServerInfo.builder()
                        .serviceDate(Util.getXmlGregorianCalendar())
                        .requestId(requestId).build())
                .customerFuzzySearchRequest(data).build();
    }
}
