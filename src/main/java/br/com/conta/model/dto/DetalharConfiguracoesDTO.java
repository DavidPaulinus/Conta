package br.com.conta.model.dto;

import java.util.List;

import br.com.conta.model.Configuracao;

public record DetalharConfiguracoesDTO(Long mesesParaVencer, List<Double> limites) {
	public DetalharConfiguracoesDTO(Configuracao con) {
		this(con.getMesesParaVencer(), con.getLimites());
	}

}
