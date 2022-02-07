package br.com.mecanicapower.ecommerce.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter{
	
	//- Configurações de Autenticação
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
	}
	
	//- Configurações de Autorização, urls, quem pode acessar o que, perfis de acesso 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/topicos").permitAll()
		.antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
		.anyRequest().authenticated()
		.and().formLogin();
	}

	//- Configurações de Recursos estáticos (js, css, imagens, etc.) 
	@Override
	public void configure(WebSecurity web) throws Exception {
		
	}
}
