package br.com.conta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.conta.model.dto.conta.ContaDTO;
import br.com.conta.model.dto.conta.DetalharContaDTO;
import br.com.conta.service.ContaService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/conta")
public class ContaController {
	@Autowired
	private ContaService serv;

	@PostMapping("/criar")
	@Transactional
	public ResponseEntity<DetalharContaDTO> criarNovaConta(@RequestBody ContaDTO dto, UriComponentsBuilder uri) {
		var _conta = serv.criarNovaConta(dto);

		return ResponseEntity.created(uri.path("/conta/criar").buildAndExpand(_conta.getId()).toUri())
				.body(new DetalharContaDTO(_conta));
	}

	@GetMapping("/{numero}")
	public ResponseEntity<DetalharContaDTO> detalharConta(@PathVariable Long numero) {
		var _conta = serv.detalharContaPorNumero(numero);

		return ResponseEntity.ok(new DetalharContaDTO(_conta));
	}
}
