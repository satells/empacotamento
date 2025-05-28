package com.empacotamento.model.pedido.saida;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class PedidosSaidaWrapper {
	@JsonProperty("pedidos")
	private List<PedidoSaida> pedidosSaida = new ArrayList<>();

	public void adicionar(PedidoSaida pedidoSaida) {
		this.pedidosSaida.add(pedidoSaida);
	}
	public List<PedidoSaida> getPedidosSaida() {
		return List.copyOf(pedidosSaida);
	}

}
