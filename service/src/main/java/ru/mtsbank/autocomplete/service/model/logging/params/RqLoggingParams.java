package ru.mtsbank.autocomplete.service.model.logging.params;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class RqLoggingParams extends RqRsLoggingParams {

    @NonNull
    private final String method;

}
