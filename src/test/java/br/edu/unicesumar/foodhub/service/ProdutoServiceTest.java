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

import br.edu.unicesumar.foodhub.domain.Produto;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@Rollback
public class ProdutoServiceTest {

	@Autowired
	private ProdutoService service;

	@Test
	public void salvarProduto() {

		Produto entity = new Produto(-1L, "Produto teste3", "Produto descricao3", BigDecimal.valueOf(31.90), true);

		Produto newEntity = service.save(entity);

		assertEquals(newEntity.getNome(), "Produto teste");

		service.getRepository().deleteAll();

	}

	@Test
	public void BuscarTodosGrupos() {

		Produto entity1 = new Produto(-1L, "Produto teste1", "Produto descricao1", BigDecimal.valueOf(18.90), true);
		Produto entity2 = new Produto(-1L, "Produto teste2", "Produto descricao2", BigDecimal.valueOf(20.90), true);
		Produto entity3 = new Produto(-1L, "Produto teste3", "Produto descricao3", BigDecimal.valueOf(31.90), true);

		service.getRepository().save(entity1);
		service.getRepository().save(entity2);
		service.getRepository().save(entity3);

		Page<Produto> entitys = service.findAll(Pageable.ofSize(2));

		assertEquals(entitys.getTotalElements(), 3L);
		assertEquals(entitys.getTotalPages(), 2);
		assertEquals(entitys.getContent().get(0).getNome(), "Produto teste1");

		service.getRepository().deleteAll();
	}

	@Test
	public void DeletarProduto() {

		Produto entity = new Produto(-1L, "Produto teste3", "Produto descricao3", BigDecimal.valueOf(31.90), true);

		service.getRepository().save(entity);

		List<Produto> entitys = service.getRepository().findAll();
		assertEquals(entitys.size(), 1);

		service.deleteById(entitys.get(0).getId());

		entitys = service.getRepository().findAll();
		assertEquals(entitys.size(), 0);
	}
}
