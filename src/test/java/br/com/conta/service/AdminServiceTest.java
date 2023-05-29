package br.com.conta.service;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import br.com.conta.model.Configuracao;
import br.com.conta.model.dto.ConfigDTO;
import br.com.conta.service.util.repository.AdminRepository;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class AdminServiceTest {
	@MockBean
	private AdminRepository repo;
	@Autowired
	private AdminService serv;
	
	private ConfigDTO dto = new ConfigDTO(60l, List.of(400.0,500.0,600.0),true);
	
	@Test
	@DisplayName("Retorna tipo Configuracao ao criar configuracao")
	void criarConfig() {
		var _config = serv.criarConfig(dto);
		
		assertInstanceOf(Configuracao.class, _config);
	}
	
	@Test
	@DisplayName("Retorna tipo Configuracao ao detalhar configuracao ativa")
	void detalharConfig() {
		var _config = serv.detalharConfigPorAtiva();
		
		assertInstanceOf(Configuracao.class, _config);
	}
	
	@Test
	@DisplayName("Retorna tipo Configuracao ao ediat configuracao ativa")
	void ediatConfig() {
		var _config = serv.editarConfigPorAtiva(dto);
		
		assertInstanceOf(Configuracao.class, _config);
	}
	
	@Test
	@DisplayName("Retorna String ao apagar configuracao ativa")
	void apagarConfig() {
		var _config = serv.apagarConfigPorAtiva();
		
		assertInstanceOf(String.class, _config);
	}
}
