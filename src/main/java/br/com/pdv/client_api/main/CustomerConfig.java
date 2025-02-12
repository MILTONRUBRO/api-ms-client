package br.com.pdv.client_api.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.pdv.client_api.application.gateways.CustomerGateway;
import br.com.pdv.client_api.application.gateways.mapper.CustomerEntityMapper;
import br.com.pdv.client_api.application.usecase.CreateCustomerInteractor;
import br.com.pdv.client_api.application.usecase.GetCustomerInteractor;
import br.com.pdv.client_api.infrastructure.gateways.repository.CustomerRepositoryGateway;
import br.com.pdv.client_api.infrastructure.persistence.repository.CustomerRepository;

@Configuration
public class CustomerConfig {
	
	@Bean
	public GetCustomerInteractor getCustomerInteractor (CustomerGateway customerGateway) {
		return new GetCustomerInteractor(customerGateway);
	}
	
	@Bean
	public CreateCustomerInteractor createCustomerInteractor(CustomerGateway customerGateway) {
		return new CreateCustomerInteractor(customerGateway);
	}
	
    @Bean
    CustomerGateway customerGateway(CustomerRepository customerRepository, CustomerEntityMapper customerEntityMapper ) {
        return new CustomerRepositoryGateway(customerRepository, customerEntityMapper);
    }
	
	@Bean
	CustomerEntityMapper customerEntityMapper() {
		return new CustomerEntityMapper();
	}

}
