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

import br.com.conta.model.dto.DepositoDTO;
import br.com.conta.model.dto.TranferenciaDTO;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class FuncoesBancoControllerTest {
	@Autowired
	private MockMvc moc;
	@Autowired
	private JacksonTester<DepositoDTO> deposito;
	@Autowired
	private JacksonTester<TranferenciaDTO> transferencia;
	
	private DepositoDTO depDto = new DepositoDTO(10.0);
	private TranferenciaDTO tranDto = new TranferenciaDTO(9134607503583071l, 10.0);
	
	@Test
	@DisplayName("Retorna 200 ao depositar na conta com o numero")
	void depositar() throws Exception {
		var _json = deposito.write(depDto).getJson();
		
		moc.perform(
				MockMvcRequestBuilders.put("/banco/{numero}", "6712764845100567")
				.contentType(MediaType.APPLICATION_JSON)
				.content(_json)
				).andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	@DisplayName("Retorna 200 ao sacar na conta com o numero")
	void sacar() throws Exception {
		var _json = deposito.write(depDto).getJson();
		
		moc.perform(
				MockMvcRequestBuilders.put("/banco/{numero}/saque", "6712764845100567")
				.contentType(MediaType.APPLICATION_JSON)
				.content(_json)
				).andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	@DisplayName("Retorna 200 ao depositar na conta com o numero")
	void transferir() throws Exception {
		var _json = transferencia.write(tranDto).getJson();
		
		moc.perform(
				MockMvcRequestBuilders.put("/banco/{numero}/transferir", "6712764845100567")
				.contentType(MediaType.APPLICATION_JSON)
				.content(_json)
				).andExpect(MockMvcResultMatchers.status().is(200));
	}
}
