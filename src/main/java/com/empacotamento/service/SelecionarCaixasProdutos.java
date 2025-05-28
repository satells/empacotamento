package com.empacotamento.service;

import java.util.List;

import com.empacotamento.model.pedido.entrada.PedidoEntrada;
import com.empacotamento.model.pedido.entrada.Produto;
import com.empacotamento.model.pedido.saida.Caixa;
import com.empacotamento.model.pedido.saida.CombinacaoCaixaProduto;
import com.empacotamento.model.pedido.saida.PedidoSaida;

public class SelecionarCaixasProdutos {

	private static final Integer PRIMEIRA_CAIXA = 0;
	private int index = 0;

	public PedidoSaida selecionar(PedidoEntrada pedidoEntrada,
			CombinacaoCaixaProdutoWrapper combinacaoCaixaProdutoWrapper) {

		PedidoSaida pedidoSaida = new PedidoSaida(pedidoEntrada.getPedido_id());
		List<CombinacaoCaixaProduto> combinacoesCaixaProduto = combinacaoCaixaProdutoWrapper
				.getCombinacoesCaixaProduto();

		CombinacaoCaixaProduto combinacaoCaixaProduto = combinacoesCaixaProduto.get(next());
		if (combinacaoCaixaProduto.getCaixas().size() == 0) {

			Caixa caixa = new Caixa(null);
			List<Produto> produtos = combinacaoCaixaProduto.getProdutos();
			produtos.stream().forEach(produto -> caixa.adicionarProduto(produto.getProduto_id()));
			pedidoSaida.adicionarCaixa(caixa);
			caixa.setObservacao("Produto não cabe em nenhuma caixa disponível.");
			return pedidoSaida;

		}

		if (pedidoEntrada.getProdutos().size() == combinacaoCaixaProduto.getProdutos().size()) {

			String caixa_id = combinacaoCaixaProduto.getCaixas().get(PRIMEIRA_CAIXA);

			Caixa caixa = new Caixa(caixa_id);
			List<Produto> produtos = combinacaoCaixaProduto.getProdutos();

			produtos.stream().forEach(produto -> caixa.adicionarProduto(produto.getProduto_id()));
			pedidoSaida.adicionarCaixa(caixa);
			System.out.println(pedidoSaida);
			return pedidoSaida;

		}
		return null;
	}

	private int next() {
		return index++;
	}

}
