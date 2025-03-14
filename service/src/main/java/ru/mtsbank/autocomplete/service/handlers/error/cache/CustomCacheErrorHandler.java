package ru.mtsbank.autocomplete.service.handlers.error.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.lang.NonNull;

/**
 * Обработчик ошибок, возникающих при работе с аннотацией Cacheable.
 * Рекомендуется логировать эти ошибки уровнем debug, чтобы не засорять логи лишней информацией на постоянной основе.
 */
@Slf4j
public class CustomCacheErrorHandler implements CacheErrorHandler {

    @Override
    public void handleCacheGetError(@NonNull final RuntimeException exception, @NonNull final Cache cache, @NonNull final Object key) {

        log.debug(
                "Error getting data from cache with name = [{}] by key = [{}]. Cause: {}.",
                cache.getName(),
                key,
                ExceptionUtils.getStackTrace(exception)
        );

    }

    @Override
    public void handleCachePutError(@NonNull final RuntimeException exception, @NonNull final Cache cache, @NonNull final Object key,
                                    final Object value) {

        log.debug(
                "Error putting data into cache with name = [{}] by key = [{}]. Cause: {}.",
                cache.getName(),
                key,
                ExceptionUtils.getStackTrace(exception)
        );

    }

    @Override
    public void handleCacheEvictError(@NonNull final RuntimeException exception, @NonNull final Cache cache, @NonNull final Object key) {

        log.debug(
                "Error evicting cache with name = [{}] by key = [{}]. Cause: {}.",
                cache.getName(),
                key,
                ExceptionUtils.getStackTrace(exception)
        );

    }

    @Override
    public void handleCacheClearError(@NonNull final RuntimeException exception, @NonNull final Cache cache) {

        log.debug(
                "Error clearing cache with name = [{}]. Cause: {}.",
                cache.getName(),
                ExceptionUtils.getStackTrace(exception)
        );

    }

}
