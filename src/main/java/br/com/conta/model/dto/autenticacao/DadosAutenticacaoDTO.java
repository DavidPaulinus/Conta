package br.com.conta.model.dto.autenticacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAutenticacaoDTO(
		@NotNull
		Long numero,
		@NotBlank
		String senha) {

}
