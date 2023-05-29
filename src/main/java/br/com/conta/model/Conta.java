package br.com.conta.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.conta.model.dto.conta.ContaAtualizarDTO;
import br.com.conta.model.dto.conta.ContaDTO;
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
public class Conta implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private TipoConta tipo;

	// dados
	private Long numero;
	private String senha;
	private String titular;

	// dinheiro
	private Double saldo;
	private Double limite;

	// datas
	private LocalDate dataCriacao;
	private LocalDate vencimento;

	public Conta(ContaDTO dto, Double limite, Long vencimento) {
		this.numero = new Random().nextLong(1000000000000000l, 9999999999999999l);
		this.tipo = TipoConta.BASICA;
		this.titular = dto.titular();
		this.senha = dto.senha();
		this.saldo = dto.saldo();
		this.limite = limite;
		this.dataCriacao = LocalDate.now();
		this.vencimento = dataCriacao.plusMonths(vencimento);
	}

	public void atualizar(ContaAtualizarDTO dto, Double limite) {
		this.tipo = dto.tipo();
		this.titular = dto.titular();
		this.saldo = dto.saldo();
		this.vencimento = dto.vencimento();
		this.senha = dto.senha();
		this.numero = dto.numero();
		this.limite = limite;
	}

	public void depositar(Double valor) {
		this.saldo += valor;
	}

	public void sacar(Double valor) {
		if (this.saldo < 1 || valor > this.limite || valor > this.saldo)
			throw new RuntimeException("Saldo insuficiente ou limite atingido.");
		else
			this.saldo -= valor;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_Admin"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return numero.toString();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
