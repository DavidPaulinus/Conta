package br.com.conta.model.dto.conta;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.conta.model.TipoConta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContaAtualizarDTO(
		@NotNull
		TipoConta tipo,
		@NotBlank
		String senha,
		@NotBlank
		String titular, 
		@NotNull
		Double saldo, 
		@NotNull
		Long numero,
		@JsonFormat(pattern = "dd/MM/yyyy")
		LocalDate vencimento) {

}
