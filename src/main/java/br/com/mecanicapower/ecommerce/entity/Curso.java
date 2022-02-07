package br.com.mecanicapower.ecommerce.entity;

import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Embeddable
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String categoria;
// ---- CONSTRUCTOR'S
	//--Default	
	public Curso() {
	}
	//--Full
	public Curso(Long id, String nome, String categoria) {
		super();
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
	}
// ----------------- GETTER'S & SETTER'S -------------------------
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
// ----------------- HASH CODE -------------------------
	@Override
	public int hashCode() {
		return Objects.hash(categoria, id, nome);
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
		Curso other = (Curso) obj;
		return Objects.equals(categoria, other.categoria) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome);
	}
// ----------------- TO STRING -------------------------	
	@Override
	public String toString() {
		return "Curso [id=" + id + ", nome=" + nome + ", categoria=" + categoria + "]";
	}
	
	
}
