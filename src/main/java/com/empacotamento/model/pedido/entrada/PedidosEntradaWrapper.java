package com.empacotamento.model.pedido.entrada;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Wrapper para uma lista de pedidos de entrada")
public class PedidosEntradaWrapper {
	@JsonProperty("pedidos")
	@Size(min = 1, message = "A lista de pedidos de entrada não pode ser vazia.")
	@Valid
	private List<PedidoEntrada> pedidosEntrada = new ArrayList<>();

	@JsonProperty("pedidos")
	@Schema(description = "Lista de pedidos a serem processados")
	@NotNull(message = "A lista de pedidos de entrada não pode ser nula.")
	@Size(min = 1, message = "A lista de pedidos de entrada não pode ser vazia.")
	@Valid
	public List<PedidoEntrada> getPedidosEntrada() {
		return List.copyOf(pedidosEntrada);
	}
}