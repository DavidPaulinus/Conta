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

import br.com.conta.model.dto.conta.ContaDTO;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ContaControllerTest {
	@Autowired
	private MockMvc mock;
	@Autowired
	private JacksonTester<ContaDTO> jackson;

	private ContaDTO dto = new ContaDTO("Titular1", 10.0, "Senha1");

	@Test
	@DisplayName("Retorna 201 ao criar conta")
	void cadastrar() throws Exception {
		var _json = jackson.write(dto).getJson();

		mock.perform(
				MockMvcRequestBuilders.post("/conta/criar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(_json)
				)
				.andExpect(MockMvcResultMatchers.status().is(201));
	}
	
	@Test
	@DisplayName("Retorna 200 ao detalhar conta")
	void detalhar() throws Exception {
		mock.perform(
				MockMvcRequestBuilders.post("/conta/{numero}", "Senha1")
				)
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

}
