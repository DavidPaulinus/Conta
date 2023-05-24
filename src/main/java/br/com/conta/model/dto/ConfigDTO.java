package br.com.conta.model.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record ConfigDTO(
		@NotNull
		Long meseParaVencer, 
		@NotNull
		List<Double> limite,
		Boolean ativa) {

}
