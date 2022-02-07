package br.com.mecanicapower.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mecanicapower.ecommerce.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{

	Curso findByNome(String nomeCurso);
	
	
}
