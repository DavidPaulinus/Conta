package br.com.conta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.conta.model.Conta;
import br.com.conta.model.dto.DepositoDTO;

@Service
public class BancoService {
	@Autowired
	private ContaService serv;

	public Conta depositar(Long id, DepositoDTO valor) {
		var _conta = serv.detalharContaPorId(id);
		_conta.depositar(valor.valor());
		
		return _conta;
	}

	public Conta sacar(Long id, DepositoDTO valor) {
		if(valor.valor() > 100) throw new RuntimeException("Valor excedeu o limite!");
			
		var _conta = serv.detalharContaPorId(id);		
		_conta.sacar(valor);
		
		return _conta;
	}
	
	
}
