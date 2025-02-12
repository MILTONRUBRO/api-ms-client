package br.com.pdv.client_api.application.gateways;

import java.util.Optional;

import br.com.pdv.client_api.domain.entity.Customer;

public interface CustomerGateway {
	
	Customer findByDocument(String document);
	Optional<Customer>findByDocumentForOrder(String document);
	Customer createCustomer(Customer customer);
	Long  nextCustomerId();

}