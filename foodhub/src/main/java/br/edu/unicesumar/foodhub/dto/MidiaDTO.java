package br.edu.unicesumar.foodhub.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MidiaDTO {

	@NotEmpty
	private String titulo;

	private String descricao;

	private Boolean ativo = Boolean.TRUE;

	private List<String> tags = new ArrayList<>();

	private LocalDateTime dataPublicacao = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

	private Boolean fotoPrincipal = Boolean.TRUE;

	@NotNull
	private Long idProduto;

	@NotNull
	private MultipartFile midia;

}
