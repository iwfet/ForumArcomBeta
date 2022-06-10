package com.forumArcom.forumArcom.repository;

import com.forumArcom.forumArcom.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nome);

}
