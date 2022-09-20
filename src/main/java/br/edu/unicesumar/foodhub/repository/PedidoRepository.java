package br.edu.unicesumar.foodhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unicesumar.foodhub.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}