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

		Produto produto = Fixtures.createProduto(1L);
		produto.getGruposComplementos().add(Fixtures.createGrupoComplemento(1L));

		Produto result = repository.save(produto);

		assertThat(result, notNullValue());
		assertThat(result.getId(), notNullValue());
		assertThat(result.getNome(), equalTo("Produto Teste"));
		assertThat(result.getValor(), equalTo(BigDecimal.TEN));
		assertThat(result.getAtivo(), equalTo(Boolean.TRUE));
		assertThat(result.getGrupo(), notNullValue());
		assertThat(result.getMidias(), notNullValue());
		assertThat(result.getGruposComplementos(), notNullValue());
		assertThat(result.getGruposComplementos().size(), is(1));
		assertThat(result.getGruposComplementos().get(0).getId(), notNullValue());
		assertThat(result.getGruposComplementos().get(0).getNome(), equalTo("Grupo Complemento Teste"));
		assertThat(result.getGruposComplementos().get(0).getObrigatorio(), equalTo(Boolean.TRUE));
		assertThat(result.getGruposComplementos().get(0).getQuantidadeMinima(), equalTo(1L));
		assertThat(result.getGruposComplementos().get(0).getQuantidadeMaxima(), equalTo(3L));
		assertThat(result.getGruposComplementos().get(0).getProdutosComplementos(), notNullValue());
		assertThat(result.getGruposComplementos().get(0).getProdutosComplementos().size(), is(1));
		assertThat(result.getGruposComplementos().get(0).getProdutosComplementos().get(0).getId(), notNullValue());
		assertThat(result.getGruposComplementos().get(0).getProdutosComplementos().get(0).getNome(),
				equalTo("Complemento Teste"));
		assertThat(result.getGruposComplementos().get(0).getProdutosComplementos().get(0).getQuantidadeMinima(),
				equalTo(1L));
		assertThat(result.getGruposComplementos().get(0).getProdutosComplementos().get(0).getQuantidadeMaxima(),
				equalTo(10L));
		assertThat(result.getGruposComplementos().get(0).getProdutosComplementos().get(0).getValor(),
				equalTo(BigDecimal.ONE));
	}

	@Test
	public void UpdateProduto() {

		Produto produto = Fixtures.createProduto(1L);
		produto.getGruposComplementos().add(Fixtures.createGrupoComplemento(1L));

		Produto saved = repository.save(produto);
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
		assertThat(result.getGruposComplementos().size(), is(1));
		assertThat(result.getGruposComplementos().get(0).getId(), notNullValue());
		assertThat(result.getGruposComplementos().get(0).getNome(), equalTo("Grupo Complemento Teste"));
		assertThat(result.getGruposComplementos().get(0).getObrigatorio(), equalTo(Boolean.TRUE));
		assertThat(result.getGruposComplementos().get(0).getQuantidadeMinima(), equalTo(1L));
		assertThat(result.getGruposComplementos().get(0).getQuantidadeMaxima(), equalTo(3L));
		assertThat(result.getGruposComplementos().get(0).getProdutosComplementos(), notNullValue());
		assertThat(result.getGruposComplementos().get(0).getProdutosComplementos().size(), is(1));
		assertThat(result.getGruposComplementos().get(0).getProdutosComplementos().get(0).getId(), notNullValue());
		assertThat(result.getGruposComplementos().get(0).getProdutosComplementos().get(0).getNome(),
				equalTo("Complemento Teste"));
		assertThat(result.getGruposComplementos().get(0).getProdutosComplementos().get(0).getQuantidadeMinima(),
				equalTo(1L));
		assertThat(result.getGruposComplementos().get(0).getProdutosComplementos().get(0).getQuantidadeMaxima(),
				equalTo(10L));
		assertThat(result.getGruposComplementos().get(0).getProdutosComplementos().get(0).getValor(),
				equalTo(BigDecimal.ONE));
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

		Produto produto = Fixtures.createProduto(1L);
		produto.getGruposComplementos().add(Fixtures.createGrupoComplemento(1L));

		Produto saved = repository.save(produto);

		Produto result = repository.findById(saved.getId()).get();

		assertThat(result, notNullValue());
		assertThat(result.getId(), notNullValue());
		assertThat(result.getNome(), equalTo("Produto Teste"));
		assertThat(result.getValor(), equalTo(BigDecimal.TEN));
		assertThat(result.getAtivo(), equalTo(Boolean.TRUE));
		assertThat(result.getGrupo(), notNullValue());
		assertThat(result.getMidias(), notNullValue());
		assertThat(result.getGruposComplementos(), notNullValue());
		assertThat(result.getGruposComplementos().size(), is(1));
		assertThat(result.getGruposComplementos().get(0).getId(), notNullValue());
		assertThat(result.getGruposComplementos().get(0).getNome(), equalTo("Grupo Complemento Teste"));
		assertThat(result.getGruposComplementos().get(0).getObrigatorio(), equalTo(Boolean.TRUE));
		assertThat(result.getGruposComplementos().get(0).getQuantidadeMinima(), equalTo(1L));
		assertThat(result.getGruposComplementos().get(0).getQuantidadeMaxima(), equalTo(3L));
		assertThat(result.getGruposComplementos().get(0).getProdutosComplementos(), notNullValue());
		assertThat(result.getGruposComplementos().get(0).getProdutosComplementos().size(), is(1));
		assertThat(result.getGruposComplementos().get(0).getProdutosComplementos().get(0).getId(), notNullValue());
		assertThat(result.getGruposComplementos().get(0).getProdutosComplementos().get(0).getNome(),
				equalTo("Complemento Teste"));
		assertThat(result.getGruposComplementos().get(0).getProdutosComplementos().get(0).getQuantidadeMinima(),
				equalTo(1L));
		assertThat(result.getGruposComplementos().get(0).getProdutosComplementos().get(0).getQuantidadeMaxima(),
				equalTo(10L));
		assertThat(result.getGruposComplementos().get(0).getProdutosComplementos().get(0).getValor(),
				equalTo(BigDecimal.ONE));

	}

	@Test
	public void findAllProduto() {

		Produto produto = Fixtures.createProduto(1L);
		produto.getGruposComplementos().add(Fixtures.createGrupoComplemento(1L));
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

	@Test
	public void addGrupoComplemento() {

		Produto produto = Fixtures.createProduto(1L);
		produto.getGruposComplementos().add(Fixtures.createGrupoComplemento(1L));
		Restaurante restautante = Fixtures.createRestaurante(1L);
		restautante.setUsers(usersService.signUp(Fixtures.createUsers(1L)));
		Grupo grupo = Fixtures.createGrupo(1L);
		grupo.setRestaurante(restauranteRepository.save(restautante));
		produto.setGrupo(grupoRepository.save(grupo));

		Produto saved = repository.save(produto);

		Produto beforeAdd = em.find(Produto.class, saved.getId());

		assertThat(beforeAdd, notNullValue());
		assertThat(beforeAdd.getGruposComplementos(), notNullValue());
		assertThat(beforeAdd.getGruposComplementos().size(), is(1));
		assertThat(beforeAdd.getGruposComplementos().get(0).getId(), notNullValue());
		assertThat(beforeAdd.getGruposComplementos().get(0).getNome(), equalTo("Grupo Complemento Teste"));
		assertThat(beforeAdd.getGruposComplementos().get(0).getObrigatorio(), equalTo(Boolean.TRUE));
		assertThat(beforeAdd.getGruposComplementos().get(0).getQuantidadeMinima(), equalTo(1L));
		assertThat(beforeAdd.getGruposComplementos().get(0).getQuantidadeMaxima(), equalTo(3L));
		assertThat(beforeAdd.getGruposComplementos().get(0).getProdutosComplementos(), notNullValue());
		assertThat(beforeAdd.getGruposComplementos().get(0).getProdutosComplementos().size(), is(1));
		assertThat(beforeAdd.getGruposComplementos().get(0).getProdutosComplementos().get(0).getId(), notNullValue());
		assertThat(beforeAdd.getGruposComplementos().get(0).getProdutosComplementos().get(0).getNome(),
				equalTo("Complemento Teste"));
		assertThat(beforeAdd.getGruposComplementos().get(0).getProdutosComplementos().get(0).getQuantidadeMinima(),
				equalTo(1L));
		assertThat(beforeAdd.getGruposComplementos().get(0).getProdutosComplementos().get(0).getQuantidadeMaxima(),
				equalTo(10L));
		assertThat(beforeAdd.getGruposComplementos().get(0).getProdutosComplementos().get(0).getValor(),
				equalTo(BigDecimal.ONE));

		beforeAdd.getGruposComplementos().add(Fixtures.createGrupoComplemento(2L));

		repository.save(beforeAdd);

		Produto afterAdd = em.find(Produto.class, saved.getId());

		assertThat(afterAdd, notNullValue());
		assertThat(afterAdd.getGruposComplementos(), notNullValue());
		assertThat(afterAdd.getGruposComplementos().size(), is(2));

	}

	@Test
	public void deleteGrupoComplemento() {

		Produto produto = Fixtures.createProduto(1L);
		produto.getGruposComplementos().add(Fixtures.createGrupoComplemento(1L));
		Restaurante restautante = Fixtures.createRestaurante(1L);
		restautante.setUsers(usersService.signUp(Fixtures.createUsers(1L)));
		Grupo grupo = Fixtures.createGrupo(1L);
		grupo.setRestaurante(restauranteRepository.save(restautante));
		produto.setGrupo(grupoRepository.save(grupo));

		Produto saved = repository.save(produto);

		Produto beforeDelete = em.find(Produto.class, saved.getId());

		assertThat(beforeDelete, notNullValue());
		assertThat(beforeDelete.getGruposComplementos(), notNullValue());
		assertThat(beforeDelete.getGruposComplementos().size(), is(1));
		assertThat(beforeDelete.getGruposComplementos().get(0).getId(), notNullValue());
		assertThat(beforeDelete.getGruposComplementos().get(0).getNome(), equalTo("Grupo Complemento Teste"));
		assertThat(beforeDelete.getGruposComplementos().get(0).getObrigatorio(), equalTo(Boolean.TRUE));
		assertThat(beforeDelete.getGruposComplementos().get(0).getQuantidadeMinima(), equalTo(1L));
		assertThat(beforeDelete.getGruposComplementos().get(0).getQuantidadeMaxima(), equalTo(3L));
		assertThat(beforeDelete.getGruposComplementos().get(0).getProdutosComplementos(), notNullValue());
		assertThat(beforeDelete.getGruposComplementos().get(0).getProdutosComplementos().size(), is(1));
		assertThat(beforeDelete.getGruposComplementos().get(0).getProdutosComplementos().get(0).getId(),
				notNullValue());
		assertThat(beforeDelete.getGruposComplementos().get(0).getProdutosComplementos().get(0).getNome(),
				equalTo("Complemento Teste"));
		assertThat(beforeDelete.getGruposComplementos().get(0).getProdutosComplementos().get(0).getQuantidadeMinima(),
				equalTo(1L));
		assertThat(beforeDelete.getGruposComplementos().get(0).getProdutosComplementos().get(0).getQuantidadeMaxima(),
				equalTo(10L));
		assertThat(beforeDelete.getGruposComplementos().get(0).getProdutosComplementos().get(0).getValor(),
				equalTo(BigDecimal.ONE));

		beforeDelete.getGruposComplementos().clear();

		repository.save(beforeDelete);

		Produto afterDelete = em.find(Produto.class, saved.getId());

		assertThat(afterDelete, notNullValue());
		assertThat(afterDelete.getGruposComplementos(), notNullValue());
		assertThat(afterDelete.getGruposComplementos().size(), is(0));

	}

	@Test
	public void addProdutoComplemento() {

		Produto produto = Fixtures.createProduto(1L);
		produto.getGruposComplementos().add(Fixtures.createGrupoComplemento(1L));
		Restaurante restautante = Fixtures.createRestaurante(1L);
		restautante.setUsers(usersService.signUp(Fixtures.createUsers(1L)));
		Grupo grupo = Fixtures.createGrupo(1L);
		grupo.setRestaurante(restauranteRepository.save(restautante));
		produto.setGrupo(grupoRepository.save(grupo));

		Produto saved = repository.save(produto);

		Produto beforeAdd = em.find(Produto.class, saved.getId());

		assertThat(beforeAdd.getGruposComplementos().get(0).getProdutosComplementos(), notNullValue());
		assertThat(beforeAdd.getGruposComplementos().get(0).getProdutosComplementos().size(), is(1));
		assertThat(beforeAdd.getGruposComplementos().get(0).getProdutosComplementos().get(0).getId(), notNullValue());
		assertThat(beforeAdd.getGruposComplementos().get(0).getProdutosComplementos().get(0).getNome(),
				equalTo("Complemento Teste"));
		assertThat(beforeAdd.getGruposComplementos().get(0).getProdutosComplementos().get(0).getQuantidadeMinima(),
				equalTo(1L));
		assertThat(beforeAdd.getGruposComplementos().get(0).getProdutosComplementos().get(0).getQuantidadeMaxima(),
				equalTo(10L));
		assertThat(beforeAdd.getGruposComplementos().get(0).getProdutosComplementos().get(0).getValor(),
				equalTo(BigDecimal.ONE));

		beforeAdd.getGruposComplementos().get(0).getProdutosComplementos().add(Fixtures.createProdutoComplemento(2L));

		repository.save(beforeAdd);

		Produto afterAdd = em.find(Produto.class, saved.getId());

		assertThat(afterAdd, notNullValue());
		assertThat(beforeAdd.getGruposComplementos().get(0).getProdutosComplementos(), notNullValue());
		assertThat(beforeAdd.getGruposComplementos().get(0).getProdutosComplementos().size(), is(2));

	}

	@Test
	public void deleteProdutoComplemento() {

		Produto produto = Fixtures.createProduto(1L);
		produto.getGruposComplementos().add(Fixtures.createGrupoComplemento(1L));
		produto.getGruposComplementos().get(0).getProdutosComplementos().add(Fixtures.createProdutoComplemento(2L));
		Restaurante restautante = Fixtures.createRestaurante(1L);
		restautante.setUsers(usersService.signUp(Fixtures.createUsers(1L)));
		Grupo grupo = Fixtures.createGrupo(1L);
		grupo.setRestaurante(restauranteRepository.save(restautante));
		produto.setGrupo(grupoRepository.save(grupo));

		Produto saved = repository.save(produto);

		Produto beforeDelete = em.find(Produto.class, saved.getId());

		assertThat(beforeDelete, notNullValue());
		assertThat(beforeDelete.getGruposComplementos().get(0).getProdutosComplementos(), notNullValue());
		assertThat(beforeDelete.getGruposComplementos().get(0).getProdutosComplementos().size(), is(2));
		assertThat(beforeDelete.getGruposComplementos().get(0).getProdutosComplementos().get(0).getId(),
				notNullValue());
		assertThat(beforeDelete.getGruposComplementos().get(0).getProdutosComplementos().get(0).getNome(),
				equalTo("Complemento Teste"));
		assertThat(beforeDelete.getGruposComplementos().get(0).getProdutosComplementos().get(0).getQuantidadeMinima(),
				equalTo(1L));
		assertThat(beforeDelete.getGruposComplementos().get(0).getProdutosComplementos().get(0).getQuantidadeMaxima(),
				equalTo(10L));
		assertThat(beforeDelete.getGruposComplementos().get(0).getProdutosComplementos().get(0).getValor(),
				equalTo(BigDecimal.ONE));

		beforeDelete.getGruposComplementos().get(0).getProdutosComplementos().clear();

		repository.save(beforeDelete);

		Produto afterDelete = em.find(Produto.class, saved.getId());

		assertThat(afterDelete, notNullValue());
		assertThat(beforeDelete.getGruposComplementos().get(0).getProdutosComplementos(), notNullValue());
		assertThat(beforeDelete.getGruposComplementos().get(0).getProdutosComplementos().size(), is(0));

	}

}
