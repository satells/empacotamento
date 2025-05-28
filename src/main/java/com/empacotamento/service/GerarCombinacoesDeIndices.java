package com.empacotamento.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.google.common.collect.Sets;

public class GerarCombinacoesDeIndices {

	private Set<Integer> originalSet = new HashSet<>();

	public GerarCombinacoesDeIndices(int totalProdutos) {
		for (int indice = 0; indice < totalProdutos; indice++) {
			this.originalSet.add(indice);
		}
	}

	Comparator<Set<Integer>> comparadorDeConjuntos = new Comparator<Set<Integer>>() {
		@Override
		public int compare(Set<Integer> set1, Set<Integer> set2) {
			int resultado = Integer.compare(set2.size(), set1.size());
			if (resultado != 0) {
				return resultado;
			}
			List<Integer> list1 = new ArrayList<>(set1);
			Collections.sort(list1);
			List<Integer> list2 = new ArrayList<>(set2);
			Collections.sort(list2);

			for (int i = 0; i < list1.size(); i++) {
				int elementCompare = Integer.compare(list1.get(i), list2.get(i));
				if (elementCompare != 0) {
					return elementCompare;
				}
			}
			return 0;
		}
	};

	public Set<Set<Integer>> getSubConjuntosDeIndices() {
		Set<Set<Integer>> powerSet = Sets.powerSet(originalSet);
		Set<Set<Integer>> ordenado = new TreeSet<>(comparadorDeConjuntos);
		ordenado.addAll(powerSet);

		return ordenado;
	}

}