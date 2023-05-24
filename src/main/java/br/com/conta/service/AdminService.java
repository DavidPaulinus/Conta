package br.com.conta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.conta.model.Configuracao;
import br.com.conta.model.dto.ConfigDTO;
import br.com.conta.service.util.repository.AdminRepository;

@Service
public class AdminService {
	@Autowired
	private AdminRepository repo;

	public Configuracao criarConfig(ConfigDTO dto) {
		if (repo.findAll() != null)
			repo.findAll().forEach(x -> x.desativar());

		var _config = new Configuracao(dto);
		repo.save(_config);

		return _config;
	}

	public Configuracao detalharConfigPorId(Long id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Configuração não encontrada."));
	}

	public Configuracao detalharConfigPorAtiva() {
		return repo.findByAtiva(true).orElseThrow(() -> new RuntimeException("Configuração não encontrada."));
	}

	public Configuracao editarConfigPorId(Long id, ConfigDTO dto) {
		var _config = detalharConfigPorId(id);

		_config.atualizar(dto);

		return _config;
	}

	public Configuracao editarConfigPorAtiva(ConfigDTO dto) {
		var _config = detalharConfigPorAtiva();

		_config.atualizar(dto);

		return _config;
	}

	public String apagarConfigPorId(Long id) {
		repo.deleteById(id);

		return "Configuração apagada com sucesso";
	}

	public String apagarConfigPorAtiva() {
		var _config = detalharConfigPorAtiva();
		repo.deleteById(_config.getId());

		return "Configuração apagada com sucesso";
	}
}
