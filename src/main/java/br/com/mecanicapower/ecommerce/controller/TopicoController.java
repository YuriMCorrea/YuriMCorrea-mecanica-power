package br.com.mecanicapower.ecommerce.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mecanicapower.ecommerce.dtos.DetalhesDoTopicoDTO;
import br.com.mecanicapower.ecommerce.dtos.TopicoDTO;
import br.com.mecanicapower.ecommerce.entity.Topico;
import br.com.mecanicapower.ecommerce.forms.TopicoEditarForm;
import br.com.mecanicapower.ecommerce.forms.TopicoForm;
import br.com.mecanicapower.ecommerce.repositories.CursoRepository;
import br.com.mecanicapower.ecommerce.repositories.TopicoRepository;

@RestController
@RequestMapping(value = "/topicos")
public class TopicoController {

	@Autowired
	private TopicoRepository topicoRepository;
	@Autowired
	private CursoRepository cursoRepository;

//	Outra forma de fazer: @RequestMapping(value = "/topicos", method = RequestMethod.GET) 
	/*
	 * @GetMapping() public Page<TopicoDTO> lista(@RequestParam int
	 * pagina, @RequestParam int qtd, @RequestParam String ordenacao){ Pageable
	 * paginacao = PageRequest.of(pagina, qtd, Direction.ASC, ordenacao);
	 * Page<Topico> topicos = topicoRepository.findAll(paginacao); return
	 * TopicoDTO.converterObjToDTO(topicos); }
	 */
// -------- GET TODOS OS TOPICOS PAGINADO -----------
	@GetMapping()
	//- Boas práticas do cache pedem que seja utilizado naquelas tabelas que raramente sao atualizadas, evitando as tabelas que são atualizadas constantemente.
	@Cacheable(value = "ListaTodosTopicos")
	public Page<TopicoDTO> lista(@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) 
			Pageable paginacao){
		
		Page<Topico> topicos = topicoRepository.findAll(paginacao); 
		return TopicoDTO.converterObjToDTO(topicos); 
	}
	
	/*
	 * @GetMapping("/curso") public Page<TopicoDTO>
	 * listaPorCursoPaginada(@RequestParam(required = false) String
	 * nomeCurso, @RequestParam int pagina, @RequestParam int qtd) { Pageable
	 * paginacao = PageRequest.of(pagina, qtd);
	 * 
	 * if (nomeCurso == null) { Page<Topico> topicos =
	 * topicoRepository.findAll(paginacao); return
	 * TopicoDTO.converterObjToDTO(topicos); } else { Page<Topico> topicosFiltrados
	 * = topicoRepository.findByCurso_Nome(nomeCurso, paginacao); return
	 * TopicoDTO.converterObjToDTO(topicosFiltrados); } }
	 */
// -------- GET TOPICOS POR CURSO PAGINADO -----------	
	@GetMapping("/curso")
	public Page<TopicoDTO> listaPorCursoPaginada(@RequestParam(required = false) String nomeCurso, 
			@PageableDefault(sort = "titulo", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		
		if (nomeCurso == null) {
			Page<Topico> topicos = topicoRepository.findAll(paginacao);
			return TopicoDTO.converterObjToDTO(topicos);
		} else {
			Page<Topico> topicosFiltrados = topicoRepository.findByCurso_Nome(nomeCurso, paginacao);
			return TopicoDTO.converterObjToDTO(topicosFiltrados);
		}
	}
	
// -------- GET TOPICOS POR CATEGORIA PAGINADO -----------	
	@GetMapping("/categoria")
	public Page<TopicoDTO> listaPorCategoria(@RequestParam(required = false) String nomeCategoria, 
			@PageableDefault(sort = "titulo", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		
		if (nomeCategoria == null) {
			Page<Topico> topicos = topicoRepository.findAll(paginacao);
			return TopicoDTO.converterObjToDTO(topicos);
		} else {
			Page<Topico> topicosFiltrados = topicoRepository.carregarPorCategoriaDoCurso(nomeCategoria, paginacao);
			return TopicoDTO.converterObjToDTO(topicosFiltrados);
		}
	}
// -------- GET TOPICOS -----------
	@GetMapping("/{id}")
// CASO QUERIA TRABALHAR COM UMA VARIAVEL COM NOME DIFERENTE DE ID, INDIQUE QUAL VARIAVEL NO PATH 	
//	public TopicoDTO buscaPorId (@PathVariable("id") Long codigo) {
	public ResponseEntity<TopicoDTO> buscaPorId (@PathVariable("id") Long id) {
		Optional<Topico> topico = topicoRepository.findById(id);
		if(topico.isPresent()) {
			return ResponseEntity.ok(new TopicoDTO(topico.get()));
		}
		return ResponseEntity.notFound().build();
		
	}
// -------- GET TOPICOS COM DETALHES -----------
	@GetMapping("detalhes/{id}")
	public ResponseEntity<DetalhesDoTopicoDTO> buscaDetalhesDoTopicoPorId (@PathVariable("id") Long id) {
		Optional<Topico> topico = topicoRepository.findById(id);
		if(topico.isPresent()) {
			return ResponseEntity.ok(new DetalhesDoTopicoDTO(topico.get()));
		}
		return ResponseEntity.notFound().build();
	}
// -------- POST TOPICO -----------
	@PostMapping
	@Transactional
	//- Boas práticas do cache pedem que seja utilizado naquelas tabelas que raramente sao atualizadas, evitando as tabelas que são atualizadas constantemente.
	@CacheEvict(value = "ListaTodosTopicos", allEntries = true)
	public ResponseEntity<TopicoDTO> cadastrarTopico(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.converterFormToObj(cursoRepository);
		topicoRepository.save(topico);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}
// -------- PUT TOPICO -----------
	@PutMapping("editar/{id}")
	@Transactional
	//- Boas práticas do cache pedem que seja utilizado naquelas tabelas que raramente sao atualizadas, evitando as tabelas que são atualizadas constantemente.
	@CacheEvict(value = "ListaTodosTopicos", allEntries = true)
	public ResponseEntity<TopicoDTO> atualizar(@PathVariable("id") Long id, @RequestBody @Valid TopicoEditarForm form){
		Optional<Topico> topico = topicoRepository.findById(id);
		if(topico.isPresent()) {
			return ResponseEntity.ok(new TopicoDTO(topico.get()));
		}
		return ResponseEntity.notFound().build();
	}
// -------- DELETAR TOPICO -----------
	@DeleteMapping("deletar/{id}")
	@Transactional
	//- Boas práticas do cache pedem que seja utilizado naquelas tabelas que raramente sao atualizadas, evitando as tabelas que são atualizadas constantemente.
	@CacheEvict(value = "ListaTodosTopicos", allEntries = true)
	public ResponseEntity<?> deletar(@PathVariable("id") Long id){
		Optional<Topico> topico = topicoRepository.findById(id);
		if(topico.isPresent()) {
			topicoRepository.delete(topico.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
