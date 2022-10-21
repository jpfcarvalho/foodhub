package br.edu.unicesumar.foodhub.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import br.edu.unicesumar.foodhub.base.CrudService;
import br.edu.unicesumar.foodhub.domain.Pedido;
import br.edu.unicesumar.foodhub.domain.enuns.StatusPedido;
import br.edu.unicesumar.foodhub.repository.StatusPedidoRepository;

@Service
public class PedidoService extends CrudService<Pedido> {

	private static Long UM_LONG = 1L;

	@Autowired
	private StatusPedidoRepository statusPedidoRepository;

	public void atualizarStatusPedido(Long idPedido) {

		Optional<Pedido> pedidoOpt = getRepository().findById(idPedido);
		pedidoOpt.ifPresentOrElse(pedido -> {
			if (pedido.getStatusPedido().getId().equals(StatusPedido.FINALIZADO.getValue())) {
				throw new IllegalArgumentException("Pedido já foi finalizado");
			}

			pedido.setStatusPedido(statusPedidoRepository.getOne(pedido.getStatusPedido().getId() + UM_LONG));
			getRepository().save(pedido);

		}, () -> {
			throw new NotFoundException("Pedido não encontrado");
		});

	}

}
