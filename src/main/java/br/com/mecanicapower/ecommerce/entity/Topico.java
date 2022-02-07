package br.com.mecanicapower.ecommerce.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.mecanicapower.ecommerce.enums.StatusTopico;
@Entity
public class Topico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao = LocalDateTime.now();
	@Enumerated(EnumType.STRING)
	private StatusTopico status = StatusTopico.NAO_RESPONDIDO;
	@ManyToOne @Embedded
	private Usuario autor;
	@ManyToOne @Embedded
	private Curso curso;
	@OneToMany(mappedBy = "topico")
	private List<Resposta> respostas = new ArrayList<>();

// ---- CONSTRUCTOR'S
	//--Default	
	public Topico() {
		
	}
	//--Full
	public Topico(Long id, String titulo, String mensagem, LocalDateTime dataCriacao, StatusTopico status,
			Usuario autor, Curso curso, List<Resposta> respostas) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.dataCriacao = dataCriacao;
		this.status = status;
		this.autor = autor;
		this.curso = curso;
		this.respostas = respostas;
	}
	//--Form
	public Topico(String titulo, String mensagem, Curso curso) {
		super();
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.curso = curso;
	}
// ----------------- GETTER'S & SETTER'S -------------------------	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public StatusTopico getStatus() {
		return status;
	}
	public void setStatus(StatusTopico status) {
		this.status = status;
	}
	public Usuario getAutor() {
		return autor;
	}
	public void setAutor(Usuario autor) {
		this.autor = autor;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public List<Resposta> getRespostas() {
		return respostas;
	}
	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}
// ----------------- HASH CODE -------------------------	
	@Override
	public int hashCode() {
		return Objects.hash(autor, dataCriacao, id, mensagem, titulo);
	}
// ----------------- EQUALS -------------------------
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topico other = (Topico) obj;
		return Objects.equals(autor, other.autor) && Objects.equals(dataCriacao, other.dataCriacao)
				&& Objects.equals(id, other.id) && Objects.equals(mensagem, other.mensagem)
				&& Objects.equals(titulo, other.titulo);
	}
// ----------------- TO STRING -------------------------	
	@Override
	public String toString() {
		return "Topico [id=" + id + ", titulo=" + titulo + ", mensagem=" + mensagem + ", dataCriacao=" + dataCriacao
				+ ", autor=" + autor + "]";
	}
	
}
