package ru.mtsbank.autocomplete.service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.mtsbank.autocomplete.customer.model.Customer;
import ru.mtsbank.autocomplete.customer.model.CustomerFuzzySearchRequest;
import ru.mtsbank.autocomplete.customer.model.InlineResponse200;
import ru.mtsbank.autocomplete.service.exception.NotFoundRuntimeException;
import ru.mtsbank.autocomplete.service.exception.constant.CustomerIssueError;
import ru.mtsbank.autocomplete.service.service.CustomerService;
import ru.mtsbank.autocomplete.service.utils.Util;

import javax.validation.Valid;


@RestController
@RequestMapping("${mtsbank.app.name}/api/v1.1")
@RequiredArgsConstructor
@Slf4j
public class CustomerControllerV1_1 {
    public static final String APPLICATION_JSON_CHARSET_UTF_8 = MediaType.APPLICATION_JSON_VALUE;

    private final CustomerService customerService;


    @Value("${mtsbank.custom.skip:false}")
    private boolean clientSkip;
    @Value("${mtsbank.custom.percent:5}")
    private int percent;


    @GetMapping("/customer")

    @Operation(summary = "customer",
            responses = {
                    @ApiResponse(responseCode = "200", content = {
                            @Content(
                                    mediaType = APPLICATION_JSON_CHARSET_UTF_8,
                                    schema = @Schema(implementation = Customer.class)
                            )
                    }),
                    @ApiResponse(responseCode = "204", description = "Not Found",
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = Void.class)
                                    )
                            }),
                    @ApiResponse(responseCode = "400", content = {
                            @Content(
                                    mediaType = APPLICATION_JSON_CHARSET_UTF_8,
                                    schema = @Schema(implementation = ru.mtsbank.lib.web.dto.error.MtsBankErrorResponse.class)
                            )
                    }),
                    @ApiResponse(responseCode = "422", content = {
                            @Content(
                                    mediaType = APPLICATION_JSON_CHARSET_UTF_8,
                                    schema = @Schema(implementation = ru.mtsbank.lib.web.dto.error.MtsBankErrorResponse.class)
                            )
                    }),
                    @ApiResponse(responseCode = "424", content = {
                            @Content(
                                    mediaType = APPLICATION_JSON_CHARSET_UTF_8,
                                    schema = @Schema(implementation = ru.mtsbank.lib.web.dto.error.MtsBankErrorResponse.class)
                            )
                    }
                    )
                    ,
                    @ApiResponse(responseCode = "500", content = {
                            @Content(
                                    mediaType = APPLICATION_JSON_CHARSET_UTF_8,
                                    schema = @Schema(implementation = ru.mtsbank.lib.web.dto.error.MtsBankErrorResponse.class)
                            )
                    }
                    )
            })
    @Cacheable(value = "user_client360:customer", key = "'client360-autocomplete|CustomerControllerV1_1|getCustomer|'+#phone", unless = "#result == null")
    public Customer getCustomer(@RequestParam(value = "phone") final String phone) throws JsonProcessingException {


        if (clientSkip && phone != null) {
            if (Util.hashCode(phone) % (100 / percent) == 0) {
                log.info("phone = {} . an algorithm is used for the control group to skip .", phone);
                throw new NotFoundRuntimeException(CustomerIssueError.NOT_FOUND.getHttpStatus(),
                        CustomerIssueError.NOT_FOUND.getCode(),
                        CustomerIssueError.NOT_FOUND.getTitle(),
                        CustomerIssueError.NOT_FOUND.getDetails());
            }
        }
        return customerService.getCustomer(phone);


    }

    @PostMapping("/customer/fuzzy-search")
    @Operation(summary = "customer/fuzzy-search",

            responses = {
                    @ApiResponse(responseCode = "200", content = {
                            @Content(
                                    mediaType = APPLICATION_JSON_CHARSET_UTF_8,
                                    schema = @Schema(implementation = InlineResponse200.class)
                            )
                    }),
                    @ApiResponse(responseCode = "204", description = "Not Found",
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = Void.class)
                                    )
                            }),
                    @ApiResponse(responseCode = "400", content = {
                            @Content(
                                    mediaType = APPLICATION_JSON_CHARSET_UTF_8,
                                    schema = @Schema(implementation = ru.mtsbank.lib.web.dto.error.MtsBankErrorResponse.class)
                            )
                    }),
                    @ApiResponse(responseCode = "422", content = {
                            @Content(
                                    mediaType = APPLICATION_JSON_CHARSET_UTF_8,
                                    schema = @Schema(implementation = ru.mtsbank.lib.web.dto.error.MtsBankErrorResponse.class)
                            )
                    }),
                    @ApiResponse(responseCode = "424", content = {
                            @Content(
                                    mediaType = APPLICATION_JSON_CHARSET_UTF_8,
                                    schema = @Schema(implementation = ru.mtsbank.lib.web.dto.error.MtsBankErrorResponse.class)
                            )
                    })
                    ,
                    @ApiResponse(responseCode = "500", content = {
                            @Content(
                                    mediaType = APPLICATION_JSON_CHARSET_UTF_8,
                                    schema = @Schema(implementation = ru.mtsbank.lib.web.dto.error.MtsBankErrorResponse.class)
                            )
                    }
                    )
            })
    @Cacheable(value = "user_client360:customer", key = "'client360-autocomplete|CustomerControllerV1_1|getCustomerFuzzySearch|'+#data", unless = "#result == null")
    public InlineResponse200 getCustomerFuzzySearch(@RequestBody @Valid final CustomerFuzzySearchRequest data) throws JsonProcessingException {


        return customerService.getCustomer(data);


    }


}
