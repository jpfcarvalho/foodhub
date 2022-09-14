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

import br.edu.unicesumar.foodhub.domain.ProdutoComplemento;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@Rollback
public class ProdutoComplementoServiceTest {
	@Autowired
	private ProdutoComplementoService service;

	@Test
	public void salvarProdutoComplemento() {

		ProdutoComplemento entity = new ProdutoComplemento(-1L, "ProdutoComplemento teste1",
				"ProdutoComplemento descricao1", BigDecimal.valueOf(18.90), 1L, 5L, Boolean.TRUE);

		ProdutoComplemento newEntity = service.save(entity);

		assertEquals(newEntity.getNome(), "Complemento teste");

		service.getRepository().deleteAll();

	}

	@Test
	public void BuscarTodosProdutosComplemento() {

		ProdutoComplemento entity1 = new ProdutoComplemento(-1L, "ProdutoComplemento teste1",
				"ProdutoComplemento descricao1", BigDecimal.valueOf(18.90), 1L, 5L, Boolean.TRUE);
		ProdutoComplemento entity2 = new ProdutoComplemento(-1L, "ProdutoComplemento teste2",
				"ProdutoComplemento descricao2", BigDecimal.valueOf(11.90), 2L, 5L, Boolean.TRUE);
		ProdutoComplemento entity3 = new ProdutoComplemento(-1L, "ProdutoComplemento teste3",
				"ProdutoComplemento descricao3", BigDecimal.valueOf(10.90), 0L, 2L, Boolean.TRUE);

		service.getRepository().save(entity1);
		service.getRepository().save(entity2);
		service.getRepository().save(entity3);

		Page<ProdutoComplemento> entitys = service.findAll(Pageable.ofSize(2));

		assertEquals(entitys.getTotalElements(), 3L);
		assertEquals(entitys.getTotalPages(), 2);
		assertEquals(entitys.getContent().get(0).getNome(), "ProdutoComplemento teste1");

		service.getRepository().deleteAll();
	}

	@Test
	public void DeletarProdutoComplemento() {

		ProdutoComplemento entity = new ProdutoComplemento(-1L, "ProdutoComplemento teste3",
				"ProdutoComplemento descricao3", BigDecimal.valueOf(10.90), 0L, 2L, Boolean.TRUE);

		service.getRepository().save(entity);

		List<ProdutoComplemento> entitys = service.getRepository().findAll();
		assertEquals(entitys.size(), 1);

		service.deleteById(entitys.get(0).getId());

		entitys = service.getRepository().findAll();
		assertEquals(entitys.size(), 0);
	}
}
