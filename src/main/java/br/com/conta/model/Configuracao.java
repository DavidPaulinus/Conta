package br.com.conta.model;

import java.util.List;

import br.com.conta.model.dto.ConfigDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_configuracoes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Configuracao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean ativa;
	private Long mesesParaVencer;
	private List<Double> limites;

	public Configuracao(ConfigDTO dto) {
		this.ativa = true;
		this.mesesParaVencer = dto.meseParaVencer();
		this.limites = dto.limite();
	}

	public void atualizar(ConfigDTO dto) {
		this.ativa = dto.ativa();
		this.mesesParaVencer = dto.meseParaVencer();
		this.limites = dto.limite();
	}

	public void desativar() {
		this.ativa = false;
	}

	public Double getLimite(TipoConta tipo) {
		switch(tipo) {
		case BASICA:
			return limites.get(0);
		case MEDIANA:
			return limites.get(1);
		case PREMIUM:
			return limites.get(2);
		}
		
		throw new RuntimeException("Erro no tipo para por limite");
	}
}
