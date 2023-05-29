package br.com.conta.service;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.conta.model.Conta;
import br.com.conta.model.dto.DepositoDTO;
import br.com.conta.model.dto.TranferenciaDTO;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class BancoServiceTest {
	@Autowired
	private BancoService serv;
	
	private DepositoDTO dto = new DepositoDTO(200.0);
	private TranferenciaDTO dtoTran = new TranferenciaDTO(9134607503583071l, 20.0);
	
	@Test
	@DisplayName("Retorna tipo Conta ao depositar na conta a partir do numero")
	void depositar() {
		var _conta = serv.depositar(9134607503583071l, dto);
		
		assertInstanceOf(Conta.class, _conta);
	}
	
	@Test
	@DisplayName("Retorna tipo Conta ao sacar na conta a partir do numero")
	void sacar() {
		var _conta = serv.sacar(9134607503583071l, dto);
		
		assertInstanceOf(Conta.class, _conta);
	}
	
	@Test
	@DisplayName("Retorna tipo Conta ao transferir da conta para outra a partir do numero")
	void transferir() {
		var _conta = serv.transferir(9134607503583071l, dtoTran);
		
		assertInstanceOf(Conta.class, _conta);
	}
}
