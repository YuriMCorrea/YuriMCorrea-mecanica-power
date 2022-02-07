package br.com.mecanicapower.ecommerce.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.mecanicapower.ecommerce.entity.Topico;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long>{
// -------- GET TOPICOS POR CURSO -----------	
	Page<Topico> findByCurso_Nome(String nomeCurso, Pageable paginacao);

// -------- GET TOPICOS POR CATEGORIA -----------
	// Montar query em JPQL entre parenteses
	@Query("SELECT t FROM Topico t WHERE t.curso.categoria = :nomeCategoria")
	Page<Topico> carregarPorCategoriaDoCurso(@Param("nomeCategoria")String nomeCategoria, Pageable paginacao);
}
