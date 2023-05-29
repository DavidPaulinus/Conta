package br.com.conta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.conta.model.dto.DepositoDTO;
import br.com.conta.model.dto.TranferenciaDTO;
import br.com.conta.model.dto.conta.DetalharContaDTO;
import br.com.conta.service.BancoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/banco")
public class FuncoesBancoController {
	@Autowired
	private BancoService serv;

	@PutMapping("/{numero}")
	@Transactional
	public ResponseEntity<DetalharContaDTO> depositar(@PathVariable Long numero, @RequestBody @Valid DepositoDTO valor) {
		var _conta = serv.depositar(numero, valor);

		return ResponseEntity.ok(new DetalharContaDTO(_conta));
	}

	@PutMapping("/{numero}/saque")
	@Transactional
	public ResponseEntity<DetalharContaDTO> sacar(@PathVariable Long numero, @RequestBody @Valid DepositoDTO valor) {
		var _conta = serv.sacar(numero, valor);

		return ResponseEntity.ok(new DetalharContaDTO(_conta));
	}

	@PutMapping("/{numero}/transferir")
	@Transactional
	public ResponseEntity<DetalharContaDTO> tranferir(@PathVariable Long numero, @RequestBody TranferenciaDTO dto) {
		var _conta = serv.transferir(numero, dto);

		return ResponseEntity.ok(new DetalharContaDTO(_conta));
	}
}
