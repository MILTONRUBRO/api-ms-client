package br.com.pdv.client_api.controller;


import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pdv.client_api.application.gateways.mapper.CustomerEntityMapper;
import br.com.pdv.client_api.application.usecase.CreateCustomerInteractor;
import br.com.pdv.client_api.application.usecase.GetCustomerInteractor;
import br.com.pdv.client_api.domain.entity.Customer;
import br.com.pdv.client_api.infrastructure.controllers.request.CustomerRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/customers")
@Log4j2
public class CustomerController {
	
    private final GetCustomerInteractor getCustomerInteractor;
    private final CreateCustomerInteractor createCustomerInteractor;
    private final CustomerEntityMapper customerMapper;

    public CustomerController(GetCustomerInteractor getCustomerInteractor,
                              CreateCustomerInteractor createCustomerInteractor, CustomerEntityMapper customerMapper) {
        this.getCustomerInteractor = getCustomerInteractor;
        this.createCustomerInteractor = createCustomerInteractor;
        this.customerMapper = customerMapper;
    }

    @Operation(summary = "Create a new customer",
            description = "Creates a new customer with the provided details",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Customer created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            })
    @PostMapping
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerRequest request) {
        log.info("POST Customer Request: {}", request);

        Customer customer = customerMapper.requestToCustomer(request);
        Customer savedCustomer = createCustomerInteractor.createCustomer(customer);

        URI location = URI.create("/customers/" + savedCustomer.id());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Find a customer by document number",
            description = "Retrieves a customer based on the provided document number.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Customer retrieved successfully",
                            content = @Content(schema = @Schema(implementation = Customer.class))),
                    @ApiResponse(responseCode = "404", description = "Customer not found")
            })
    @GetMapping("/{documentNumber}")
    public ResponseEntity<Customer> find(@PathVariable String documentNumber) {
        log.info("GET Customer Document: {}", documentNumber);
        Customer customer = getCustomerInteractor.findCustomerByDocument(documentNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }


}
