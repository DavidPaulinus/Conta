package br.com.conta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.conta.model.Conta;
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
	
}
