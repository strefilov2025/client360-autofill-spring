package ru.mtsbank.autocomplete.service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.mtsbank.autocomplete.service.exception.NotFoundRuntimeException;
import ru.mtsbank.autocomplete.service.exception.constant.CustomerIssueError;
import ru.mtsbank.lib.web.dto.error.MtsBankErrorResponse;
import ru.mtsbank.lib.web.model.context.MtsBankHttpRequestContext;
import ru.mtsbank.lib.web.utils.MtsBankHeaders;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class CustomerControllerAdvice {
    private final MtsBankHttpRequestContext requestContext;

    @ExceptionHandler(NotFoundRuntimeException.class)
    public ResponseEntity<Void> handleNotFoundRuntimeException(final NotFoundRuntimeException exception) {
        HttpHeaders repHeaders = new HttpHeaders();
        repHeaders.add(MtsBankHeaders.X_REQUEST_ID, requestContext.getRequestId());
        return ResponseEntity.status(NO_CONTENT).headers(repHeaders).build();
    }

    @ExceptionHandler({RestClientException.class, HttpMessageNotReadableException.class,
            MissingRequestHeaderException.class, MethodArgumentTypeMismatchException.class, MissingServletRequestParameterException.class})
    public HttpEntity<MtsBankErrorResponse> handleClientExceptions(final Exception e) {

        log.error(ExceptionUtils.getStackTrace(e));

        MtsBankErrorResponse error = MtsBankErrorResponse.builder()
                .code(CustomerIssueError.BAD_REQUEST.getCode())
                .title(CustomerIssueError.BAD_REQUEST.getTitle())
                .details(e.getMessage())
                .uuid(requestContext.getRequestId()).build();
        HttpHeaders repHeaders = new HttpHeaders();
        repHeaders.add(MtsBankHeaders.X_REQUEST_ID, requestContext.getRequestId());
        return ResponseEntity.status(CustomerIssueError.BAD_REQUEST.getHttpStatus()).headers(repHeaders).body(error);
    }
}
