package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;

public class InclusaoCidadeMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CidadeRepository cidades = applicationContext.getBean(CidadeRepository.class);
		
		Cidade cidade1 = new Cidade();
		cidade1.setNome("Salvador");
		
		Estado estado1 = new Estado();
		estado1.setId(1L);
		cidade1.setEstado(estado1);

		
		Cidade cidade2 = new Cidade();
		cidade2.setNome("Rio de Janeiro");
		
		Estado estado2 = new Estado();
		estado2.setId(2L);
		cidade2.setEstado(estado2);
		
		cidade1 = cidades.save(cidade1);
		cidade2 = cidades.save(cidade2);
		
		System.out.printf("%d - %s\n", cidade1.getId(), cidade1.getNome());
		System.out.printf("%d - %s\n", cidade2.getId(), cidade2.getNome());
	}
	
}
