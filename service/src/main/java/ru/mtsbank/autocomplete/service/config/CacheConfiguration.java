package ru.mtsbank.autocomplete.service.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.MtsBankRedisAutoConfiguration;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.mtsbank.autocomplete.service.handlers.error.cache.CustomCacheErrorHandler;

/**
 * Класс конфигурации кэширования.
 * Выполняет импорт автоконфигурации MtsBankRedisAutoConfiguration, а также глобально включает кэширование посредством
 * аннотации EnableCaching.
 * Выполняет все вышеописанные действия в случае, если свойство spring.cache.type = redis.
 * Таким образом, кэш можно отключить, если не задавать настройки кэширования или если в свойство spring.cache.type
 * установить значение, отличное от redis (лучше всего подойдёт none).
 */
@EnableCaching
@Configuration
@Import(MtsBankRedisAutoConfiguration.class)
@ConditionalOnProperty(prefix = "spring.cache", name = "type", havingValue = "redis")
public class CacheConfiguration extends CachingConfigurerSupport {

    /**
     * Данный конфиг нужен для того, чтобы все ошибки, которые могут произойти при работе с Redis'ом, не выбрасывались
     * сразу в контроллер, в виде исключения, а обрабатывались корректно и после этого запускался код метода, над
     * которым стоит аннотация Cacheable.
     */
    @Override
    public CacheErrorHandler errorHandler() {
        return new CustomCacheErrorHandler();
    }

}
