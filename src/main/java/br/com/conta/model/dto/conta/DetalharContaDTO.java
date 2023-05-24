package br.com.conta.model.dto.conta;

import java.time.LocalDate;

import br.com.conta.model.Conta;

public record DetalharContaDTO(Long numero,String titular, Double saldo, LocalDate criacaoConta, LocalDate vencimentoConta) {
	public DetalharContaDTO(Conta con) {
		this(con.getNumero(),con.getTitular(), con.getSaldo(), con.getDataCriacao(), con.getVencimento());
	}
	

}
