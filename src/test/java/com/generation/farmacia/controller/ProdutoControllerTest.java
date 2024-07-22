package com.generation.farmacia.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.generation.farmacia.model.Produto;
import com.generation.farmacia.repository.ProdutoRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProdutoControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private ProdutoRepository produtoRepository;

	@BeforeAll
	void start() {
		produtoRepository.deleteAll();
	}

	@Test
	@DisplayName("Cadastrar uma Produto")
	public void deveCriarUmProduto() {

		HttpEntity<Produto> corpoRequisicao = new HttpEntity<Produto>(new Produto(null, "Remédio", BigDecimal.valueOf(22), null));

		ResponseEntity<Produto> corpoResposta = testRestTemplate.exchange("/produtos", HttpMethod.POST,
				corpoRequisicao, Produto.class);

		assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
	}

	
	@Test
	@DisplayName("Listar todos os Produtos")
	public void deveMostrarTodosUsuarios() {
		
		ResponseEntity<String> resposta = testRestTemplate
				.exchange("/produtos", HttpMethod.GET, null, String.class);
		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
}
