package br.com.conta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.conta.model.Conta;
import br.com.conta.model.TipoConta;
import br.com.conta.model.dto.conta.ContaAtualizarDTO;
import br.com.conta.model.dto.conta.ContaDTO;
import br.com.conta.service.util.repository.ContaRepository;

@Service
public class ContaService {
	@Autowired
	private ContaRepository repo;
	@Autowired
	private AdminService serv;

	public Conta criarNovaConta(ContaDTO dto) {
		var _config = serv.detalharConfigPorAtiva();

		var _conta = new Conta(dto, _config.getLimite(TipoConta.BASICA), _config.getMesesParaVencer());
		repo.save(_conta);

		return _conta;
	}

	public Conta detalharContaPorNumero(Long numero) {
		return repo.findByNumero(numero);
	}

	public Conta atualizarPorNumero(Long numero, ContaAtualizarDTO dto) {
		var _config = serv.detalharConfigPorAtiva();
		var _conta = detalharContaPorNumero(numero);

		_conta.atualizar(dto, _config.getLimite(_conta.getTipo()));

		return _conta;
	}

	public String apagarContaPorNumero(Long numero) {
		repo.deleteByNumero(numero);

		return "Conta apagada com sucesso!";
	}

}
