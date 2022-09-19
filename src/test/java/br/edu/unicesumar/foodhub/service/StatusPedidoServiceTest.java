package br.edu.unicesumar.foodhub.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import br.edu.unicesumar.foodhub.domain.StatusPedido;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@Rollback
public class StatusPedidoServiceTest {
	@Autowired
	private StatusPedidoService service;

	@Test
	public void salvarStatusPedido() {

		StatusPedido entity = new StatusPedido(-1L, "StatusPedido teste1");

		StatusPedido newEntity = service.save(entity);

		assertEquals(newEntity.getStatus(), "StatusPedido teste1");

		service.getRepository().deleteAll();

	}

	@Test
	public void BuscarTodosStatusPedidos() {

		StatusPedido entity1 = new StatusPedido(-1L, "StatusPedido teste1");
		StatusPedido entity2 = new StatusPedido(-2L, "StatusPedido teste2");
		StatusPedido entity3 = new StatusPedido(-3L, "StatusPedido teste3");

		service.getRepository().save(entity1);
		service.getRepository().save(entity2);
		service.getRepository().save(entity3);

		Page<StatusPedido> entitys = service.findAll(Pageable.ofSize(2));

		assertEquals(entitys.getTotalElements(), 3L);
		assertEquals(entitys.getTotalPages(), 2);
		assertEquals(entitys.getContent().get(0).getStatus(), "StatusPedido teste1");

		service.getRepository().deleteAll();
	}

	@Test
	public void DeletarStatusPedido() {

		StatusPedido entity = new StatusPedido(-3L, "StatusPedido teste3");

		service.getRepository().save(entity);

		List<StatusPedido> entitys = service.getRepository().findAll();
		assertEquals(entitys.size(), 1);

		service.deleteById(entitys.get(0).getId());

		entitys = service.getRepository().findAll();
		assertEquals(entitys.size(), 0);
	}
}
