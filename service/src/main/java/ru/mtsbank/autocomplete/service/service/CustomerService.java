package ru.mtsbank.autocomplete.service.service;

import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mtsbank.autocomplete.customer.model.Customer;
import ru.mtsbank.autocomplete.customer.model.CustomerFuzzySearchRequest;
import ru.mtsbank.autocomplete.customer.model.Document;
import ru.mtsbank.autocomplete.customer.model.InlineResponse200;
import ru.mtsbank.autocomplete.service.exception.NotFoundRuntimeException;
import ru.mtsbank.autocomplete.service.exception.constant.CustomerIssueError;
import ru.mtsbank.lib.web.model.context.MtsBankHttpRequestContext;
import ru.mtsbank.lib.web.model.exception.MtsBankException;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class CustomerService {
    public static final String DETAILS = " Details=";
    public static final String TITLE = " Title=";
    public static final String CODE = " Code=";
    public static final String GR_HUB = "GR HUB";
    public static final String PASSPORT_RU = "PASSPORT_RU";
    @Autowired
    protected CustomerDomainGrpcClientSAO customerDomainClient;
    @Autowired
    protected CustFussySearching custFussySearching;
    @Autowired
    protected CustSearchList custSearchList;
    @Autowired
    protected MtsbClientSAO mtsbClientSAO;

    @Autowired
    private MtsBankHttpRequestContext requestContext;
    private final static String REGEX = "^\\+?[\\d\\s()]*$";
    private final Set<String> listTypeDocuments = Set.of(PASSPORT_RU, "PASSPORT_USSR", "PASSPORT_FOREIGNER");

    /**
     * @param phone request information
     * @return Customer
     */
    public Customer getCustomer(final String phone) {
        log.debug("getCustomer {}", phone);
        validation(phone);
        Customer resultMdm = null;
        Customer resultGrhab = null;
        Exception exceptionMdm = null;
        Exception exceptionGrhab = null;

        final String requestId = getRequestId();
        final CompletableFuture<Customer> mdm = CompletableFuture.supplyAsync(() -> custSearchList.sendSearch(phone, requestId));
        final CompletableFuture<Customer> grhab = CompletableFuture.supplyAsync(() -> mtsbClientSAO.getPerson(phone));
        try {
            resultMdm = mdm.get();
        } catch (InterruptedException | ExecutionException e) {

            log.error(ExceptionUtils.getStackTrace(e));

            exceptionMdm = e;
        }

        try {
            resultGrhab = grhab.get();
        } catch (InterruptedException | ExecutionException e) {

            log.error(ExceptionUtils.getStackTrace(e));

            exceptionGrhab = e;
        }

        return getCustomerFromCD(phone, rulesForReturn(resultMdm, resultGrhab, exceptionMdm, exceptionGrhab));
    }

    private Customer getCustomerFromCD(final String phone, final Customer result) {
        if (!GR_HUB.equals(result.getSourceSystem())) {
            try {
                final Customer cd = customerDomainClient.getCustomerFull(phone);
                if (cd != null
                        && StringUtils.equalsIgnoreCase(result.getFirstName(), cd.getFirstName())
                        && StringUtils.equalsIgnoreCase(result.getMiddleName(), cd.getMiddleName())
                        && result.getBirthDate() != null && cd.getBirthDate() != null && convertToCalendar(result.getBirthDate()).equals(convertToCalendar(cd.getBirthDate()))
                ) {
                    result.setIsFullyIdent(cd.getIsFullyIdent());
                    result.setCategory(cd.getCategory());
                } else {
                    if (cd != null && result != null) {
                        final MessageFormat format = new MessageFormat("compare MDM & CD  FirstName M [{0}] vs C [{1}] , MiddleName M [{2}] vs C [{3}] ,BirthDate M [{4}] vs C [{5}]");
                        final String errorMessage = format.format(new String[]{result.getFirstName(), cd.getFirstName(),
                                result.getMiddleName(), cd.getMiddleName(),
                                getDateString(result.getBirthDate()), getDateString(cd.getBirthDate())});
                        log.error(errorMessage);
                        throw new MtsBankException(CustomerIssueError.UNPROCESSABLE_ENTITY.getHttpStatus(),
                                CustomerIssueError.UNPROCESSABLE_ENTITY.getCode(),
                                CustomerIssueError.UNPROCESSABLE_ENTITY.getTitle(),
                                errorMessage
                        );
                    }
                }
            } catch (StatusRuntimeException exception) {
                log.error(exception.getStatus().getDescription());
            }
        }
        return result;
    }

    private Customer rulesForReturn(final Customer resultMdm, final Customer resultGrhab, final Exception exceptionMdm, final Exception exceptionGrhab) {
        Customer result = null;
        if (exceptionGrhab != null && exceptionMdm != null) {
            extractedException(exceptionMdm, exceptionGrhab);
        } else if (resultMdm != null && resultGrhab == null) {
            result = resultMdm;
        } else if (resultMdm == null && resultGrhab != null) {
            result = resultGrhab;
        } else if (resultMdm != null && resultGrhab != null) {
            if (validation(resultGrhab, resultMdm)) {
                result = compareToCustomers(resultMdm, resultGrhab);

            } else {
                throw new MtsBankException(CustomerIssueError.UNPROCESSABLE_ENTITY.getHttpStatus(),
                        CustomerIssueError.UNPROCESSABLE_ENTITY.getCode(),
                        CustomerIssueError.UNPROCESSABLE_ENTITY.getTitle(),
                        CustomerIssueError.UNPROCESSABLE_ENTITY.getDetails()
                );
            }
        }
        if (result == null) {
            throw new NotFoundRuntimeException(CustomerIssueError.NOT_FOUND.getHttpStatus(),
                    CustomerIssueError.NOT_FOUND.getCode(),
                    CustomerIssueError.NOT_FOUND.getTitle(),
                    CustomerIssueError.NOT_FOUND.getDetails()
            );
        }

        return result;
    }

    private void extractedException(final Exception exceptionMdm, final Exception exceptionGrhab) {
        final StringBuilder exepBuffer = new StringBuilder();
        exepBuffer.append("Grhab->").append(exceptionGrhab.getMessage());
        if (exceptionGrhab.getCause() instanceof MtsBankException) {
            final MtsBankException e = (MtsBankException) exceptionGrhab.getCause();
            exepBuffer.append(CODE).append(e.getCode());
            exepBuffer.append(TITLE).append(e.getTitle());
            exepBuffer.append(DETAILS).append(e.getDetails());
        } else {
            exepBuffer.append(DETAILS).append(exceptionGrhab.getCause().getMessage());
        }
        exepBuffer.append("\nmdm->").append(exceptionMdm.getMessage());
        if (exceptionMdm.getCause() instanceof MtsBankException) {
            final MtsBankException e = (MtsBankException) exceptionMdm.getCause();
            exepBuffer.append(CODE).append(e.getCode());
            exepBuffer.append(TITLE).append(e.getTitle());
            exepBuffer.append(DETAILS).append(e.getDetails());
        } else {
            exepBuffer.append(DETAILS).append(exceptionMdm.getCause().getMessage());
        }
        throw new MtsBankException(CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getHttpStatus(),
                CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getCode(),
                CustomerIssueError.BANK_SERVICE_ERROR_RESPONSE.getTitle(),
                exepBuffer.toString());
    }

    @NotNull
    private Customer compareToCustomers(final Customer resultMdm, final Customer resultGrhab) {
        Customer result;
        long count = 0;
        if (resultMdm.getDocuments() != null) {
            count = resultMdm.getDocuments().stream().filter(nn -> nn.getValid() && PASSPORT_RU.equals(nn.getType())).count();
        }
        if (count == 0 && resultGrhab.getInn() != null && !resultGrhab.getInn().isBlank()) {
            result = resultGrhab;
        } else {
            result = resultMdm;
        }
        return result;
    }

    private boolean validation(final Customer resultGrhab, final Customer resultMdm) {
        Calendar mdmBirthDate = null;
        if (resultMdm.getBirthDate() != null) {
            mdmBirthDate = convertToCalendar(resultMdm.getBirthDate());
        }
        Calendar grhabBirthDate = null;
        if (resultGrhab.getBirthDate() != null) {
            grhabBirthDate = convertToCalendar(resultGrhab.getBirthDate());
        }
        final boolean result = (StringUtils.equalsIgnoreCase(resultGrhab.getFirstName(), resultMdm.getFirstName()) &&
                StringUtils.equalsIgnoreCase(resultGrhab.getMiddleName(), resultMdm.getMiddleName()) &&
                grhabBirthDate != null && mdmBirthDate != null &&
                grhabBirthDate.equals(mdmBirthDate));
        if (!result) {
            log.error("validation  FirstName h {} vs m {} ,MiddleName h {} vs m {} ,BirthDate h {} vs m {}",
                    resultGrhab.getFirstName(), resultMdm.getFirstName(), resultGrhab.getMiddleName(),
                    resultMdm.getMiddleName(), getDateString(grhabBirthDate), getDateString(mdmBirthDate));
        }
        return result;
    }

    private String getDateString(final Calendar birthDate) {
        if (birthDate != null) {
            final Date date = birthDate.getTime();
            final DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            return dateFormat.format(date);

        }
        return "";
    }

    private String getDateString(final Date birthDate) {
        if (birthDate != null) {
            final Date date = birthDate;
            final DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            return dateFormat.format(date);

        }
        return "";
    }

    @NotNull
    private Calendar convertToCalendar(final Date value) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(value);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     * @param data request information
     * @return InlineResponse200
     */
    public InlineResponse200 getCustomer(final CustomerFuzzySearchRequest data) {
        log.debug("getCustomerFuzzy {}", data.toString());
        validation(data);

        final InlineResponse200 result = custFussySearching.sendFuzzySearch(data, getRequestId());

        if (result == null) {
            throw new NotFoundRuntimeException(CustomerIssueError.NOT_FOUND.getHttpStatus(),
                    CustomerIssueError.NOT_FOUND.getCode(),
                    CustomerIssueError.NOT_FOUND.getTitle());
        }

        return result;

    }

    private void validation(final String phone) {
        if (phone == null || phone.isBlank() || !checkPhone(phone)) {
            final MessageFormat format = new MessageFormat(CustomerIssueError.BAD_REQUEST.getDetails());
            final String errorMessage = format.format(new String[]{phone});
            throw new MtsBankException(CustomerIssueError.BAD_REQUEST.getHttpStatus(),
                    CustomerIssueError.BAD_REQUEST.getCode(),
                    CustomerIssueError.BAD_REQUEST.getTitle(),
                    errorMessage);
        }
    }

    protected void validation(final CustomerFuzzySearchRequest data) {
        final MessageFormat format = new MessageFormat(CustomerIssueError.BAD_REQUEST_FIO.getDetails());
        if (data != null && data.getDocuments() != null) {
            for (Document item : data.getDocuments()) {
                if (item != null && !StringUtils.isEmpty(item.getType()) && !listTypeDocuments.contains(item.getType())) {
                    final String errorMessage = format.format(new String[]{item.getType()});
                    throw new MtsBankException(CustomerIssueError.BAD_REQUEST.getHttpStatus(),
                            CustomerIssueError.BAD_REQUEST.getCode(),
                            CustomerIssueError.BAD_REQUEST.getTitle(),
                            errorMessage);
                }
            }
        }
    }

    @Nullable
    private String getRequestId() {
        return requestContext.getRequestId();
    }

    private boolean checkPhone(final String phone) {
        return phone != null && phone.matches(REGEX);
    }

}
