package com.empacotamento.model.pedido.entrada;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Produto {
	@NotBlank(message = "O ID do produto não pode ser vazio.")
	private String produto_id;

	@NotNull(message = "Dimensões do produto não podem ser nula.")
	@Valid
	private Dimensoes dimensoes;

}