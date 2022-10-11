package br.edu.unicesumar.foodhub.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import br.edu.unicesumar.foodhub.domain.StatusPedido;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Rollback
public class StatusPedidoRepositoryTest {

	@Autowired
	private StatusPedidoRepository repository;

	@Test
	public void findByIdStatusPedido() {

		StatusPedido result = repository.findById(1L).get();

		assertThat(result, notNullValue());
		assertThat(result.getStatus(), equalTo("Aberto"));

	}

	@Test
	public void findAllStatusPedido() {

		List<StatusPedido> result = repository.findAll();

		assertThat(result, notNullValue());
		assertThat(result.size(), is(4));

	}

}
