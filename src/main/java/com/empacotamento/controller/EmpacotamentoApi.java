package com.empacotamento.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.empacotamento.model.pedido.entrada.PedidosEntradaWrapper;
import com.empacotamento.model.pedido.saida.PedidosSaidaWrapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Empacotamento", description = "API para definir a melhor forma de empacotar o itens de um pedido")
@RequestMapping("/empacotamentos")
public interface EmpacotamentoApi {
	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Recebe e processa pedidos de entrada para empacotamento", description = "Este endpoint aceita um lote de pedidos de entrada no formato JSON para processamento.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pedidos processados com sucesso", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = PedidosSaidaWrapper.class),
					examples = {
							@ExampleObject(name = "Exemplo de Saída Processada", summary = "Exemplo de resposta JSON após processamento de pedidos", value = """
									{
									    "pedidos": [
									        {
									            "pedido_id": 1,
									            "caixas": [
									                {
									                    "caixa_id": "Caixa 1",
									                    "produtos": [
									                        "Volante",
									                        "PS5"
									                    ]
									                }
									            ]
									        },
									        {
									            "pedido_id": 5,
									            "caixas": [
									                {
									                    "caixa_id": null,
									                    "produtos": [
									                        "Cadeira Gamer"
									                    ],
									                    "observacao": "Produto não cabe em nenhuma caixa disponível."
									                }
									            ]
									        }
									    ]
									}
									"""
							) })),
			@ApiResponse(responseCode = "400", description = "Requisição inválida (problemas de validação ou formato JSON)", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = com.empacotamento.exception.ErrorDetails.class))),
			@ApiResponse(responseCode = "415", description = "Tipo de mídia não suportado", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = com.empacotamento.exception.ErrorDetails.class))) })
	@RequestBody(description = "Dados do pedido de entrada para processamento", required = true, content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = PedidosEntradaWrapper.class), examples = {
			@ExampleObject(name = "Exemplo Completo de Pedido", summary = "Exemplo de um ou mais pedidos de entrada para empacotamento", value = """
					{
					  "pedidos": [
					    {
					      "pedido_id": 101,
					      "produtos": [
					        {
					          "produto_id": "PROD-ABC-001",
					          "dimensoes": {
					            "altura": 10,
					            "largura": 5,
					            "comprimento": 2
					          }
					        }
					      ]
					    },
					    {
					      "pedido_id": 102,
					      "produtos": [
					        {
					          "produto_id": "PROD-XYZ-002",
					          "dimensoes": {
					            "altura": 15,
					            "largura": 8,
					            "comprimento": 3
					          }
					        }
					      ]
					    }
					  ]
					}
					""") }))
	public ResponseEntity<?> receberPedidos(@RequestBody @Valid PedidosEntradaWrapper pedidosWrapper);

}
