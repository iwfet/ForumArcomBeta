package com.forumArcom.forumArcom.repository;

import java.util.List;

import com.forumArcom.forumArcom.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TopicoRepository extends JpaRepository<Topico, Long> {

	List<Topico> findByCursoNome(String nomeCurso);

}
