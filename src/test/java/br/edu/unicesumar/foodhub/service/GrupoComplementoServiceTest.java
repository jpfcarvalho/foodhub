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

import br.edu.unicesumar.foodhub.domain.GrupoComplemento;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@Rollback
public class GrupoComplementoServiceTest {

	@Autowired
	private GrupoComplementoService service;

	@Test
	public void salvarGrupoComplemento() {

		GrupoComplemento entity = new GrupoComplemento(-1L, "GrupoComplemento teste1", Boolean.TRUE, 1L, 5L, null);

		GrupoComplemento newEntity = service.save(entity);

		assertEquals(newEntity.getNome(), "Complemento teste");

		service.getRepository().deleteAll();

	}

	@Test
	public void BuscarTodosGrupoComplemento() {

		GrupoComplemento entity1 = new GrupoComplemento(-1L, "GrupoComplemento teste1", Boolean.TRUE, 1L, 5L, null);
		GrupoComplemento entity2 = new GrupoComplemento(-1L, "GrupoComplemento teste2", Boolean.TRUE, 2L, 10L, null);
		GrupoComplemento entity3 = new GrupoComplemento(-1L, "GrupoComplemento teste3", Boolean.TRUE, 0L, 11L, null);

		service.getRepository().save(entity1);
		service.getRepository().save(entity2);
		service.getRepository().save(entity3);

		Page<GrupoComplemento> entitys = service.findAll(Pageable.ofSize(2));

		assertEquals(entitys.getTotalElements(), 3L);
		assertEquals(entitys.getTotalPages(), 2);
		assertEquals(entitys.getContent().get(0).getNome(), "GrupoComplemento teste1");

		service.getRepository().deleteAll();
	}

	@Test
	public void DeletarGrupoComplemento() {

		GrupoComplemento entity = new GrupoComplemento(-1L, "GrupoComplemento teste1", Boolean.TRUE, 1L, 5L, null);

		service.getRepository().save(entity);

		List<GrupoComplemento> entitys = service.getRepository().findAll();
		assertEquals(entitys.size(), 1);

		service.deleteById(entitys.get(0).getId());

		entitys = service.getRepository().findAll();
		assertEquals(entitys.size(), 0);
	}
}
