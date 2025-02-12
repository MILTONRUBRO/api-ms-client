package br.com.pdv.client_api.infrastructure.gateways.repository;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.pdv.client_api.application.exception.NotFoundException;
import br.com.pdv.client_api.application.gateways.CustomerGateway;
import br.com.pdv.client_api.application.gateways.mapper.CustomerEntityMapper;
import br.com.pdv.client_api.domain.entity.Customer;
import br.com.pdv.client_api.infrastructure.persistence.entity.CustomerEntity;
import br.com.pdv.client_api.infrastructure.persistence.repository.CustomerRepository;

@Service
public class CustomerRepositoryGateway implements CustomerGateway {
	
	private final CustomerRepository customerRepository;
	private final CustomerEntityMapper customerEntityMapper;
	
	public CustomerRepositoryGateway(CustomerRepository customerRepository,
			CustomerEntityMapper customerEntityMapper) {
		this.customerRepository = customerRepository;
		this.customerEntityMapper = customerEntityMapper;
	}
	
	@Override
	public Customer findByDocument(String document) {
		Optional<CustomerEntity> optionalCustomer = customerRepository.findByDocument(document);
		
        if (!optionalCustomer.isPresent()) {
            throw new NotFoundException("Cliente não encontrado");
        }
        
        return customerEntityMapper.entityToCustomer(optionalCustomer.get());
	}

	@Override
	public Optional<Customer> findByDocumentForOrder(String document) {
		Optional<CustomerEntity> customerEntityOptional = customerRepository.findByDocument(document);
		return customerEntityMapper.toDomainOptional(customerEntityOptional);
	}

	@Override
	public Customer createCustomer(Customer customer) {
		CustomerEntity customerEntity = customerEntityMapper.customerToEntity(customer);
		CustomerEntity customerSaved = customerRepository.saveAndFlush(customerEntity);
		return customerEntityMapper.entityToCustomer(customerSaved);
	}

	@Override
	public Long nextCustomerId() {
		Long maxId = customerRepository.findMaxId();
		return (maxId == null) ? 1 : maxId + 1;
	}

}
