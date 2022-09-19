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

import br.edu.unicesumar.foodhub.domain.PedidoComplementoProduto;
import br.edu.unicesumar.foodhub.domain.ProdutoComplemento;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@Rollback
public class PedidoComplementoProdutoServiceTest {

	@Autowired
	private PedidoComplementoProdutoService service;

	@Test
	public void salvarPedidoComplementoProduto() {

		ProdutoComplemento entityProduto = new ProdutoComplemento(-1L, "ProdutoComplemento teste1",
				"ProdutoComplemento descricao1", BigDecimal.valueOf(18.90), 1L, 5L, Boolean.TRUE);

		PedidoComplementoProduto entity = new PedidoComplementoProduto(-1L, BigDecimal.valueOf(2), (long) 42,
				entityProduto);

		PedidoComplementoProduto newEntity = service.save(entity);

		// assertEquals(newEntity.getId(), "PedidoComplementoProduto teste");
		// linha desnecessária?

		service.getRepository().deleteAll();

	}

	@Test
	public void BuscarTodosPedidoComplementoProdutos() {

		ProdutoComplemento entityProduto1 = new ProdutoComplemento(-1L, "ProdutoComplemento teste1",
				"ProdutoComplemento descricao1", BigDecimal.valueOf(18.90), 1L, 5L, Boolean.TRUE);

		PedidoComplementoProduto entity1 = new PedidoComplementoProduto(-1L, BigDecimal.valueOf(2), (long) 42,
				entityProduto1);

		ProdutoComplemento entityProduto2 = new ProdutoComplemento(-1L, "ProdutoComplemento teste2",
				"ProdutoComplemento descricao1", BigDecimal.valueOf(18.90), 1L, 5L, Boolean.TRUE);

		PedidoComplementoProduto entity2 = new PedidoComplementoProduto(-1L, BigDecimal.valueOf(2), (long) 42,
				entityProduto2);

		ProdutoComplemento entityProduto3 = new ProdutoComplemento(-1L, "ProdutoComplemento teste3",
				"ProdutoComplemento descricao1", BigDecimal.valueOf(18.90), 1L, 5L, Boolean.TRUE);

		PedidoComplementoProduto entity3 = new PedidoComplementoProduto(-1L, BigDecimal.valueOf(2), (long) 42,
				entityProduto3);

		service.getRepository().save(entity1);
		service.getRepository().save(entity2);
		service.getRepository().save(entity3);

		Page<PedidoComplementoProduto> entitys = service.findAll(Pageable.ofSize(2));

		assertEquals(entitys.getTotalElements(), 3L);
		assertEquals(entitys.getTotalPages(), 2);
		// assertEquals(entitys.getContent().get(0).getNome(), "PedidoComplementoProduto
		// teste1");

		service.getRepository().deleteAll();
	}

	@Test
	public void DeletarPedidoComplementoProduto() {

		ProdutoComplemento entityProdutoComplemento = new ProdutoComplemento(-1L, "ProdutoComplemento teste3",
				"ProdutoComplemento descricao1", BigDecimal.valueOf(18.90), 1L, 5L, Boolean.TRUE);

		PedidoComplementoProduto entity = new PedidoComplementoProduto(-1L, BigDecimal.valueOf(2), (long) 42,
				entityProdutoComplemento);

		service.getRepository().save(entity);

		List<PedidoComplementoProduto> entitys = service.getRepository().findAll();
		assertEquals(entitys.size(), 1);

		service.deleteById(entitys.get(0).getId());

		entitys = service.getRepository().findAll();
		assertEquals(entitys.size(), 0);
	}
}
