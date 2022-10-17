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
import br.edu.unicesumar.foodhub.domain.Pessoa;
import br.edu.unicesumar.foodhub.service.UsersService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Rollback
public class PessoaRepositoryTest {

	@Autowired
	private PessoaRepository repository;

	@Autowired
	private UsersService usersService;

	@Autowired
	private EntityManager em;

	@Test
	public void insertPessoa() {

		Pessoa pessoa = Fixtures.createPessoa(1L);
		pessoa.setUsers(usersService.signUp(Fixtures.createUsers(1L)));

		Pessoa result = repository.save(pessoa);

		assertThat(result, notNullValue());
		assertThat(result.getId(), notNullValue());
		assertThat(result.getNome(), equalTo("Pessoa Teste"));
		assertThat(result.getSobrenome(), equalTo("Sobrenome Teste"));
		assertThat(result.getCpf(), equalTo("87251052059"));
		assertThat(result.getTelefone(), equalTo("44999999999"));
		assertThat(result.getEnderecos(), notNullValue());
		assertThat(result.getEnderecos().size(), is(1));
		assertThat(result.getUsers(), notNullValue());

	}

	@Test
	public void UpdatePessoa() {

		Pessoa pessoa = Fixtures.createPessoa(1L);
		pessoa.setUsers(usersService.signUp(Fixtures.createUsers(1L)));

		Pessoa saved = repository.save(pessoa);
		saved.setNome("Pessoa Atualizada");

		Pessoa result = repository.save(saved);

		assertThat(result, notNullValue());
		assertThat(result.getId(), notNullValue());
		assertThat(result.getNome(), equalTo("Pessoa Atualizada"));
		assertThat(result.getSobrenome(), equalTo("Sobrenome Teste"));
		assertThat(result.getCpf(), equalTo("87251052059"));
		assertThat(result.getTelefone(), equalTo("44999999999"));
		assertThat(result.getEnderecos(), notNullValue());
		assertThat(result.getEnderecos().size(), is(1));
		assertThat(result.getUsers(), notNullValue());
	}

	@Test
	public void deletePessoa() {

		Pessoa pessoa = Fixtures.createPessoa(1L);
		pessoa.setUsers(usersService.signUp(Fixtures.createUsers(1L)));

		Pessoa saved = repository.save(pessoa);

		Pessoa beforeDelete = em.find(Pessoa.class, saved.getId());

		assertThat(beforeDelete, notNullValue());

		repository.deleteById(saved.getId());
		Pessoa afterDelete = em.find(Pessoa.class, saved.getId());

		assertThat(afterDelete, nullValue());

	}

	@Test
	public void findByIdPessoa() {

		Pessoa pessoa = Fixtures.createPessoa(1L);
		pessoa.setUsers(usersService.signUp(Fixtures.createUsers(1L)));

		Pessoa saved = repository.save(pessoa);

		Pessoa result = repository.findById(saved.getId()).get();

		assertThat(result, notNullValue());
		assertThat(result.getId(), notNullValue());
		assertThat(result.getNome(), equalTo("Pessoa Teste"));
		assertThat(result.getSobrenome(), equalTo("Sobrenome Teste"));
		assertThat(result.getCpf(), equalTo("87251052059"));
		assertThat(result.getTelefone(), equalTo("44999999999"));
		assertThat(result.getEnderecos(), notNullValue());
		assertThat(result.getEnderecos().size(), is(1));
		assertThat(result.getUsers(), notNullValue());

	}

	@Test
	public void findAllPessoas() {

		Pessoa pessoa = Fixtures.createPessoa(1L);
		pessoa.setUsers(usersService.signUp(Fixtures.createUsers(1L)));

		Pessoa pessoa2 = Fixtures.createPessoa(2L);
		pessoa2.setUsers(usersService.signUp(Fixtures.createUsers(2L)));

		repository.saveAll(List.of(pessoa, pessoa2));

		List<Pessoa> result = repository.findAll();

		assertThat(result, notNullValue());
		assertThat(result.size(), is(2));

	}

	@Test
	public void addEndereco() {

		Pessoa pessoa = Fixtures.createPessoa(1L);
		pessoa.setUsers(usersService.signUp(Fixtures.createUsers(1L)));

		Pessoa saved = repository.save(pessoa);

		Pessoa beforeAdd = em.find(Pessoa.class, saved.getId());

		assertThat(beforeAdd.getEnderecos(), notNullValue());
		assertThat(beforeAdd.getEnderecos().size(), is(1));
		assertThat(beforeAdd.getEnderecos().get(0).getId(), notNullValue());
		assertThat(beforeAdd.getEnderecos().get(0).getNumero(), equalTo("1"));
		assertThat(beforeAdd.getEnderecos().get(0).getCep(), equalTo("87075444"));
		assertThat(beforeAdd.getEnderecos().get(0).getLougradouro(), equalTo("Rua Teste"));
		assertThat(beforeAdd.getEnderecos().get(0).getBairro(), equalTo("Bairro Teste"));
		assertThat(beforeAdd.getEnderecos().get(0).getCidade(), notNullValue());
		assertThat(beforeAdd.getEnderecos().get(0).getCoordenadas(), notNullValue());

		beforeAdd.getEnderecos().add(Fixtures.createEndereco(2L));

		repository.save(beforeAdd);

		Pessoa afterAdd = em.find(Pessoa.class, saved.getId());

		assertThat(afterAdd, notNullValue());
		assertThat(afterAdd.getEnderecos(), notNullValue());
		assertThat(afterAdd.getEnderecos().size(), is(2));

	}

	@Test
	public void deleteEndereco() {

		Pessoa pessoa = Fixtures.createPessoa(1L);
		pessoa.setUsers(usersService.signUp(Fixtures.createUsers(1L)));
		pessoa.getEnderecos().add(Fixtures.createEndereco(2L));

		Pessoa saved = repository.save(pessoa);

		Pessoa beforeDelete = em.find(Pessoa.class, saved.getId());

		assertThat(beforeDelete.getEnderecos(), notNullValue());
		assertThat(beforeDelete.getEnderecos().size(), is(2));

		beforeDelete.getEnderecos().clear();

		repository.save(beforeDelete);

		Pessoa afterDelete = em.find(Pessoa.class, saved.getId());

		assertThat(afterDelete, notNullValue());
		assertThat(afterDelete.getEnderecos(), notNullValue());
		assertThat(afterDelete.getEnderecos().size(), is(0));

	}

}
