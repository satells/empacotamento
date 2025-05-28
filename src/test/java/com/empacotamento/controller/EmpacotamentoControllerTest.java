package com.empacotamento.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class EmpacotamentoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired

	private static final String EMPACOTAMENTOS = "/empacotamentos";

	private String readJsonFromFile(String nomeArquivo) throws Exception {
		final File file = new File(String.format("src/test/resources/%s", nomeArquivo));
		return new String(Files.readAllBytes(file.toPath()));
	}

	@Test
	void deveProcessarArquivoERetornarOProprioArquivo() throws Exception {
		String jsonEntrada = readJsonFromFile("entrada.json");
		String jsonSaida = readJsonFromFile("saida.json");

		mockMvc.perform(post(EMPACOTAMENTOS).contentType(MediaType.APPLICATION_JSON).content(jsonEntrada))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().json(jsonSaida));
	}

	@Test
	void deveRetornarErroListaDePedidosVazia() throws Exception {
		String jsonEntrada = readJsonFromFile("entrada_sem_pedido.json");

		mockMvc.perform(post(EMPACOTAMENTOS).contentType(APPLICATION_JSON).content(jsonEntrada)).andDo(print())
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.error").value("Validation Failed"))
				.andExpect(jsonPath("$.details[0].field").value("pedidosEntrada"))
				.andExpect(jsonPath("$.details[0].message").value("A lista de pedidos de entrada não pode ser vazia."));
	}

	@Test
	void deveRetornarErroPedidoVazio() throws Exception {
		String jsonEntrada = readJsonFromFile("entrada_sem_pedido.json");

		mockMvc.perform(post(EMPACOTAMENTOS)

				.contentType(APPLICATION_JSON)

				.content(jsonEntrada)).andDo(print()).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error").value("Validation Failed"))
				.andExpect(jsonPath("$.details[0].field").value("pedidosEntrada"))
				.andExpect(jsonPath("$.details[0].message").value("A lista de pedidos de entrada não pode ser vazia."));
	}

	@Test
	void deveRetornarErroPedidoComNumeroInvalido() throws Exception {
		String jsonEntrada = readJsonFromFile("entrada_pedido_sem_numero.json");

		mockMvc.perform(post(EMPACOTAMENTOS)

				.contentType(APPLICATION_JSON)

				.content(jsonEntrada)).andDo(print()).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error").value("Validation Failed"))
				.andExpect(jsonPath("$.details[0].field").value("pedidosEntrada[0].pedido_id"))
				.andExpect(jsonPath("$.details[0].message").value("O ID do pedido não pode ser nulo."));
	}

	@Test
	void deveRetornarErroPedidoSemProdutos() throws Exception {
		String jsonEntrada = readJsonFromFile("entrada_pedido_sem_produtos.json");

		mockMvc.perform(post(EMPACOTAMENTOS)

				.contentType(APPLICATION_JSON)

				.content(jsonEntrada)).andDo(print()).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error").value("Validation Failed"))
				.andExpect(jsonPath("$.details[0].field").value("pedidosEntrada[0].produtos"))
				.andExpect(jsonPath("$.details[0].message").value("A lista de produtos não pode ser vazia."));
	}

	@Test
	void deveRetornarErroProdutoSemId() throws Exception {
		String jsonEntrada = readJsonFromFile("entrada_produto_sem_id.json");

		mockMvc.perform(post(EMPACOTAMENTOS)

				.contentType(APPLICATION_JSON)

				.content(jsonEntrada)).andDo(print()).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error").value("Validation Failed"))
				.andExpect(jsonPath("$.details[0].field").value("pedidosEntrada[1].produtos[0].produto_id"))
				.andExpect(jsonPath("$.details[0].message").value("O ID do produto não pode ser vazio."));
	}

	@Test
	void deveRetornarErroProdutoSemDimensoes() throws Exception {
		String jsonEntrada = readJsonFromFile("entrada_produto_sem_dimensoes.json");

		mockMvc.perform(post(EMPACOTAMENTOS)

				.contentType(APPLICATION_JSON)

				.content(jsonEntrada)).andDo(print()).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error").value("Validation Failed"))
				.andExpect(jsonPath("$.details[0].field").value("pedidosEntrada[0].produtos[0].dimensoes"))
				.andExpect(jsonPath("$.details[0].message").value("Dimensões do produto não podem ser nula."));
	}

	@Test
	void deveRetornarErroProdutoDimensoesNulas() throws Exception {
		String jsonEntrada = readJsonFromFile("entrada_dimensoes_nulas.json");

		String campoLargura = "pedidosEntrada[0].produtos[0].dimensoes.largura";
		String mensagemCampoLargura = "A largura não pode ser nula.";

		String campoComprimento = "pedidosEntrada[0].produtos[0].dimensoes.comprimento";
		String mensagemCampoComprimento = "O comprimento não pode ser nulo.";

		String campoAltura = "pedidosEntrada[0].produtos[0].dimensoes.altura";
		String mensagemCampoAltura = "A altura não pode ser nula.";

		String expressaoCampo = "$.details[?(@.field == '%s')]";
		String expressaoMensagem = "$.details[?(@.field == '%s')].message";

		mockMvc.perform(post(EMPACOTAMENTOS)

				.contentType(APPLICATION_JSON)

				.content(jsonEntrada)).andDo(print()).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error").value("Validation Failed"))

				.andExpect(jsonPath(expressaoCampo.formatted(campoLargura)).exists())
				.andExpect(jsonPath(expressaoMensagem.formatted(campoLargura)).value(mensagemCampoLargura))

				.andExpect(jsonPath(expressaoCampo.formatted(campoComprimento)).exists())
				.andExpect(jsonPath(expressaoMensagem.formatted(campoComprimento)).value(mensagemCampoComprimento))

				.andExpect(jsonPath(expressaoCampo.formatted(campoAltura)).exists())
				.andExpect(jsonPath(expressaoMensagem.formatted(campoAltura)).value(mensagemCampoAltura))

		;
	}

	@Test
	void deveRetornarErroFormatoInvalido() throws Exception {
		String jsonEntrada = readJsonFromFile("entrada_formato_invalido.json");

		mockMvc.perform(post(EMPACOTAMENTOS)

				.contentType(APPLICATION_JSON)

				.content(jsonEntrada)).andDo(print()).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error").value("Bad Request - Deserialization Error"))
				.andExpect(jsonPath("$.details[0].field").value("pedido_id")).andExpect(
						jsonPath("$.details[0].message")
								.value("Campo 'pedido_id': valor com formato inválido. Esperado tipo Integer."));
	}

	@Test
	void deveRetornarErroUrlErrada() throws Exception {
		String jsonEntrada = readJsonFromFile("entrada.json");

		mockMvc.perform(post(EMPACOTAMENTOS + "1").contentType(MediaType.APPLICATION_JSON).content(jsonEntrada))
				.andDo(print()).andExpect(status().is(404));
	}

	@Test
	void deveRetornarErroEstruturaErrada() throws Exception {
		String jsonEntrada = readJsonFromFile("entrada_estrutura_errada.json");

		mockMvc.perform(post(EMPACOTAMENTOS).contentType(MediaType.APPLICATION_JSON).content(jsonEntrada))
				.andDo(print()).andExpect(status().is(400));
	}

	@Test
	void deveRetornarErroFormatoInvalido2() throws Exception {
		String jsonEntrada = readJsonFromFile("entrada_formato_invalido2.json");

		mockMvc.perform(post(EMPACOTAMENTOS).contentType(MediaType.APPLICATION_JSON).content(jsonEntrada))
				.andDo(print()).andExpect(status().is(400));
	}

	@Test
	void deveRetornarErroEstruturaErrada2() throws Exception {
		String jsonEntrada = readJsonFromFile("entrada_estrutura_errada2.json");

		mockMvc.perform(post(EMPACOTAMENTOS).contentType(MediaType.APPLICATION_JSON).content(jsonEntrada))
				.andDo(print()).andExpect(status().is(400));
	}

	@Test
	void deveRetornarErroEnviandoUmTexto() throws Exception {

		mockMvc.perform(
				post(EMPACOTAMENTOS).contentType(MediaType.TEXT_PLAIN).content("texto normal"))

				.andExpect(status().isUnsupportedMediaType())
				.andExpect(jsonPath("$.error").value("Unsupported Media Type"))
				.andExpect(jsonPath("$.details[0].field").value("content_type"))
				.andExpect(jsonPath("$.details[0].message")
						.value(org.hamcrest.Matchers.containsString("Tipo de mídia não suportado")));
		;
	}

}