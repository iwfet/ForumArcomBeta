package com.forumArcom.forumArcom.controller;

import java.beans.Transient;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.forumArcom.forumArcom.controller.dto.DetalhesDoTopicoDto;
import com.forumArcom.forumArcom.controller.dto.TopicoDto;
import com.forumArcom.forumArcom.controller.form.AtualizacaoTopicoForm;
import com.forumArcom.forumArcom.controller.form.TopicoForm;
import com.forumArcom.forumArcom.modelo.Topico;
import com.forumArcom.forumArcom.repository.CursoRepository;
import com.forumArcom.forumArcom.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
@RequestMapping("/topicos")
public class TopicosController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	public List<TopicoDto> lista(String nomeCurso) {
		List<Topico> topicos;
		if (nomeCurso == null) {
			topicos = topicoRepository.findAll();
		} else {
			topicos = topicoRepository.findByCursoNome(nomeCurso);
		}
		return TopicoDto.converter(topicos);
	}
	
	@PostMapping
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable Long id){
		final Optional<Topico> byId = topicoRepository.findById(id);
		return byId.map(
				topico -> ResponseEntity.ok(new DetalhesDoTopicoDto(topico)))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}


	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id,@RequestBody @Valid AtualizacaoTopicoForm form){
		final Optional<Topico> byId = topicoRepository.findById(id);
		if(byId.isPresent()){
			Topico topico = form.atualizar(id,topicoRepository);
			return  ResponseEntity.ok(new TopicoDto(topico));
		}
		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		final Optional<Topico> byId = topicoRepository.findById(id);
		if (byId.isPresent()) {
			topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}


}
