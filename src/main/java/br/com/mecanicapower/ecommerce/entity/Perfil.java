package br.com.mecanicapower.ecommerce.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Perfil implements GrantedAuthority{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
// ---- CONSTRUCTOR'S
	//--Default
	public Perfil() {
	}
	//--Full
	public Perfil(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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
	
// ----------------- HASH CODE -------------------------
	@Override
	public int hashCode() {
		return Objects.hash(id, nome);
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
		Perfil other = (Perfil) obj;
		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
	}
// ----------------- TO STRING -------------------------	
	@Override
	public String toString() {
		return "Perfil [id=" + id + ", nome=" + nome + "]";
	}
@Override
public String getAuthority() {
	// TODO Auto-generated method stub
	return nome;
}
	
}
