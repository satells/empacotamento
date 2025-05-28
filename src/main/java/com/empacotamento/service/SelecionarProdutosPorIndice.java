package com.empacotamento.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.empacotamento.model.pedido.entrada.Produto;

public class SelecionarProdutosPorIndice {
	public List<Produto> selecionar(Set<Integer> indices, List<Produto> produtos) {
		return indices.stream().filter(index -> index != null && index >= 0 && index < produtos.size())
				.map(produtos::get).collect(Collectors.toList());
	}

}
