package com.empacotamento.model.pedido.entrada;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoEntrada {
	@NotNull(message = "O ID do pedido não pode ser nulo.")
	@Positive(message = "O ID do pedido deve ser um número positivo.")
	private Integer pedido_id;

	@Getter(AccessLevel.NONE)
	@JsonProperty("produtos")
	@NotNull(message = "A lista de produtos não pode ser nula.")
	@Size(min = 1, message = "A lista de produtos não pode ser vazia.")
	@Valid
	private List<Produto> produtos = new ArrayList<>();

	public List<Produto> getProdutos() {
		return List.copyOf(this.produtos);
	}

}