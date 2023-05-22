package br.com.conta.model.dto;

import jakarta.validation.constraints.NotNull;

public record DepositoDTO(
		@NotNull
		Double valor) {

}
