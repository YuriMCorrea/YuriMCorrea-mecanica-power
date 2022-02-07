package br.com.mecanicapower.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
//- Permite o uso direto da interface Pageable e @PageableDefault
@EnableSpringDataWebSupport
//- Permite o uso de cache
@EnableCaching
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
