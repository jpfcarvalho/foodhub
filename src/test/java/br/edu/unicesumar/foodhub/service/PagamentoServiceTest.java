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

import br.edu.unicesumar.foodhub.domain.Pagamento;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@Rollback
public class PagamentoServiceTest {

	@Autowired
	private PagamentoService service;

	@Test
	public void salvarPagamento() {

		Pagamento entity = new Pagamento(-1L, "Pagamento token1", "Processando");

		Pagamento newEntity = service.save(entity);

		assertEquals(newEntity.getToken(), "Pagamento token1");

		service.getRepository().deleteAll();

	}

	@Test
	public void BuscarTodosPagamentos() {

		Pagamento entity1 = new Pagamento(-1L, "Pagamento token1", "Processando");
		Pagamento entity2 = new Pagamento(-2L, "Pagamento token2", "Cancelado");
		Pagamento entity3 = new Pagamento(-3L, "Pagamento token3", "Finalizado");

		service.getRepository().save(entity1);
		service.getRepository().save(entity2);
		service.getRepository().save(entity3);

		Page<Pagamento> entitys = service.findAll(Pageable.ofSize(2));

		assertEquals(entitys.getTotalElements(), 3L);
		assertEquals(entitys.getTotalPages(), 2);
		assertEquals(entitys.getContent().get(0).getToken(), "Pagamento token1");

		service.getRepository().deleteAll();
	}

	@Test
	public void DeletarPagamento() {

		Pagamento entity = new Pagamento(-1L, "Pagamento token1", "Processando");

		service.getRepository().save(entity);

		List<Pagamento> entitys = service.getRepository().findAll();
		assertEquals(entitys.size(), 1);

		service.deleteById(entitys.get(0).getId());

		entitys = service.getRepository().findAll();
		assertEquals(entitys.size(), 0);
	}
}
