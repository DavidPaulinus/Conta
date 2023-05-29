package br.com.conta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.conta.model.Conta;
import br.com.conta.model.dto.DepositoDTO;
import br.com.conta.model.dto.TranferenciaDTO;

@Service
public class BancoService {
	@Autowired
	private ContaService serv;

	public Conta depositar(Long numero, DepositoDTO valor) {
		var _conta = serv.detalharContaPorNumero(numero);
		_conta.depositar(valor.valor());

		return _conta;
	}

	public Conta sacar(Long numero, DepositoDTO valor) {
		var _valor = valor.valor();
		var _conta = serv.detalharContaPorNumero(numero);
		
		if (_valor > _conta.getLimite())
			throw new RuntimeException("Valor excedeu o limite!");

		_conta.sacar(_valor);

		return _conta;
	}

	public Conta transferir(Long numero, TranferenciaDTO dto) {
		var _contaOrigem = serv.detalharContaPorNumero(numero);
		var _contaDestino = serv.detalharContaPorNumero(dto.numero());

		_contaOrigem.sacar(dto.valor());
		_contaDestino.depositar(dto.valor());

		return _contaOrigem;
	}

}
