package com.empacotamento.service;
import java.util.ArrayList;
import java.util.List;

import com.empacotamento.model.pedido.entrada.Dimensoes;
import com.empacotamento.model.pedido.entrada.Produto;
import com.github.skjolber.packing.api.Box;
import com.github.skjolber.packing.api.StackableItem;

public class CriarProdutosStackableItem {
	public List<StackableItem> criar(List<Produto> produtosSelecionadosPorIndice) {
		List<StackableItem> produtosStackItem = new ArrayList<StackableItem>();

		produtosSelecionadosPorIndice.stream().forEach(produto -> {
			Dimensoes dimensoes = produto.getDimensoes();

			produtosStackItem.add(new StackableItem(Box.newBuilder()
					.withId(produto.getProduto_id())
					.withSize(dimensoes.getAltura(), dimensoes.getLargura(), dimensoes.getComprimento())
					.withRotate3D()
					.withWeight(1).build(), 1));

		});

		return produtosStackItem;
	}

}
