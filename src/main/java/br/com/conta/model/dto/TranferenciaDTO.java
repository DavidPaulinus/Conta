package br.com.conta.model.dto;

import jakarta.validation.constraints.NotNull;

public record TranferenciaDTO(
		@NotNull
		Long numero, 
		@NotNull
		Double valor) {

}
