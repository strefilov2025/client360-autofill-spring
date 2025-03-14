package ru.mtsbank.autocomplete.service.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.MtsBankRedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * Выключение автоконфигураций, загружаемых стартером mtsbank-spring-boot-starter-data-redis. Это нужно для того, чтобы
 * можно было отключать кэш на уровне приложения посредством настроек (не будут подниматься лишние бины).
 * Автоконфигурации подключатся по условию ConditionalOnProperty в классе CacheConfiguration.
 * {@link ru.example.configuration.CacheConfiguration}.
 */
@Configuration
@EnableAutoConfiguration(exclude = {MtsBankRedisAutoConfiguration.class, RedisRepositoriesAutoConfiguration.class})
public class DataRedisDisableConfiguration {
}
