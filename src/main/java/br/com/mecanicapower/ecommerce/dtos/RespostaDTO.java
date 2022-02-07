package br.com.mecanicapower.ecommerce.dtos;

import java.time.LocalDateTime;

import br.com.mecanicapower.ecommerce.entity.Resposta;

public class RespostaDTO {

	
	private Long id;
	private String mensagem;
	private LocalDateTime dataCriação;
	private String nomeAutor;

// ---- CONSTRUCTOR'S
	//--Default	
	public RespostaDTO() {
		
	}
	//--Full
	public RespostaDTO(Resposta resposta) {
		this.id = resposta.getId();
		this.mensagem = resposta.getMensagem();
		this.dataCriação = resposta.getDataCriacao();
		this.nomeAutor = resposta.getAutor().getNome();
	}
// ----------------- GETTER'S - NO SETTER'S -------------------------	
	public Long getId() {
		return id;
	}
	public String getMensagem() {
		return mensagem;
	}
	public LocalDateTime getDataCriação() {
		return dataCriação;
	}
	public String getNomeAutor() {
		return nomeAutor;
	}
	
	
}
