package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

public class InclusaoPermissaoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		PermissaoRepository permissoes = applicationContext.getBean(PermissaoRepository.class);
		
		Permissao permissao1 = new Permissao();
		permissao1.setNome("Cancelar Pedido");
		permissao1.setDescricao("Permite cancelar pedido");
		
		Permissao permissao2 = new Permissao();
		permissao2.setNome("Conceder Desconto");
		permissao2.setDescricao("Permite conceder desconto");
		
		permissao1 = permissoes.save(permissao1);
		permissao2 = permissoes.save(permissao2);
		
		System.out.printf("%d - %s\n", permissao1.getId(), permissao1.getNome());
		System.out.printf("%d - %s\n", permissao2.getId(), permissao2.getNome());
	}
	
}
