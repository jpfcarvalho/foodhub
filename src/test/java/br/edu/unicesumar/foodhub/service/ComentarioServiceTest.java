package br.edu.unicesumar.foodhub.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

import br.edu.unicesumar.foodhub.domain.Comentario;
import br.edu.unicesumar.foodhub.domain.Pessoa;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@Rollback
public class ComentarioServiceTest {

	@Autowired
	private ComentarioService service;

	@Test
	public void salvarComentario() {

		Comentario entity = new Comentario(-1L, "Comentario texto", BigDecimal.valueOf(4), LocalDateTime.now(),
				new Pessoa());

		Comentario newEntity = service.save(entity);

		assertEquals(newEntity.getTexto(), "Comentario texto");

		service.getRepository().deleteAll();

	}

	@Test
	public void BuscarTodosComentarios() {

		Comentario entity1 = new Comentario(-1L, "Comentario texto otimo", BigDecimal.valueOf(4), LocalDateTime.now(),
				new Pessoa());

		Comentario entity2 = new Comentario(-1L, "Comentario texto2 ruim", BigDecimal.valueOf(2), LocalDateTime.now(),
				new Pessoa());

		Comentario entity3 = new Comentario(-1L, "Comentario texto3 Bom", BigDecimal.valueOf(3), LocalDateTime.now(),
				new Pessoa());

		service.getRepository().save(entity1);
		service.getRepository().save(entity2);
		service.getRepository().save(entity3);

		Page<Comentario> entitys = service.findAll(Pageable.ofSize(2));

		assertEquals(entitys.getTotalElements(), 3L);
		assertEquals(entitys.getTotalPages(), 2);
		assertEquals(entitys.getContent().get(0).getTexto(), "Comentario teste1 otimo");

		service.getRepository().deleteAll();
	}

	@Test
	public void DeletarComentario() {

		Comentario entity1 = new Comentario(-1L, "Comentario texto", BigDecimal.valueOf(4), LocalDateTime.now(),
				new Pessoa());
		service.getRepository().save(entity1);

		List<Comentario> entitys = service.getRepository().findAll();
		assertEquals(entitys.size(), 1);

		service.deleteById(entitys.get(0).getId());

		entitys = service.getRepository().findAll();
		assertEquals(entitys.size(), 0);
	}
}
