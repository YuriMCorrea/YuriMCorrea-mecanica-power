package br.com.mecanicapower.ecommerce.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.mecanicapower.ecommerce.entity.Topico;
import br.com.mecanicapower.ecommerce.enums.StatusTopico;

public class DetalhesDoTopicoDTO {
	
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private String nomeAutor;
	private StatusTopico status;
	private List<RespostaDTO> respostas;
	
// ---- CONSTRUCTOR'S
	//--Default	
	public DetalhesDoTopicoDTO() {
		
	}
	//--Full
	public DetalhesDoTopicoDTO(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
		this.nomeAutor = topico.getAutor().getNome();
		this.status = topico.getStatus();
		this.respostas = new ArrayList<>();
		this.respostas.addAll(topico.getRespostas().stream().map(RespostaDTO::new).collect(Collectors.toList()));
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
	public String getNomeAutor() {
		return nomeAutor;
	}
	public StatusTopico getStatus() {
		return status;
	}
	public List<RespostaDTO> getRespostas() {
		return respostas;
	}
	
}
