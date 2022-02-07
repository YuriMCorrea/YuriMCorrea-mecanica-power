package br.com.mecanicapower.ecommerce.dtos;

public class TokenDTO {
	
	private String token;
	private String tipo;

	public TokenDTO (String token, String tipo) {
		this.token = token;
		this.tipo = tipo;
	}
// ----------------- GETTER'S - NO SETTER'S -------------------------

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}
	
}
