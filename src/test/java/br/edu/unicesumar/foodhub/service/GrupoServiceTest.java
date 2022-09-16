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

import br.edu.unicesumar.foodhub.domain.Grupo;
import br.edu.unicesumar.foodhub.domain.Restaurante;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@Rollback
public class GrupoServiceTest {

	@Autowired
	private GrupoService service;

	@Test
	public void salvarGrupo() {

		Grupo entity = new Grupo(-1L, "Grupo teste", null, new Restaurante());

		Grupo newEntity = service.save(entity);

		assertEquals(newEntity.getNome(), "Grupo teste");

		service.getRepository().deleteAll();

	}

	@Test
	public void BuscarTodosGrupos() {

		Grupo entity1 = new Grupo(-1L, "Grupo teste1", null, new Restaurante());
		Grupo entity2 = new Grupo(-1L, "Grupo teste2", null, new Restaurante());
		Grupo entity3 = new Grupo(-1L, "Grupo teste3", null, new Restaurante());

		service.getRepository().save(entity1);
		service.getRepository().save(entity2);
		service.getRepository().save(entity3);

		Page<Grupo> entitys = service.findAll(Pageable.ofSize(2));

		assertEquals(entitys.getTotalElements(), 3L);
		assertEquals(entitys.getTotalPages(), 2);
		assertEquals(entitys.getContent().get(0).getNome(), "Grupo teste1");

		service.getRepository().deleteAll();
	}

	@Test
	public void DeletarGrupo() {

		Grupo entity = new Grupo(1L, "Grupo teste", null, new Restaurante());
		service.getRepository().save(entity);

		List<Grupo> entitys = service.getRepository().findAll();
		assertEquals(entitys.size(), 1);

		service.deleteById(entitys.get(0).getId());

		entitys = service.getRepository().findAll();
		assertEquals(entitys.size(), 0);
	}
}
