package br.com.mecanicapower.ecommerce.dtos;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.mecanicapower.ecommerce.entity.Topico;

public class TopicoDTO {
	
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	
// ---- CONSTRUCTOR'S
	//--Default	
	public TopicoDTO() {
		
	}
	//--Full
	public TopicoDTO(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
	}
// ----------------- GETTER'S - NO SETTER'S -------------------------	
	public Long getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public static Page<TopicoDTO> converterObjToDTO(Page<Topico> topicos) {
		return topicos.map(TopicoDTO::new);
	}
	
	
	
	
}
