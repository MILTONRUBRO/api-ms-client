package br.com.pdv.client_api.application.usecase;

import br.com.pdv.client_api.application.gateways.CustomerGateway;
import br.com.pdv.client_api.domain.entity.Customer;

public class GetCustomerInteractor {
	
	private final CustomerGateway customerGateway;

	public GetCustomerInteractor(CustomerGateway customerGateway) {
		this.customerGateway = customerGateway;
	}
	
	
	public Customer findCustomerByDocument(String document) {
		return customerGateway.findByDocument(document);
	}

}
