package com.empacotamento.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.github.skjolber.packing.api.Container;

public class CaixaService {

	private static final String CAIXA_1_KEY = "Caixa 1";
	private static final String CAIXA_2_KEY = "Caixa 2";
	private static final String CAIXA_3_KEY = "Caixa 3";

	private Set<String> caixaKey = new TreeSet<>();

	private Map<String, Container> caixas = new HashMap<>();

	public CaixaService() {
		this.caixaKey.add(CAIXA_1_KEY);
		this.caixaKey.add(CAIXA_2_KEY);
		this.caixaKey.add(CAIXA_3_KEY);

		caixas.put(CAIXA_1_KEY, Container.newBuilder().withDescription(CAIXA_1_KEY).withSize(30, 40, 80)
				.withEmptyWeight(1).withMaxLoadWeight(1000).build());
		caixas.put(CAIXA_2_KEY, Container.newBuilder().withDescription(CAIXA_2_KEY).withSize(80, 50, 40)
				.withEmptyWeight(1).withMaxLoadWeight(1000).build());
		caixas.put(CAIXA_3_KEY, Container.newBuilder().withDescription(CAIXA_3_KEY).withSize(50, 80, 60)
				.withEmptyWeight(1).withMaxLoadWeight(1000).build());
	}

	public Container getCaixa(String caixaKey) {
		return caixas.get(caixaKey);
	}

	public Set<String> getCaixaKey() {
		return caixaKey;
	}

}
