package ru.mtsbank.autocomplete.service.config;

import customer_proto.CustomerServiceGrpc;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import ru.mtsbank.client.MqGatewayClient;
import ru.mtsbank.config.MqGatewayClientConfiguration;
import ru.mtsbank.lib.client.grpc.context.model.factory.stub.MtsBankGrpcStubFactory;
import ru.mtsbank.lib.client.grpc.context.properties.client.MtsBankGrpcClientProperties;

import java.util.List;

@Configuration
@EnableCaching
@RequiredArgsConstructor
@Import({MqGatewayClientConfiguration.class, MqGatewayClient.class})
public class ConfigurationApplication {
    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    private final MtsBankGrpcStubFactory grpcStubFactory;

    @Bean("grpcClientCustomerProperties")
    @ConfigurationProperties("mtsbank.grpc.customer")
    public MtsBankGrpcClientProperties grpcClientCustomerProperties() {
        return new MtsBankGrpcClientProperties();
    }

    @Bean
    public CustomerServiceGrpc.CustomerServiceBlockingStub grpcClientCustomerServiceBlockingStub(
            @Qualifier("grpcClientCustomerProperties") final MtsBankGrpcClientProperties clientProperties) {
        return grpcStubFactory.createStubNettyChannelBased(clientProperties, CustomerServiceGrpc::newBlockingStub);
    }

    @Bean("customOpenApi")
    @Primary
    public OpenAPI customOpenApi(final OpenAPI openAPI) {
        if (!"local".equals(activeProfile)) {
            return openAPI
                    .servers(List.of(
                            new Server().url("http://services-ump2-test.mbrd.ru:8090")
                                    .description("Test service"),
                            new Server().url("http://services-ump2-dev.mbrd.ru")
                                    .description("dev service")
                    ));
        }
        return openAPI;
    }


}
