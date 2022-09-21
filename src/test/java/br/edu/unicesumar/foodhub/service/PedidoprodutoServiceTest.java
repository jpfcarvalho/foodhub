package br.edu.unicesumar.foodhub.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
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

import br.edu.unicesumar.foodhub.domain.PedidoProduto;
import br.edu.unicesumar.foodhub.domain.Produto;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@Rollback
public class PedidoprodutoServiceTest {
	@Autowired
	private PedidoProdutoService service;

	@Test
	public void salvarPedidoProduto() {

		Produto produto = new Produto(-1L, "Produto teste", "Descrição produto", BigDecimal.valueOf(29), Boolean.TRUE,
				null);

		PedidoProduto entity = new PedidoProduto(-1L, BigDecimal.valueOf(30), produto, null);

		PedidoProduto newEntity = service.save(entity);

		// assertEquals(newEntity.getPrecoProduto(), "Complemento teste");

		service.getRepository().deleteAll();

	}

	@Test
	public void BuscarTodosProdutosComplemento() {

		Produto produto1 = new Produto(-1L, "Produto teste1", "Descrição produto1", BigDecimal.valueOf(10),
				Boolean.TRUE, null);
		PedidoProduto entity1 = new PedidoProduto(-1L, BigDecimal.valueOf(30), produto1, null);

		Produto produto2 = new Produto(-1L, "Produto teste2", "Descrição produto2", BigDecimal.valueOf(9), Boolean.TRUE,
				null);
		PedidoProduto entity2 = new PedidoProduto(-1L, BigDecimal.valueOf(30), produto2, null);

		Produto produto3 = new Produto(-1L, "Produto teste3", "Descrição produto3", BigDecimal.valueOf(29),
				Boolean.TRUE, null);
		PedidoProduto entity3 = new PedidoProduto(-1L, BigDecimal.valueOf(30), produto3, null);

		service.getRepository().save(entity1);
		service.getRepository().save(entity2);
		service.getRepository().save(entity3);

		Page<PedidoProduto> entitys = service.findAll(Pageable.ofSize(2));

		assertEquals(entitys.getTotalElements(), 3L);
		assertEquals(entitys.getTotalPages(), 2);
		// assertEquals(entitys.getContent().get(0).getNome(), "PedidoProduto teste1");

		service.getRepository().deleteAll();
	}

	@Test
	public void DeletarPedidoProduto() {

		Produto produto = new Produto(-1L, "Produto teste", "Descrição produto", BigDecimal.valueOf(29), Boolean.TRUE,
				null);

		PedidoProduto entity = new PedidoProduto(-1L, BigDecimal.valueOf(30), produto, null);

		service.getRepository().save(entity);

		List<PedidoProduto> entitys = service.getRepository().findAll();
		assertEquals(entitys.size(), 1);

		service.deleteById(entitys.get(0).getId());

		entitys = service.getRepository().findAll();
		assertEquals(entitys.size(), 0);
	}
}
