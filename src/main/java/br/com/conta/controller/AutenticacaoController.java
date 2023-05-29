package br.com.conta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.conta.infra.TokenJwtDTO;
import br.com.conta.infra.TokenService;
import br.com.conta.model.Conta;
import br.com.conta.model.dto.autenticacao.DadosAutenticacaoDTO;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/login")
public class AutenticacaoController {
	@Autowired
	private AuthenticationManager manager;
	@Autowired
	private TokenService token;
	
	@PostMapping
	public ResponseEntity<TokenJwtDTO> login(@RequestBody @Valid DadosAutenticacaoDTO dto) {
		var _authenticationToken = new UsernamePasswordAuthenticationToken(dto.numero(), dto.senha());
		var _authentication = manager.authenticate(_authenticationToken);
		
		var _tokenJWT = token.gerarToken((Conta)_authentication.getPrincipal());
		
		return ResponseEntity.ok(new TokenJwtDTO(_tokenJWT));
	}
	
}
