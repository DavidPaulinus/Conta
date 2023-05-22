package br.com.conta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.conta.model.Conta;
import br.com.conta.model.dto.ContaAtualizarDTO;
import br.com.conta.model.dto.ContaDTO;
import br.com.conta.service.util.repository.ContaRepository;

@Service
public class ContaService {
	@Autowired
	private ContaRepository repo;

	public Conta criarNovaConta(ContaDTO dto) {
		var _conta = new Conta(dto);
		repo.save(_conta);
		
		return _conta;
	}

	public Conta detalharContaPorId(Long id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
	}

	public Conta atualizarPorId(Long id, ContaAtualizarDTO dto) {
		var _conta = detalharContaPorId(id);
		
		_conta.atualizar(dto);
		
		return _conta;
	}
	
}
