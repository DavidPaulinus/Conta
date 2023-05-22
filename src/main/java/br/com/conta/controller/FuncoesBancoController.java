package br.com.conta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.conta.model.dto.DepositoDTO;
import br.com.conta.model.dto.DetalharContaDTO;
import br.com.conta.model.dto.TranferenciaDTO;
import br.com.conta.service.BancoService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/banco")
public class FuncoesBancoController {
	@Autowired
	private BancoService serv;

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DetalharContaDTO> depositar(@PathVariable Long id, @RequestBody DepositoDTO valor) {
		var _conta = serv.depositar(id, valor);

		return ResponseEntity.ok(new DetalharContaDTO(_conta));
	}
	
	@PutMapping("/{id}/saque")
	@Transactional
	public ResponseEntity<DetalharContaDTO> sacar(@PathVariable Long id, @RequestBody DepositoDTO valor){
		var _conta = serv.sacar(id, valor);
		
		return ResponseEntity.ok(new DetalharContaDTO(_conta));
	}

	@PutMapping("/{id}/tranferir")
	@Transactional
	public ResponseEntity<DetalharContaDTO> tranferir(@PathVariable Long id, @RequestBody TranferenciaDTO dto){
		var _conta = serv.transferir(id, dto);
		
		return ResponseEntity.ok(new DetalharContaDTO(_conta));
	}
	
}
