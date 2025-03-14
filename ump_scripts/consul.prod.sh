#!/bin/bash
set -e
declare -A map

# Конфигурация
appKey='client360'
appName='autocomplete'

# Полный путь к конфигу сервиса
root='ump/'$appKey'/'$appName

# Урл консула и токен
url=http://consul.ump-prod.mbrd.ru:80/v1/kv/${root}
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
    cluster:
      nodes: vm-redis-master-ump01p.mbrd.ru:6379,vm-redis-master-ump02p.mbrd.ru:6379,vm-redis-master-ump03p.mbrd.ru:6379
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
    skip: false
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
      host: customer-grpc.customer-domain.ump-prod.mbrd.ru
      port: 8091
      security-type: tls_insecure
      timeout: 10000
      connect-timeout: 10000
  mtsb:
    url: http://api-grhub-mts.common.svc.cluster.local:8080
mq:
  sender: client360-autocomplete
  info: http://mq-gateway-info.integration.ump-prod.mbrd.ru:8090/v1/messages
  connect-timeout: 30000
  read-timeout: 30000
  intergation-timeout: 35000
  pool-max-per-route: 80
  pool-max-total: 80
  connection-ttl: 590000'

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
