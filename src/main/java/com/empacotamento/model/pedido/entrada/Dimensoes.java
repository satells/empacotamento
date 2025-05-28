package com.empacotamento.model.pedido.entrada;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Dimensoes {

	@NotNull(message = "A altura não pode ser nula.")
	@Min(value = 1, message = "A altura deve ser no mínimo 1.")
	private Integer altura;
	
	@NotNull(message = "A largura não pode ser nula.")
	@Min(value = 1, message = "A largura deve ser no mínimo 1.")
	private Integer largura;
	
	@NotNull(message = "O comprimento não pode ser nulo.")
	@Min(value = 1, message = "O comprimento deve ser no mínimo 1.")
	private Integer comprimento;

}