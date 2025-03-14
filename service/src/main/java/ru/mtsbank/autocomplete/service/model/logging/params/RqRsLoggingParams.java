package ru.mtsbank.autocomplete.service.model.logging.params;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Getter
@SuperBuilder
public abstract class RqRsLoggingParams extends RqRsLoggingBodyChunkingParams {

    @NonNull
    private final String serviceName;
    private final String body;
    private final Map<String, String> headers;
    private final String url;

}
