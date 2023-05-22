package br.com.conta.model.dto;

import java.time.LocalDate;

import br.com.conta.model.Conta;

public record DetalharContaDTO(String titular, Double saldo, LocalDate criacaoCOnta, LocalDate vencimentoConta) {
	public DetalharContaDTO(Conta con) {
		this(con.getTitular(), con.getSaldo(), con.getDataCriacao(), con.getVencimento());
	}
	

}
