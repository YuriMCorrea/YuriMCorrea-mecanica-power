package br.com.mecanicapower.ecommerce.forms;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.mecanicapower.ecommerce.entity.Topico;
import br.com.mecanicapower.ecommerce.repositories.TopicoRepository;

public class TopicoEditarForm {
	@NotNull(message="Título é obrigatório.") 
	@NotEmpty(message="Título é obrigatório.") 
	@Size(min=3, max=20, message="Título deve ter no min 3 e no max 20 caracteres.")
	private String titulo;
	@NotNull(message="Mensagem é obrigatória.") 
	@NotEmpty(message="Mensagem é obrigatória.") 
	@Size(min=3, max=1000, message="Mensagem deve ter no min 3 e no max 1000 caracteres.")
	private String mensagem;
	
// ---- CONSTRUCTOR'S
	//--Default	
	public TopicoEditarForm() {
	}
	//--Full
	public TopicoEditarForm(String titulo, String mensagem) {
		this.titulo = titulo;
		this.mensagem = mensagem;
	}
// ----------------- GETTER'S & SETTER'S -------------------------
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Topico atualizar(Long id, TopicoRepository topicoRepository) {
		Topico topico = topicoRepository.getById(id);
		topico.setTitulo(this.titulo);
		topico.setMensagem(this.mensagem);
		return topico;
	}
	
}
