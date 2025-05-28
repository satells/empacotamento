package com.empacotamento.model.pedido.saida;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PedidoSaida {

	private Integer pedido_id;

	private List<Caixa> caixas = new ArrayList<>();
	public PedidoSaida(Integer pedido_id) {
		this.pedido_id = pedido_id;
	}

	public void adicionarCaixa(Caixa caixa) {
		this.caixas.add(caixa);
	}

}
