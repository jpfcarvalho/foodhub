package br.edu.unicesumar.foodhub.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import br.edu.unicesumar.foodhub.config.auth.Roles;
import br.edu.unicesumar.foodhub.domain.Users;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Rollback
public class UsersRepositoryTest {

	@Autowired
	private UsersRepository repository;

	@Autowired
	private EntityManager em;

	@Test
	public void insertUsers() {

		Users user = new Users(null, Set.of(Roles.ROLE_CLIENTE), "Email@teste.com", "Username", "password");

		Users result = repository.save(user);

		assertThat(result, notNullValue());
		assertThat(result.getId(), notNullValue());
		assertThat(result.getEmail(), equalTo("Email@teste.com"));
		assertThat(result.getUsername(), equalTo("Username"));
		assertThat(result.getPassword(), equalTo("password"));
		assertThat(result.getRoles().size(), is(1));

	}

	@Test
	public void deleteUsers() {

		Users user = new Users(null, Set.of(Roles.ROLE_CLIENTE), "Email@teste.com", "Username", "password");
		Users saved = repository.save(user);

		Users beforeDelete = em.find(Users.class, saved.getId());

		assertThat(beforeDelete, notNullValue());

		repository.deleteById(saved.getId());
		Users afterDelete = em.find(Users.class, saved.getId());

		assertThat(afterDelete, nullValue());

	}

	@Test
	public void findByIdUsers() {

		Users user = new Users(null, Set.of(Roles.ROLE_CLIENTE), "Email@teste.com", "Username", "password");
		Users saved = repository.save(user);

		Users result = repository.findById(saved.getId()).get();

		assertThat(result, notNullValue());
		assertThat(result.getEmail(), equalTo("Email@teste.com"));
		assertThat(result.getUsername(), equalTo("Username"));
		assertThat(result.getPassword(), equalTo("password"));
		assertThat(result.getRoles().size(), is(1));

	}

	@Test
	public void findAllUsers() {

		Users user = new Users(null, Set.of(Roles.ROLE_CLIENTE), "Email@teste.com", "Username", "password");
		Users user2 = new Users(null, Set.of(Roles.ROLE_CLIENTE), "Email2@teste.com", "Username2", "password");
		repository.saveAll(List.of(user, user2));

		List<Users> result = repository.findAll();

		assertThat(result, notNullValue());
		assertThat(result.size(), is(2));

	}

}
