package ru.mtsbank.autocomplete.service.utils;

import ch.qos.logback.classic.Level;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;
import ru.mtsbank.autocomplete.service.model.logging.params.RqLoggingParams;
import ru.mtsbank.autocomplete.service.model.logging.params.RsLoggingParams;
import ru.mtsbank.lib.logging.model.context.MtsBankLoggingContext;
import ru.mtsbank.lib.logging.model.context.MtsBankLoggingContextBuilder;
import ru.mtsbank.lib.logging.utils.MtsBankLoggingUtils;

import java.util.Map;
import java.util.stream.Collectors;

;

//Пример реализации утилитарного класса с логированием запросов и ответов посредством LoggingUtils
@Slf4j
@UtilityClass
public class RqRsLoggingUtils {

    public void logRequest(@NonNull RqLoggingParams rqLoggingParams) {
        try {
            final Map<String, String> headers = rqLoggingParams.getHeaders();
            final MtsBankLoggingContext loggingContext = MtsBankLoggingContextBuilder.instance()
                    .withMessage(String.format("Send request to [%s]", rqLoggingParams.getServiceName()))
                    .withLoggedFieldHttpMethod(rqLoggingParams.getMethod())
                    .withLoggingLevel(Level.INFO)
                    .withLoggedFieldHttpReqBody(rqLoggingParams.getBody())
                    .withConditionallyLoggedFieldHttpHeaders(
                            () -> headers.keySet().stream()
                                    .map(key -> key + "=" + headers.get(key))
                                    .collect(Collectors.joining(";")),
                            !CollectionUtils.isEmpty(headers)
                    )
                    .withLoggedFieldHttpUri(rqLoggingParams.getUrl())
                    .withUseBodyChunking(rqLoggingParams.isUseBodyChunking())
                    .withChunkLength(rqLoggingParams.getChunkLength())
                    .withChunksCount(rqLoggingParams.getChunksCount())
                    .build();
            MtsBankLoggingUtils.log(loggingContext);
        } catch (Exception e) {
            //Logging errors should not affect the processing of the request
            log.error("Request logging error. Cause: {}", ExceptionUtils.getStackTrace(e));
        }
    }

    public void logResponse(@NonNull RsLoggingParams rsLoggingParams) {
        try {
            final Map<String, String> headers = rsLoggingParams.getHeaders();
            final MtsBankLoggingContext loggingContext = MtsBankLoggingContextBuilder.instance()
                    .withMessage(String.format("Got response from [%s]", rsLoggingParams.getServiceName()))
                    .withLoggedFieldHttpRspBody(rsLoggingParams.getBody())
                    .withLoggingLevel(Level.INFO)
                    .withLoggedFieldHttpCode(rsLoggingParams.getHttpCode())
                    .withConditionallyLoggedFieldHttpHeaders(
                            () -> headers.keySet().stream()
                                    .map(key -> key + "=" + headers.get(key))
                                    .collect(Collectors.joining(";")),
                            !CollectionUtils.isEmpty(headers)
                    )
                    .withLoggedFieldHttpUri(rsLoggingParams.getUrl())
                    .withUseBodyChunking(rsLoggingParams.isUseBodyChunking())
                    .withChunkLength(rsLoggingParams.getChunkLength())
                    .withChunksCount(rsLoggingParams.getChunksCount())
                    .build();
            MtsBankLoggingUtils.log(loggingContext);
        } catch (Exception e) {
            //Logging errors should not affect the processing of the response
            log.error("Response logging error. Cause: {}", ExceptionUtils.getStackTrace(e));
        }
    }

}
