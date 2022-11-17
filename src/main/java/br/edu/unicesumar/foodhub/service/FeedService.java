package br.edu.unicesumar.foodhub.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unicesumar.foodhub.domain.Produto;
import br.edu.unicesumar.foodhub.domain.Restaurante;
import br.edu.unicesumar.foodhub.dto.FeedDTO;
import br.edu.unicesumar.foodhub.repository.ProdutoRepository;

@Service
public class FeedService {

	@Autowired
	private RestauranteService restauranteService;

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<FeedDTO> getFeed() {

		List<FeedDTO> feed = new ArrayList<>();

		List<Restaurante> restaurantes = restauranteService.getRepository().findAll();

		restaurantes.stream().forEach(restaurante -> {
			List<Produto> produtos = produtoRepository.findProdutos(restaurante.getId());
			List<Produto> produtosComMidia = produtos.stream().filter(produto -> !produto.getMidias().isEmpty())
					.collect(Collectors.toList());
			if (!produtosComMidia.isEmpty()) {
				feed.add(FeedDTO.of(restaurante.getId(), produtosComMidia));
			}
		});

		return feed;
	}

}
