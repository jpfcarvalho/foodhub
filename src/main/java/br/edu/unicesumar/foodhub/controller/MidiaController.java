package br.edu.unicesumar.foodhub.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.foodhub.base.CrudController;
import br.edu.unicesumar.foodhub.domain.Midia;
import br.edu.unicesumar.foodhub.dto.MidiaDTO;
import br.edu.unicesumar.foodhub.service.MidiaService;

@RestController
@RequestMapping("/api/midias")
public class MidiaController extends CrudController<Midia> {

	@Autowired
	private MidiaService midiaService;

	@PostMapping("/arquivo")
	public ResponseEntity<Midia> saveArquivo(@Valid @RequestBody MidiaDTO entityDTO) throws URISyntaxException {

		Midia newMidia = midiaService.saveMidia(entityDTO);
		return ResponseEntity.created(new URI("/" + newMidia.getId())).body(newMidia);

	}

	@GetMapping("/download/{caminho}")
	public ResponseEntity<ByteArrayResource> downloadMidia(@PathVariable(name = "caminho") String caminho) {

		ByteArrayResource arquivo = new ByteArrayResource(midiaService.downloadMidia(caminho));
		return ResponseEntity.ok().contentLength(arquivo.contentLength())
				.header("Content-type", "application/octet-stream")
				.header("Content-disposition", "attachment; filename=\"" + caminho + "\"").body(arquivo);
	}

}
