package br.com.mecanicapower.ecommerce.exceptions;

public class ErroDeFromularioDTO {
	private String campo;
	private String erro;
	
// ---- CONSTRUCTOR'S
	//--Default	
	public ErroDeFromularioDTO() {
	}
	//--Full
	public ErroDeFromularioDTO(String campo, String erro) {
		super();
		this.campo = campo;
		this.erro = erro;
	}
// ----------------- GETTER'S - NO SETTER'S -------------------------
	public String getCampo() {
		return campo;
	}
	public String getErro() {
		return erro;
	}
	
}
