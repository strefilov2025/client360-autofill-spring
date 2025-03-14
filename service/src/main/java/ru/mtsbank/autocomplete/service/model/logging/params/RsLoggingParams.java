package ru.mtsbank.autocomplete.service.model.logging.params;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class RsLoggingParams extends RqRsLoggingParams {

    private final int httpCode;

}
