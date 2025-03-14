ARG CI_BUILD_DOCKER_IMAGE
ARG CI_BASE_DOCKER_IMAGE
ARG VERSION
ARG CI_APP_NAME
FROM $CI_BASE_DOCKER_IMAGE
ARG CERT_TEST="test.crt"
ARG CERT_PROD="prod.crt"


COPY $CERT_TEST /opt
COPY $CERT_PROD /opt
RUN  cd /opt && keytool -importcert -file $CERT_TEST -alias $CERT_TEST -cacerts -storepass changeit -noprompt && keytool -importcert -file $CERT_PROD -alias $CERT_PROD -cacerts -storepass changeit -noprompt

ADD ./service/target/*.jar /opt/app.jar
ENV HTTP_PROXY="https://prx-srv.mbrd.ru:3128"
ENV HTTPS_PROXY="https://prx-srv.mbrd.ru:3128"
RUN adduser --uid 888 --ingroup users -D ck8su

USER 888
EXPOSE 8080

WORKDIR /opt


CMD ["java", "-server", "-Xms192m", "-Xmx192m", "-jar", "/opt/app.jar"]
