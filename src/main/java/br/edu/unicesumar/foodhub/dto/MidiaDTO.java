package br.edu.unicesumar.foodhub.dto;

import java.time.LocalDateTime;
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

	private List<String> tags;

	private LocalDateTime dataPublicacao = LocalDateTime.now();

	private Boolean fotoPrincipal = Boolean.TRUE;

	@NotNull
	private Long idProduto;

	@NotNull
	private MultipartFile midia;

}