package br.com.conta.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.conta.model.dto.autenticacao.DadosAutenticacaoDTO;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class AutenticacaoControllerTest {
	@Autowired
	private MockMvc moc;
	@Autowired
	private JacksonTester<DadosAutenticacaoDTO> jack;
	
	private DadosAutenticacaoDTO dto = new DadosAutenticacaoDTO(9134607503583071l, "Senha1");
	
	@Test
	@DisplayName("Retorna 200 ao criar token")
	void login() throws Exception {
		var _json = jack.write(dto).getJson();
		
		moc.perform(MockMvcRequestBuilders.post("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(_json)
				).andExpect(MockMvcResultMatchers.status().is(200));
	}
	
}
