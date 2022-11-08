package br.edu.unicesumar.foodhub.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.unicesumar.foodhub.base.CrudService;
import br.edu.unicesumar.foodhub.domain.Produto;
import br.edu.unicesumar.foodhub.domain.Users;
import br.edu.unicesumar.foodhub.repository.ProdutoRepository;
import br.edu.unicesumar.foodhub.repository.RestauranteRepository;

@Service
public class ProdutoService extends CrudService<Produto> {

	@Autowired
	private UsersService usersService;

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	public Boolean atualizarDisponibilidadeProduto(Long idProduto) {
		validRestaurante();

		Optional<Produto> produtoOpt = produtoRepository.findById(idProduto);

		if (produtoOpt.isPresent()) {
			Produto produto = produtoOpt.get();
			produto.setAtivo(!produto.getAtivo());
			produtoRepository.save(produto);
			return produto.getAtivo();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado");
		}

	}

	private void validRestaurante() {
		Users userLogado = usersService.findUsersLogado();
		if (restauranteRepository.findRestauranteByUsersUsername(userLogado.getUsername()).isEmpty()) {
			throw new AuthorizationServiceException("O Usuario não tem Permissão de atualizar pedidos.");
		}
	}
}
