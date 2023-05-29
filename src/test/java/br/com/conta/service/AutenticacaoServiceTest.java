package br.com.conta.service;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import br.com.conta.service.util.repository.ContaRepository;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class AutenticacaoServiceTest {
	@MockBean
	private ContaRepository repo;
	@Autowired
	private AutenticacaoService serv;
	
	@Test
	@DisplayName("Deve retornar tipo UserDetails ao carregar um usuario pelo username")
	void carregarUsuarioPorUserName() {
		var _user = serv.loadUserByUsername("9134607503583071");
		
		assertInstanceOf(UserDetails.class, _user);
	}
}
