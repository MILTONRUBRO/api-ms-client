package br.com.pdv.client_api.infrastructure.gateways.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import br.com.pdv.client_api.application.exception.NotFoundException;
import br.com.pdv.client_api.application.gateways.mapper.CustomerEntityMapper;
import br.com.pdv.client_api.domain.entity.Customer;
import br.com.pdv.client_api.infrastructure.persistence.entity.CustomerEntity;
import br.com.pdv.client_api.infrastructure.persistence.repository.CustomerRepository;

class CustomerRepositoryGatewayTest {

	@InjectMocks
	private CustomerRepositoryGateway customerRepositoryGateway;

	@Mock
	private CustomerRepository customerRepository;

	@Mock
	private CustomerEntityMapper customerEntityMapper;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindByDocument_CustomerExists() {
		String document = "123456789";
		CustomerEntity customerEntity = new CustomerEntity();
		Customer customer = new Customer(null, "123456789", "Ragnar Lodbrok", "ragnarlodbrok@example.com");

		when(customerRepository.findByDocument(document)).thenReturn(Optional.of(customerEntity));
		when(customerEntityMapper.entityToCustomer(customerEntity)).thenReturn(customer);

		Customer result = customerRepositoryGateway.findByDocument(document);

		assertNotNull(result);
		assertEquals(customer, result);
	}

	@Test
	void testFindByDocument_CustomerNotFound() {
		String document = "123456789";

		when(customerRepository.findByDocument(document)).thenReturn(Optional.empty());

		assertThrows(NotFoundException.class, () -> {
			customerRepositoryGateway.findByDocument(document);
		});
	}

	@Test
	void testCreateCustomer() {
		Customer customer = new Customer(null, "123456789", "Ragnar Lodbrok", "ragnarlodbrok@example.com");
		CustomerEntity customerEntity = new CustomerEntity();
		CustomerEntity savedCustomerEntity = new CustomerEntity();

		when(customerEntityMapper.customerToEntity(customer)).thenReturn(customerEntity);
		when(customerRepository.saveAndFlush(customerEntity)).thenReturn(savedCustomerEntity);
		when(customerEntityMapper.entityToCustomer(savedCustomerEntity)).thenReturn(customer);

		Customer result = customerRepositoryGateway.createCustomer(customer);

		assertNotNull(result);
		assertEquals(customer, result);
	}

	@Test
	void testNextCustomerId_NoCustomers() {
		when(customerRepository.findMaxId()).thenReturn(null);

		Long result = customerRepositoryGateway.nextCustomerId();

		assertNotNull(result);
		assertEquals(1L, result);
	}

	@Test
	void testNextCustomerId_CustomersExist() {
		when(customerRepository.findMaxId()).thenReturn(10L);

		Long result = customerRepositoryGateway.nextCustomerId();

		assertNotNull(result);
		assertEquals(11L, result);
	}

}
