package br.com.pdv.client_api.application.gateways.mapper;

import java.util.Optional;

import br.com.pdv.client_api.domain.entity.Customer;
import br.com.pdv.client_api.infrastructure.controllers.request.CustomerRequest;
import br.com.pdv.client_api.infrastructure.persistence.entity.CustomerEntity;

public class CustomerEntityMapper {
	
	
    public Customer entityToCustomer(CustomerEntity customerEntity) {

        return new Customer(customerEntity.getId(), customerEntity.getName(), 
        		customerEntity.getEmail(), customerEntity.getDocument());
    }
    
    public CustomerEntity customerToEntity(Customer customer) {
    	var customerEntity = new CustomerEntity();
		customerEntity.setId(customer.id());
    	customerEntity.setDocument(customer.document());
    	customerEntity.setEmail(customer.email());
    	customerEntity.setName(customer.name());
    	
    	return customerEntity;
    }

	public Customer requestToCustomer(CustomerRequest request) {
		return new Customer(null, request.name(), request.email(), request.document());
	}

	public Optional<Customer> toDomainOptional(Optional<CustomerEntity> entity) {
		return entity.map(this::entityToCustomer);
	}

}
