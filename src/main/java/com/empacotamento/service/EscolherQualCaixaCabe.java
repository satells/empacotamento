package com.empacotamento.service;
import java.util.ArrayList;
import java.util.List;

import com.github.skjolber.packing.api.Container;
import com.github.skjolber.packing.api.ContainerItem;
import com.github.skjolber.packing.api.PackagerResult;
import com.github.skjolber.packing.api.StackableItem;
import com.github.skjolber.packing.packer.plain.PlainPackager;

public class EscolherQualCaixaCabe {

	public List<String> escolher(List<StackableItem> produtosSelecionados) {
		CaixaService caixaService = new CaixaService();
		List<String> caixas = new ArrayList<>();
		for (String caixaKey : caixaService.getCaixaKey()) {
			Container caixa = caixaService.getCaixa(caixaKey);

			List<ContainerItem> containerItems = ContainerItem.newListBuilder().withContainer(caixa).build();
			PlainPackager packager = PlainPackager.newBuilder().build();
			PackagerResult packagerResult = packager.newResultBuilder().withContainers(containerItems)
					.withStackables(produtosSelecionados).build();

			if (packagerResult.isSuccess()) {
				Container match = packagerResult.get(0);
				caixas.add(match.getDescription());
			}

		}
		return caixas;

	}

}
