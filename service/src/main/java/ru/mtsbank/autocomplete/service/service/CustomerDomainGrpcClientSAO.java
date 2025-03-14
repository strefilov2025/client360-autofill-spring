package ru.mtsbank.autocomplete.service.service;

import customer_proto.CustomerServiceGrpc;
import customer_proto.CustomerServiceOuterClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.mtsbank.autocomplete.customer.model.Customer;
import ru.mtsbank.autocomplete.service.mapping.CustomerMapper;

@Component
@RequiredArgsConstructor
public class CustomerDomainGrpcClientSAO {
    private final CustomerServiceGrpc.CustomerServiceBlockingStub stub;
    private final CustomerMapper customerMapper;

    /**
     * @param mainPhoneNumber
     * @return Customer
     */


    public Customer getCustomerFull(final String mainPhoneNumber) {


        final CustomerServiceOuterClass.GetCustomerFullRsp resp = stub.getCustomerFull(
                CustomerServiceOuterClass.GetCustomerFullReq.newBuilder()
                        .setMainPhoneNumber(mainPhoneNumber)
                        .build());

        return customerMapper.toDto(resp.getCustomerFull());
    }
}
