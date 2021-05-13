package com.algaworks.algafood.jpa;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class InclusaoRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restaurantes = applicationContext.getBean(RestauranteRepository.class);
		
		Restaurante restaurante1 = new Restaurante();
		restaurante1.setNome("Confraria");
		restaurante1.setTaxaFrete(new BigDecimal("10"));
		
		Restaurante restaurante2 = new Restaurante();
		restaurante2.setNome("Florença");
		restaurante2.setTaxaFrete(new BigDecimal("5.50"));
		
		restaurante1 = restaurantes.save(restaurante1);
		restaurante2 = restaurantes.save(restaurante2);
		
		System.out.printf("%d - %s - %.2f\n", restaurante1.getId(), restaurante1.getNome(), restaurante1.getTaxaFrete());
		System.out.printf("%d - %s - %.2f\n", restaurante2.getId(), restaurante2.getNome(), restaurante2.getTaxaFrete());
	}
	
}
