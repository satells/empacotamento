package com.empacotamento.service;
import java.util.List;

import com.empacotamento.model.pedido.entrada.PedidoEntrada;
import com.empacotamento.model.pedido.entrada.PedidosEntradaWrapper;
import com.empacotamento.model.pedido.saida.PedidoSaida;
import com.empacotamento.model.pedido.saida.PedidosSaidaWrapper;

public class ProcessarPedidosEntrada {

	public PedidosSaidaWrapper processar(PedidosEntradaWrapper pedidosWrapper) {

		List<PedidoEntrada> pedidosEntrada = pedidosWrapper.getPedidosEntrada();

		PedidosSaidaWrapper pedidosSaidaWrapper = new PedidosSaidaWrapper();
		for (PedidoEntrada pedidoEntrada : pedidosEntrada) {
			CombinacaoCaixaProdutoWrapper combinacaoCaixaProdutoWrapper = EmpacotaProdutosDoPedidoService
					.empacota(pedidoEntrada);
			System.out.println("combinacaoCaixaProdutoWrapper: " + combinacaoCaixaProdutoWrapper);

			PedidoSaida pedidoSaida = new SelecionarCaixasProdutos().selecionar(pedidoEntrada,
					combinacaoCaixaProdutoWrapper);

			pedidosSaidaWrapper.adicionar(pedidoSaida);

		}


		return pedidosSaidaWrapper;
	}
}
