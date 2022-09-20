package br.edu.unicesumar.foodhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unicesumar.foodhub.domain.StatusPedido;

public interface StatusPedidoRepository extends JpaRepository<StatusPedido, Long> {

}