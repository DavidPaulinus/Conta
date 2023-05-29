package br.com.conta.service;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import br.com.conta.model.Conta;
import br.com.conta.model.TipoConta;
import br.com.conta.model.dto.conta.ContaAtualizarDTO;
import br.com.conta.model.dto.conta.ContaDTO;
import br.com.conta.service.util.repository.ContaRepository;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ContaServiceTest {
	@MockBean
	private ContaRepository repo;
	@Autowired
	private ContaService serv;
	
	private ContaDTO dto = new ContaDTO("Titular1",10.0,"Senha1.1");
	private ContaAtualizarDTO dtoAtualizar = new ContaAtualizarDTO(TipoConta.MEDIANA, "Senha2", "titular", 10.0, 9134607503583071l, LocalDate.now() );
	
	@Test
	@DisplayName("Retorna tipo Conta ao criar conta")
	void ciarConta() {
		var _conta = serv.criarNovaConta(dto);
		
		assertInstanceOf(Conta.class, _conta);
	}
	
	@Test
	@DisplayName("Retorna tipo Conta ao detalhar conta")
	void detalharConta() {
		var _conta = serv.detalharContaPorNumero(9134607503583071l);
		
		assertInstanceOf(Conta.class, _conta);
	}
	
	@Test
	@DisplayName("Retorna tipo Conta ao editar conta")
	void editarConta() {
		var _conta = serv.atualizarPorNumero(9134607503583071l, dtoAtualizar);
		
		assertInstanceOf(Conta.class, _conta);
	}
	
	@Test
	@DisplayName("Retorna String ao apagar conta")
	void apagarConta() {
		var _conta = serv.apagarContaPorNumero(9134607503583071l);
		
		assertInstanceOf(String.class, _conta);
	}
}
