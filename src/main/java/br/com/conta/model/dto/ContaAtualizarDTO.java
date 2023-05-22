package br.com.conta.model.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContaAtualizarDTO(
		@NotBlank
		String titular, 
		@NotNull
		Double saldo, 
		@JsonFormat(pattern = "dd/MM/yyyy")
		LocalDate vencimento) {

}
