package br.edu.unicesumar.foodhub.service;

import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;

import br.edu.unicesumar.foodhub.base.CrudService;
import br.edu.unicesumar.foodhub.domain.Pedido;
import br.edu.unicesumar.foodhub.domain.PedidoProduto;
import br.edu.unicesumar.foodhub.domain.Produto;
import br.edu.unicesumar.foodhub.domain.ProdutoComplemento;
import br.edu.unicesumar.foodhub.domain.Users;
import br.edu.unicesumar.foodhub.domain.enuns.StatusPedido;
import br.edu.unicesumar.foodhub.repository.PessoaRepository;
import br.edu.unicesumar.foodhub.repository.ProdutoRepository;
import br.edu.unicesumar.foodhub.repository.StatusPedidoRepository;

@Service
public class PedidoService extends CrudService<Pedido> {

	private static Long UM_LONG = 1L;

	@Autowired
	private StatusPedidoRepository statusPedidoRepository;

	@Autowired
	private UsersService usersService;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EntityManager em;

	public void atualizarStatusPedido(Long idPedido, Boolean cancelar) {

		Optional<Pedido> pedidoOpt = getRepository().findById(idPedido);
		pedidoOpt.ifPresentOrElse(pedido -> {
			if (pedido.getStatusPedido().getId().equals(StatusPedido.FINALIZADO.getValue())) {
				throw new IllegalArgumentException("Pedido já foi finalizado");
			} else if (pedido.getStatusPedido().getId().equals(StatusPedido.RECUSADO.getValue())) {
				throw new IllegalArgumentException("Pedido foi recusado");
			}

			if (cancelar) {
				pedido.setStatusPedido(statusPedidoRepository.getOne(StatusPedido.RECUSADO.getValue()));
			} else {
				pedido.setStatusPedido(statusPedidoRepository.getOne(pedido.getStatusPedido().getId() + UM_LONG));
			}

			getRepository().save(pedido);

		}, () -> {
			throw new NotFoundException("Pedido não encontrado");
		});

	}

	@Override
	protected void beforeSave(Pedido entity) {
		setPrecoTotal(entity);
	}

	@Override
	protected void beforeInsert(Pedido entity) {
		validSetUser(entity);

		setValor(entity);

		setRestaurante(entity);

	}

	private void setRestaurante(Pedido entity) {
		produtoRepository
				.findRestaurante(entity.getProdutos().stream().map(p -> p.getProduto().getId()).findAny().orElse(0L))
				.ifPresent(restaurante -> {
					if (!entity.getProdutos().stream().map(p -> findObjeto(Produto.class, p.getProduto().getId()))
							.allMatch(produto -> produto.getGrupo().getRestaurante().equals(restaurante))) {
						throw new IllegalArgumentException("Existem produtos de restaurante diferentes.");
					}
					entity.setRestaurante(restaurante);
				});
	}

	private void setPrecoTotal(Pedido entity) {
		BigDecimal precoProdutos = entity.getProdutos().stream().map(PedidoProduto::getPrecoProduto)
				.reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

		BigDecimal precoComplementos = entity.getProdutos().stream().map(PedidoProduto::getPrecoComplementos)
				.reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

		entity.setPrecoTotal(precoProdutos.add(precoComplementos));
	}

	private void setValor(Pedido entity) {
		entity.getProdutos().forEach(produto -> {
			produto.getComplementos().forEach(complemento -> {
				complemento.setValorComplemento(
						findObjeto(ProdutoComplemento.class, complemento.getProdutoComplemento().getId()).getValor());
			});
			produto.setPrecoProduto(findObjeto(Produto.class, produto.getProduto().getId()).getValor());
		});
	}

	private void validSetUser(Pedido entity) {
		Users userLogado = usersService.findUsersLogado();
		pessoaRepository.findPessoaByUsersUsername(userLogado.getUsername()).ifPresentOrElse(pessoa -> {
			entity.setPessoa(pessoa);
			entity.getPagamento().setPessoa(pessoa);
		}, () -> {
			throw new AuthorizationServiceException("O Usuario não tem Permissão de realizar pedidos.");
		});
	}

	private <T> T findObjeto(Class<T> classBuscado, Long id) {

		return Optional.ofNullable(em.find(classBuscado, id))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado"));

	}

}
