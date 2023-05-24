package br.com.conta.infra;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.conta.model.Conta;

@Service
public class TokenService {
	public String gerarToken(Conta conta) {
		try {
			var _algoritmo = Algorithm.HMAC256("123456");
			return JWT.create()
					.withIssuer("API banco")
					.withClaim("id", conta.getId())
					.withSubject(conta.getPassword())
					.withExpiresAt(dataExpiracao())
					.sign(_algoritmo);
			
		}catch(JWTCreationException  e) {
			throw new RuntimeException("Erro ao criar Token", e);
		}
	}
	
	public String getSubject(String JwtToken) {
		try {
			var _algoritimo = Algorithm.HMAC256("123456");
		    return JWT.require(_algoritimo)
		        .withIssuer("API banco")
		        .build()
		        .verify(JwtToken)
		        .getSubject();
		}catch(JWTVerificationException e) {
			throw new RuntimeException("Token JWT inválido ou expirado");
		}
	}
	
	public Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
