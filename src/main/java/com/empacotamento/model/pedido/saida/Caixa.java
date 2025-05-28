package com.empacotamento.model.pedido.saida;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@Getter
public class Caixa {

	private String caixa_id;

	private Set<String> produtos = new HashSet<>();
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String observacao;

	public Caixa(String caixa_id) {
		this.caixa_id = caixa_id;
	}

	public boolean adicionarProduto(String produto_id) {
		return produtos.add(produto_id);
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
