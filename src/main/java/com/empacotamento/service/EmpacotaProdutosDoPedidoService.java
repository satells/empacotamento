package com.empacotamento.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.empacotamento.model.pedido.entrada.PedidoEntrada;
import com.empacotamento.model.pedido.entrada.Produto;
import com.empacotamento.model.pedido.saida.CombinacaoCaixaProduto;

@Service
public class EmpacotaProdutosDoPedidoService {

	public static CombinacaoCaixaProdutoWrapper empacota(PedidoEntrada pedidoEntrada) {
		CombinacaoCaixaProdutoWrapper combinacaoCaixaProdutoWrapper = new CombinacaoCaixaProdutoWrapper();
		List<Produto> produtos = pedidoEntrada.getProdutos();
		int totalProdutos = produtos.size();
		Set<Set<Integer>> subConjuntosDeIndices = new GerarCombinacoesDeIndices(totalProdutos)
				.getSubConjuntosDeIndices();

		for (Set<Integer> subConjuntoIndex : subConjuntosDeIndices) {

			List<Produto> produtosSelecionadosPorIndice = new SelecionarProdutosPorIndice().selecionar(subConjuntoIndex,
					produtos);
			List<String> caixasQueCabem = new EscolherQualCaixaCabe()
					.escolher(new CriarProdutosStackableItem().criar(produtosSelecionadosPorIndice));


			combinacaoCaixaProdutoWrapper.addCombinacaoCaixaProduto(new CombinacaoCaixaProduto(
					pedidoEntrada.getPedido_id(), produtosSelecionadosPorIndice, caixasQueCabem));
			System.out.println(caixasQueCabem);

		}
		return combinacaoCaixaProdutoWrapper;

	}
}
