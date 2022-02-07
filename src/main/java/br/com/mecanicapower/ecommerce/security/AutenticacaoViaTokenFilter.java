package br.com.mecanicapower.ecommerce.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import br.com.mecanicapower.ecommerce.service.TokenService;


public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;
	
// -- CONSTRUTOR PARA INJETAR O TOKEN SERVICE 
	public AutenticacaoViaTokenFilter(TokenService tokenService) {
		super();
		this.tokenService = tokenService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);
		boolean valido = tokenService.isTokenValid(token);
		System.out.println(valido);
		filterChain.doFilter(request, response);
		
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;			
		}
		return token.substring(7, token.length());
		
	}

}
