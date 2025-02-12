package br.com.pdv.client_api.application.gateways.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import br.com.pdv.client_api.domain.entity.Customer;
import br.com.pdv.client_api.infrastructure.controllers.request.CustomerRequest;
import br.com.pdv.client_api.infrastructure.persistence.entity.CustomerEntity;

class CustomerEntityMapperTest {

	private CustomerEntityMapper mapper;

	@BeforeEach
	void setUp() {
		mapper = new CustomerEntityMapper();
	}

	@Test
	void testEntityToCustomer() {
		CustomerEntity entity = new CustomerEntity();
		entity.setId(1L);
		entity.setName("John Doe");
		entity.setEmail("john.doe@example.com");
		entity.setDocument("123456789");

		Customer customer = mapper.entityToCustomer(entity);

		assertNotNull(customer);
		assertEquals(1L, customer.id());
		assertEquals("John Doe", customer.name());
		assertEquals("john.doe@example.com", customer.email());
		assertEquals("123456789", customer.document());
	}

	@Test
	void testCustomerToEntity() {
		Customer customer = new Customer(1L, "John Doe", "john.doe@example.com", "123456789");

		CustomerEntity entity = mapper.customerToEntity(customer);

		assertNotNull(entity);
		assertEquals(1L, entity.getId());
		assertEquals("John Doe", entity.getName());
		assertEquals("john.doe@example.com", entity.getEmail());
		assertEquals("123456789", entity.getDocument());
	}

	@Test
	void testRequestToCustomer() {
		CustomerRequest request = new CustomerRequest("John Doe", "john.doe@example.com", "123456789");

		Customer customer = mapper.requestToCustomer(request);

		assertNotNull(customer);
		assertNull(customer.id());
		assertEquals("John Doe", customer.name());
		assertEquals("john.doe@example.com", customer.email());
		assertEquals("123456789", customer.document());
	}

	@Test
	void testToDomainOptional() {
		CustomerEntity entity = new CustomerEntity();
		entity.setId(1L);
		entity.setName("John Doe");
		entity.setEmail("john.doe@example.com");
		entity.setDocument("123456789");

		Optional<CustomerEntity> optionalEntity = Optional.of(entity);

		Optional<Customer> optionalCustomer = mapper.toDomainOptional(optionalEntity);

		assertTrue(optionalCustomer.isPresent());
		Customer customer = optionalCustomer.get();
		assertEquals(1L, customer.id());
		assertEquals("John Doe", customer.name());
		assertEquals("john.doe@example.com", customer.email());
		assertEquals("123456789", customer.document());
	}

	@Test
	void testToDomainOptionalEmpty() {
		Optional<CustomerEntity> optionalEntity = Optional.empty();

		Optional<Customer> optionalCustomer = mapper.toDomainOptional(optionalEntity);

		assertFalse(optionalCustomer.isPresent());
	}

}
