package br.com.pdv.client_api.infrastructure.controllers.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record CustomerRequest(
		@Schema(description = "Name of the customer", example = "Joao pedro") String name,
		@Schema(description = "Email of the customer", example = "joao_pedro@gmail.com") String email,
		@Schema(description = "Document of the customer", example = "123") String document) {
}