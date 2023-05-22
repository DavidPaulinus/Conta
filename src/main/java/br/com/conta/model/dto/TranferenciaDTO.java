package br.com.conta.model.dto;

import jakarta.validation.constraints.NotNull;

public record TranferenciaDTO(
		@NotNull
		Long id, 
		@NotNull
		Double valor) {

}
