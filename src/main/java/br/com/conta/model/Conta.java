package br.com.conta.model;

import java.time.LocalDate;

import br.com.conta.model.dto.ContaAtualizarDTO;
import br.com.conta.model.dto.ContaDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_contas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titular;
	private Double saldo;
	private LocalDate dataCriacao;
	private LocalDate vencimento;
	
	public Conta(ContaDTO dto) {
		this.titular = dto.titular();
		this.saldo = dto.saldo();
		this.dataCriacao = LocalDate.now();
		this.vencimento = dataCriacao.plusMonths(85);
	}

	public void atualizar(ContaAtualizarDTO dto) {
		this.titular = dto.titular();
		this.saldo = dto.saldo();
		this.vencimento = dto.vencimento();
	}
	
}
