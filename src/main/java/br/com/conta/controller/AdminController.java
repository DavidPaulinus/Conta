package br.com.conta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.conta.model.dto.ConfigDTO;
import br.com.conta.model.dto.DetalharConfiguracoesDTO;
import br.com.conta.model.dto.conta.ContaAtualizarDTO;
import br.com.conta.model.dto.conta.DetalharContaDTO;
import br.com.conta.service.AdminService;
import br.com.conta.service.ContaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminServ;
	@Autowired
	private ContaService userServ;

	//configurações
	@PostMapping("/config")
	@Transactional
	public ResponseEntity<DetalharConfiguracoesDTO> criarConfiguracoes(@RequestBody @Valid ConfigDTO dto, UriComponentsBuilder uri) {
		var _config = adminServ.criarConfig(dto);

		return ResponseEntity.created(uri.path("/admin").buildAndExpand().toUri()).body(new DetalharConfiguracoesDTO(_config));
	}

	@GetMapping("/config")
	public ResponseEntity<DetalharConfiguracoesDTO> detalharConfiguracoes() {
		return ResponseEntity.ok(new DetalharConfiguracoesDTO(adminServ.detalharConfigPorAtiva()));
	}

	@PutMapping("/config/editar")
	@Transactional
	public ResponseEntity<DetalharConfiguracoesDTO> editarConfig(@Valid ConfigDTO dto) {
		var _config = adminServ.editarConfigPorAtiva(dto);

		return ResponseEntity.ok(new DetalharConfiguracoesDTO(_config));
	}
	
	@DeleteMapping("/config/{id}")
	@Transactional
	public ResponseEntity<String> apagarConfiguracao(@PathVariable Long id) {
		return ResponseEntity.ok(adminServ.apagarConfigPorId(id));
	}

	//usuário
	@PutMapping("/conta/{numero}")
	@Transactional
	public ResponseEntity<DetalharContaDTO> atualizarConta(@PathVariable Long numero, @RequestBody @Valid ContaAtualizarDTO dto) {
		var _conta = userServ.atualizarPorNumero(numero, dto);

		return ResponseEntity.ok(new DetalharContaDTO(_conta));
	}

	@DeleteMapping("/conta/{numero}")
	@Transactional
	public ResponseEntity<String> apagarConta(@PathVariable Long numero) {
		var _conta = userServ.apagarContaPorNumero(numero);

		return ResponseEntity.ok(_conta);
	}
}
