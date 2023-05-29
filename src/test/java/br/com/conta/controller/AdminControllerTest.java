package br.com.conta.controller;

import java.time.LocalDate;
import java.util.List;

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

import br.com.conta.model.TipoConta;
import br.com.conta.model.dto.ConfigDTO;
import br.com.conta.model.dto.conta.ContaAtualizarDTO;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class AdminControllerTest {
	@Autowired
	private MockMvc moc;
	@Autowired
	private JacksonTester<ConfigDTO> jack;
	@Autowired
	private JacksonTester<ContaAtualizarDTO> conta;

	private ConfigDTO cDto = new ConfigDTO(60l, List.of(100d, 200d, 300d), true);
	private ContaAtualizarDTO atuDto = new ContaAtualizarDTO(TipoConta.MEDIANA, "Senha2", "titular", 10.0, 9134607503583071l, LocalDate.now());
	
	//configuracoes
	@Test
	@DisplayName("Retorna 201 ao criar configuracao")
	void criarConfiguracao() throws Exception {
		var _json = jack.write(cDto).getJson();

		moc.perform(
				MockMvcRequestBuilders.post("/admin/config")
				.contentType(MediaType.APPLICATION_JSON)
				.content(_json)
				)
				.andExpect(MockMvcResultMatchers.status().is(201));
	}
	
	@Test
	@DisplayName("Retorna 200 ao detalhar configuracao")
	void detalharConfiguracao() throws Exception {
		moc.perform(
				MockMvcRequestBuilders.post("/admin/config")
				)
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	@DisplayName("Retorna 200 ao editar configuracao")
	void editarConfiguracao() throws Exception {
		var _json = jack.write(cDto).getJson();

		moc.perform(
				MockMvcRequestBuilders.post("/admin/config/editar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(_json)
				)
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	@DisplayName("Retorna 200 ao apagar configuracao")
	void apagarConfiguracao() throws Exception {
		moc.perform(
				MockMvcRequestBuilders.post("/admin/config/{id}", 1l)
				)
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	
	//usuario
	@Test
	@DisplayName("Retorna 200 ao editar usuario")
	void editarUsuario() throws Exception {
		var _json = conta.write(atuDto).getJson();

		moc.perform(
				MockMvcRequestBuilders.post("/admin/conta/{numero}", "6712764845100567")
				.contentType(MediaType.APPLICATION_JSON)
				.content(_json)
				)
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	@DisplayName("Retorna 200 ao apagar usuario")
	void apagarUsuario() throws Exception {
		moc.perform(
				MockMvcRequestBuilders.post("/admin/conta/{numero}", "6712764845100567")
				)
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
}
