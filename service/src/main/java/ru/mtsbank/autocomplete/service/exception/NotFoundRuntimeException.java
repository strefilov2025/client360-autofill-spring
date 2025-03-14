package ru.mtsbank.autocomplete.service.exception;

import org.springframework.http.HttpStatus;
import ru.mtsbank.lib.web.model.exception.MtsBankException;

public class NotFoundRuntimeException extends MtsBankException {

    private static final long serialVersionUID = -274442024703399855L;
    public NotFoundRuntimeException(final HttpStatus httpStatus, final String code, final String title) {
        super(httpStatus, code, title);
    }

    public NotFoundRuntimeException(final HttpStatus httpStatus, final String code, final String title, final String details) {
        super(httpStatus, code, title, details);
    }
}
