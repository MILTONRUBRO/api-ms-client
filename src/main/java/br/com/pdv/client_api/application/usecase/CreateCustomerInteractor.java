package br.com.pdv.client_api.application.usecase;

import br.com.pdv.client_api.application.gateways.CustomerGateway;
import br.com.pdv.client_api.domain.entity.Customer;

public class CreateCustomerInteractor {
	
	private final CustomerGateway customerGateway;

	public CreateCustomerInteractor(CustomerGateway customerGateway) {
		this.customerGateway = customerGateway;
	}
	
	public Customer createCustomer(Customer customer) {
		return customerGateway.createCustomer(customer);
	}

}
