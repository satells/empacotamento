package com.empacotamento.model.pedido.saida;

import java.util.List;

import com.empacotamento.model.pedido.entrada.Produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CombinacaoCaixaProduto {
	private Integer pedido_id;
	private List<Produto> produtos;
	private List<String> caixas;

}
