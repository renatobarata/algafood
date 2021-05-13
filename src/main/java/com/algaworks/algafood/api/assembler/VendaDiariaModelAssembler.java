package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.VendaDiariaModel;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;

@Component
public class VendaDiariaModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public VendaDiariaModel toModel(VendaDiaria vendaDiaria) {
		return modelMapper.map(vendaDiaria, VendaDiariaModel.class);
	}
	
	public List<VendaDiariaModel> toCollectionModel(List<VendaDiaria> vendasDiarias) {
		return vendasDiarias.stream()
				.map(vendaDiaria -> toModel(vendaDiaria))
				.collect(Collectors.toList());
	}
	
}
