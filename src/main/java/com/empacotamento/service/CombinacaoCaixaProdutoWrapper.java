package com.empacotamento.service;
import java.util.ArrayList;
import java.util.List;

import com.empacotamento.model.pedido.saida.CombinacaoCaixaProduto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CombinacaoCaixaProdutoWrapper {

	private List<CombinacaoCaixaProduto> combinacoesCaixaProduto = new ArrayList<>();

	public void addCombinacaoCaixaProduto(CombinacaoCaixaProduto combinacaoCaixaProduto) {
		combinacoesCaixaProduto.add(combinacaoCaixaProduto);
	}

}
