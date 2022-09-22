package br.edu.unicesumar.foodhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unicesumar.foodhub.domain.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
