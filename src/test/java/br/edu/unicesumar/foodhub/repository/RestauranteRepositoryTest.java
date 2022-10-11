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
import br.edu.unicesumar.foodhub.domain.Users;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Rollback
public class RestauranteRepositoryTest {

	@Autowired
	private RestauranteRepository repository;

	@Autowired
	private EntityManager em;

	@Test
	public void insertRestaurante() {

		Restaurante result = repository.save(Fixtures.createRestaurante());

		assertThat(result, notNullValue());
		assertThat(result.getId(), notNullValue());
		assertThat(result.getNomeFantasia(), equalTo("Restaurante Teste"));
		assertThat(result.getCpfCnpj(), equalTo("9999999999"));
		assertThat(result.getTelefone(), equalTo("44999999999"));
		assertThat(result.getEndereco(), notNullValue());
		assertThat(result.getCategoria(), notNullValue());
		assertThat(result.getUsers(), notNullValue());
		assertThat(result.getFuncionamento(), notNullValue());
	}

	@Test
	public void deleteRestaurante() {

		Restaurante saved = repository.save(Fixtures.createRestaurante());

		Restaurante beforeDelete = em.find(Restaurante.class, saved.getId());

		assertThat(beforeDelete, notNullValue());

		repository.deleteById(saved.getId());
		Users afterDelete = em.find(Users.class, saved.getId());

		assertThat(afterDelete, nullValue());

	}

	@Test
	public void findByIdRestaurante() {

		Restaurante saved = repository.save(Fixtures.createRestaurante());

		Restaurante result = repository.findById(saved.getId()).get();

		assertThat(result, notNullValue());
		assertThat(result.getNomeFantasia(), equalTo("Restaurante Teste"));
		assertThat(result.getCpfCnpj(), equalTo("9999999999"));
		assertThat(result.getTelefone(), equalTo("44999999999"));
		assertThat(result.getEndereco(), notNullValue());
		assertThat(result.getCategoria(), notNullValue());
		assertThat(result.getUsers(), notNullValue());
		assertThat(result.getFuncionamento(), notNullValue());

	}

	@Test
	public void findAllRestaurante() {

		Restaurante restaurante = Fixtures.createRestaurante(1L);
		Restaurante restaurante2 = Fixtures.createRestaurante(2L);
		repository.saveAll(List.of(restaurante, restaurante2));

		List<Restaurante> result = repository.findAll();

		assertThat(result, notNullValue());
		assertThat(result.size(), is(2));

	}

}
