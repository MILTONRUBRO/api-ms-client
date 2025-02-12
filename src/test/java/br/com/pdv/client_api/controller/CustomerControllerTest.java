package br.com.pdv.client_api.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import br.com.pdv.client_api.application.gateways.mapper.CustomerEntityMapper;
import br.com.pdv.client_api.application.usecase.CreateCustomerInteractor;
import br.com.pdv.client_api.application.usecase.GetCustomerInteractor;
import br.com.pdv.client_api.domain.entity.Customer;
import br.com.pdv.client_api.infrastructure.controllers.request.CustomerRequest;

class CustomerControllerTest {

	private GetCustomerInteractor getCustomerInteractor;
	private CreateCustomerInteractor createCustomerInteractor;
	private CustomerEntityMapper customerMapper;
	private CustomerController customerController;

	@BeforeEach
	void setUp() {
		getCustomerInteractor = mock(GetCustomerInteractor.class);
		createCustomerInteractor = mock(CreateCustomerInteractor.class);
		customerMapper = mock(CustomerEntityMapper.class);
		customerController = new CustomerController(getCustomerInteractor, createCustomerInteractor, customerMapper);
	}

	@Test
	void testSaveCustomer() {
		CustomerRequest request = new CustomerRequest("Ragnar Lodbrok", "ragnarlodbrok@example.com","123456789");
		Customer customer = new Customer(null, "123456789", "Ragnar Lodbrok", "ragnarlodbrok@example.com");
		Customer savedCustomer = new Customer(123L, "123456789", "Ragnar Lodbrok", "ragnarlodbrok@example.com");

		when(customerMapper.requestToCustomer(request)).thenReturn(customer);
		when(createCustomerInteractor.createCustomer(customer)).thenReturn(savedCustomer);
		
		ResponseEntity<Void> response = customerController.saveCustomer(request);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("/customers/123", response.getHeaders().getLocation().toString());
	}

	@Test
	void testFindCustomer() {
		String documentNumber = "123456789";
		Customer customer = new Customer(null, "123456789", "Ragnar Lodbrok", "ragnarlodbrok@example.com");

		when(getCustomerInteractor.findCustomerByDocument(documentNumber)).thenReturn(customer);

		ResponseEntity<Customer> response = customerController.find(documentNumber);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(customer, response.getBody());
	}


}
