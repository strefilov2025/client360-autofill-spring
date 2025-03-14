package ru.mtsbank.autocomplete.service.model.logging.params;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/*
Пример настройки разбиения тела запроса/ответа на части с использованием значений по умолчанию. В данном случае
по умолчанию будут выводиться первые 3000 символов тела запроса/ответа.
 */
@Getter
@SuperBuilder
public abstract class RqRsLoggingBodyChunkingParams {

    private final boolean useBodyChunking = true;
    private final int chunkLength = 3000;
    private final int chunksCount = 1;

}
