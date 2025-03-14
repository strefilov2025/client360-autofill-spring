#!/bin/bash
set -e
declare -A map

# Конфигурация
appKey='client360'
appName='autocomplete'

# Полный путь к конфигу сервиса
root='ump/'$appKey'/'$appName

# Урл консула и токен
url=http://consul-ump-test.mbrd.ru:8081/v1/kv/${root}
token=$1

# parent:
map[application]='
server:
  tomcat:
    threads:
        max: 1000
logging:
  level:
    root: INFO
spring:
  cache:
    type: redis
    cache-names: user_client360:customer
    redis:
      time-to-live: 30000
  redis:
    ssl: true
    lettuce:
      cluster:
         dynamic-refresh-sources: false
    cluster:
      nodes: vm-redis-ump01t.mbrd.ru:6379,vm-redis-ump02t.mbrd.ru:6379,vm-redis-ump03t.mbrd.ru:6379
      max-redirects: 3
    timeout: 100
    jedis:
      pool:
        max-active: 8
        max-idle: 8
        min-idle: 2
mtsbank:
  custom:
    # если hashCode % 100/percent == 0 мы возращаем не найден это для статистики . если false то это отключано . контрольная группа в серверах МДМ
    skip: true
    percent: 5
  app:
    name: client360-autocomplete
    time-zone: UTC

    info:
      description: Приложение для автозаполнения данных для формы клиента
      owner: client360 team
  tracer:
    tracing:
      api-patterns: ${mtsbank.app.name}/api/v\d.\d/
      header-names: x-request-id
    log:
      request:
        headers:
          enabled: true
      response:
        body:
          chunking:
            enabled: false
  grpc:
    customer:
      enabled: true
      host: customer-grpc.customer-domain.ump-test.mbrd.ru
      port: 8091
      security-type: tls_insecure
      timeout: 10000
      connect-timeout: 10000
  mtsb:
    url: http://api-grhub-mts.common.svc.cluster.local:8080
mq:
  sender: client360-autocomplete
  info: http://mq-gateway-info.integration.ump-test.mbrd.ru:8090/v1/messages
  connect-timeout: 30000
  read-timeout: 30000
  intergation-timeout: 35000
  pool-max-per-route: 80
  pool-max-total: 80
  connection-ttl: 590000
springdoc:
  api-docs:
    path: /${mtsbank.app.name}/swagger-ui/swagger.json #Путь к API документации в формате JSON
  swagger-ui:
    path: /${mtsbank.app.name}/swagger-ui/ #Путь к Swagger UI (интерфейс)
management:
  endpoint:
    health:
      probes:
        enabled: true #Включены ли Health пробы
  health:
    livenessstate:
      enabled: true #Включена ли live проба
    readinessstate:
      enabled: true #Включена ли read проба
  endpoints:
    web:
      exposure:
        include: loggers, health, metrics, prometheus #Список отдаваемых эндпоинтов
      path-mapping:
        metrics: testmetrics #URL, по которому будут доступны метрики актуатора
        prometheus: metrics #URL, по которому будут доступны метрики прометея
      base-path: / #Базовый путь
  metrics:
    distribution:
      percentiles-histogram:
        http:
          server:
            requests: false #Отключение передачи метрик по гистограмме запросов к http-серверу
      percentiles:
        http:
          server:
            requests: 0.5,0.95,0.99 #Percentiles метрики
      slo:
        http:
          server:
            requests: 1ms,5ms,10ms,50ms,100ms,200ms,400ms,500ms,750ms,1s,2s,5s,10s,30s,60s #Временные метрики
    tags:
      application: ${mtsbank.app.name} #Тэг с имененем приложения  '

# Delete all keys
curl -H "X-Consul-Token: ${token}" --request DELETE ${url}/?recurse=true

# --- DO NOT EDIT BELOW ---
setProperty () {
  echo "### Setting ${root}/${1//./\/} as:"
  echo "$2"
  if [[ "$(curl -X PUT -H "X-Consul-Token: ${token}" -d "${2}" -s ${url}/${1//./\/})" == "true" ]]; then
    echo "### ${url}/${1} is set"
  else
    echo "### ERROR: Cannot set ${url}/${1}"
    exit 1
  fi
}
for i in "${!map[@]}"; do
  setProperty $i "${map[$i]}"
done
