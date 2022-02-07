package br.com.mecanicapower.ecommerce.forms;

import br.com.mecanicapower.ecommerce.entity.Curso;
import br.com.mecanicapower.ecommerce.entity.Topico;
import br.com.mecanicapower.ecommerce.repositories.CursoRepository;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

@Validated
public class TopicoForm {
	
	@NotNull(message="Título é obrigatório.") 
	@NotEmpty(message="Título é obrigatório.") 
	@Size(min=3, max=20, message="Título deve ter no min 3 e no max 20 caracteres.")
	private String titulo;
	@NotNull(message="Mensagem é obrigatória.") 
	@NotEmpty(message="Mensagem é obrigatória.") 
	@Size(min=3, max=1000, message="Mensagem deve ter no min 3 e no max 1000 caracteres.")
	private String mensagem;
	@NotNull(message="Nome do Curso é obrigatório.") 
	@NotEmpty(message="Nome do Curso é obrigatório.") 
	@Size(min=3, max=1000, message="Nome do Curso deve ter no min 3 e no max 20 caracteres.")
	private String nomeCurso;
// ---- CONSTRUCTOR'S
	//--Default	
	public TopicoForm() {
	}
	//--Full
	public TopicoForm(String titulo, String mensagem, String nomeCurso) {
		super();
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.nomeCurso = nomeCurso;
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
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	public Topico converterFormToObj(CursoRepository repo) {
		Curso curso = repo.findByNome(nomeCurso);
		return new Topico(titulo, mensagem, curso);
	}
	
	
}
