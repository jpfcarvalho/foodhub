package br.edu.unicesumar.foodhub.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import br.edu.unicesumar.foodhub.Fixtures;
import br.edu.unicesumar.foodhub.domain.Grupo;
import br.edu.unicesumar.foodhub.domain.Produto;
import br.edu.unicesumar.foodhub.domain.Restaurante;
import br.edu.unicesumar.foodhub.service.UsersService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Rollback
public class ProdutoRepositoryTest {

	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private UsersService usersService;

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private EntityManager em;

	@Test
	public void insertProduto() {

		Produto result = repository.save(Fixtures.createProduto(1L));

		assertThat(result, notNullValue());
		assertThat(result.getId(), notNullValue());
		assertThat(result.getNome(), equalTo("Produto Teste"));
		assertThat(result.getValor(), equalTo(BigDecimal.TEN));
		assertThat(result.getAtivo(), equalTo(Boolean.TRUE));
		assertThat(result.getGrupo(), notNullValue());
		assertThat(result.getMidias(), notNullValue());
		assertThat(result.getGruposComplementos(), notNullValue());
	}

	@Test
	public void UpdateProduto() {

		Produto saved = repository.save(Fixtures.createProduto(1L));
		saved.setNome("Produto Atualizado");

		Produto result = repository.save(saved);

		assertThat(result, notNullValue());
		assertThat(result.getId(), notNullValue());
		assertThat(result.getNome(), equalTo("Produto Atualizado"));
		assertThat(result.getValor(), equalTo(BigDecimal.TEN));
		assertThat(result.getAtivo(), equalTo(Boolean.TRUE));
		assertThat(result.getGrupo(), notNullValue());
		assertThat(result.getMidias(), notNullValue());
		assertThat(result.getGruposComplementos(), notNullValue());
	}

	@Test
	public void deleteProduto() {

		Produto saved = repository.save(Fixtures.createProduto(1L));

		Produto beforeDelete = em.find(Produto.class, saved.getId());

		assertThat(beforeDelete, notNullValue());

		repository.deleteById(saved.getId());
		Produto afterDelete = em.find(Produto.class, saved.getId());

		assertThat(afterDelete, nullValue());

	}

	@Test
	public void findByIdProduto() {

		Produto saved = repository.save(Fixtures.createProduto(1L));

		Produto result = repository.findById(saved.getId()).get();

		assertThat(result, notNullValue());
		assertThat(result.getId(), notNullValue());
		assertThat(result.getNome(), equalTo("Produto Teste"));
		assertThat(result.getValor(), equalTo(BigDecimal.TEN));
		assertThat(result.getAtivo(), equalTo(Boolean.TRUE));
		assertThat(result.getGrupo(), notNullValue());
		assertThat(result.getMidias(), notNullValue());
		assertThat(result.getGruposComplementos(), notNullValue());

	}

	@Test
	public void findAllProduto() {

		Produto produto = Fixtures.createProduto(1L);
		Restaurante restautante = Fixtures.createRestaurante(1L);
		restautante.setUsers(usersService.signUp(Fixtures.createUsers(1L)));
		Grupo grupo = Fixtures.createGrupo(1L);
		grupo.setRestaurante(restauranteRepository.save(restautante));
		produto.setGrupo(grupoRepository.save(grupo));

		Produto produto2 = Fixtures.createProduto(2L);
		Restaurante restautante2 = Fixtures.createRestaurante(2L);
		restautante2.setUsers(usersService.signUp(Fixtures.createUsers(2L)));
		Grupo grupo2 = Fixtures.createGrupo(2L);
		grupo2.setRestaurante(restauranteRepository.save(restautante2));
		produto2.setGrupo(grupoRepository.save(grupo2));

		repository.saveAll(List.of(produto, produto2));

		List<Produto> result = repository.findAll();

		assertThat(result, notNullValue());
		assertThat(result.size(), is(2));

	}

}
