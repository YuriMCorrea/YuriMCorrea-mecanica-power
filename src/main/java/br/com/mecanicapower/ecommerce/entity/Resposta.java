package br.com.mecanicapower.ecommerce.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Resposta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mensagem;
	@ManyToOne
	private Topico topico;
	private LocalDateTime dataCriacao = LocalDateTime.now();
	@ManyToOne
	@Embedded
	private Usuario autor;
	private Boolean solucao = false;
// ---- CONSTRUCTOR'S
	//--Default	
	public Resposta() {
	}
	//--Full
	public Resposta(Long id, String mensagem, Topico topico, LocalDateTime dataCriacao, Usuario autor,
			Boolean solucao) {
		super();
		this.id = id;
		this.mensagem = mensagem;
		this.topico = topico;
		this.dataCriacao = dataCriacao;
		this.autor = autor;
		this.solucao = solucao;
	}
// ----------------- GETTER'S & SETTER'S -------------------------	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Topico getTopico() {
		return topico;
	}
	public void setTopico(Topico topico) {
		this.topico = topico;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Usuario getAutor() {
		return autor;
	}
	public void setAutor(Usuario autor) {
		this.autor = autor;
	}
	public Boolean getSolucao() {
		return solucao;
	}
	public void setSolucao(Boolean solucao) {
		this.solucao = solucao;
	}
// ----------------- HASH CODE -------------------------
	@Override
	public int hashCode() {
		return Objects.hash(autor, id, mensagem, solucao, topico);
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
		Resposta other = (Resposta) obj;
		return Objects.equals(autor, other.autor) && Objects.equals(id, other.id)
				&& Objects.equals(mensagem, other.mensagem) && Objects.equals(solucao, other.solucao)
				&& Objects.equals(topico, other.topico);
	}
// ----------------- TO STRING -------------------------	
	@Override
	public String toString() {
		return "Resposta [id=" + id + ", mensagem=" + mensagem + ", topico=" + topico + ", autor=" + autor
				+ ", solucao=" + solucao + "]";
	}
	
}
