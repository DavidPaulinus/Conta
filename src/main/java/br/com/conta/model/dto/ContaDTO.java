package br.com.conta.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContaDTO(
		@NotBlank
		String titular,
		@NotNull
		Double saldo) {

}
