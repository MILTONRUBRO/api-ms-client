package br.com.pdv.client_api.domain.entity;

public record Customer(Long id, 
		String name, 
		String email, 
		String document) {

}
