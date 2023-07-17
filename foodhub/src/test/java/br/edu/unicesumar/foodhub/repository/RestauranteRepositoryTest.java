package br.edu.unicesumar.foodhub.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import br.edu.unicesumar.foodhub.Fixtures;
import br.edu.unicesumar.foodhub.domain.Restaurante;
import br.edu.unicesumar.foodhub.service.UsersService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Rollback
public class RestauranteRepositoryTest {

	@Autowired
	private RestauranteRepository repository;

	@Autowired
	private UsersService usersService;

	@Autowired
	private EntityManager em;

	@Test
	public void insertRestaurante() {

		Restaurante result = repository.save(Fixtures.createRestaurante(1L));

		assertThat(result, notNullValue());
		assertThat(result.getId(), notNullValue());
		assertThat(result.getNomeFantasia(), equalTo("Restaurante Teste"));
		assertThat(result.getCpfCnpj(), equalTo("99999999999"));
		assertThat(result.getTelefone(), equalTo("44999999999"));
		assertThat(result.getEndereco(), notNullValue());
		assertThat(result.getCategoria(), notNullValue());
		assertThat(result.getUsers(), notNullValue());
		assertThat(result.getFuncionamento(), notNullValue());
	}

	@Test
	public void UpdateRestaurante() {

		Restaurante restaurante = Fixtures.createRestaurante(1L);
		restaurante.setUsers(usersService.signUp(Fixtures.createUsers(1L)));

		Restaurante saved = repository.save(restaurante);
		saved.setNomeFantasia("Restaurante Atualizado");

		Restaurante result = repository.save(saved);

		assertThat(result, notNullValue());
		assertThat(result.getId(), equalTo(saved.getId()));
		assertThat(result.getNomeFantasia(), equalTo("Restaurante Atualizado"));
		assertThat(result.getCpfCnpj(), equalTo("99999999999"));
		assertThat(result.getTelefone(), equalTo("44999999999"));
		assertThat(result.getEndereco(), notNullValue());
		assertThat(result.getCategoria(), notNullValue());
		assertThat(result.getUsers(), notNullValue());
		assertThat(result.getFuncionamento(), notNullValue());
	}

	@Test
	public void deleteRestaurante() {

		Restaurante saved = repository.save(Fixtures.createRestaurante(1L));

		Restaurante beforeDelete = em.find(Restaurante.class, saved.getId());

		assertThat(beforeDelete, notNullValue());

		repository.deleteById(saved.getId());
		Restaurante afterDelete = em.find(Restaurante.class, saved.getId());

		assertThat(afterDelete, nullValue());

	}

	@Test
	public void findByIdRestaurante() {

		Restaurante saved = repository.save(Fixtures.createRestaurante(1L));

		Restaurante result = repository.findById(saved.getId()).get();

		assertThat(result, notNullValue());
		assertThat(result.getNomeFantasia(), equalTo("Restaurante Teste"));
		assertThat(result.getCpfCnpj(), equalTo("99999999999"));
		assertThat(result.getTelefone(), equalTo("44999999999"));
		assertThat(result.getEndereco(), notNullValue());
		assertThat(result.getCategoria(), notNullValue());
		assertThat(result.getUsers(), notNullValue());
		assertThat(result.getFuncionamento(), notNullValue());

	}

	@Test
	public void findAllRestaurante() {

		Restaurante restaurante = Fixtures.createRestaurante(1L);
		restaurante.setUsers(usersService.signUp(Fixtures.createUsers(1L)));

		Restaurante restaurante2 = Fixtures.createRestaurante(2L);
		restaurante2.setUsers(usersService.signUp(Fixtures.createUsers(2L)));

		repository.saveAll(List.of(restaurante, restaurante2));

		List<Restaurante> result = repository.findAll();

		assertThat(result, notNullValue());
		assertThat(result.size(), is(2));

	}

}
