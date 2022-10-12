package br.edu.unicesumar.foodhub;

import java.math.BigDecimal;
import java.util.Set;

import br.edu.unicesumar.foodhub.config.auth.Roles;
import br.edu.unicesumar.foodhub.domain.Categoria;
import br.edu.unicesumar.foodhub.domain.Cidade;
import br.edu.unicesumar.foodhub.domain.Endereco;
import br.edu.unicesumar.foodhub.domain.Estado;
import br.edu.unicesumar.foodhub.domain.Funcionamento;
import br.edu.unicesumar.foodhub.domain.Grupo;
import br.edu.unicesumar.foodhub.domain.Produto;
import br.edu.unicesumar.foodhub.domain.Restaurante;
import br.edu.unicesumar.foodhub.domain.Users;
import br.edu.unicesumar.foodhub.domain.embedded.Coordenadas;

public final class Fixtures {

	public static Endereco createEndereco(Long id) {

		Endereco endereco = new Endereco();
		endereco.setId(id);
		endereco.setNumero("1");
		endereco.setCep("87075444");
		endereco.setLougradouro("Rua Teste");
		endereco.setBairro("Bairro Teste");
		endereco.setCidade(createCidade(id));
		endereco.setCoordenadas(new Coordenadas(1L, 1L));

		return endereco;
	}

	public static Cidade createCidade(Long id) {

		Cidade cidade = new Cidade();
		cidade.setId(id);
		cidade.setNome("Cidade Teste");
		cidade.setEstado(createEstado(id));

		return cidade;
	}

	public static Estado createEstado(Long id) {

		Estado estado = new Estado();
		estado.setId(id);
		estado.setNome("Estado Teste");
		estado.setUf("ET");

		return estado;
	}

	public static Users createUsers(Long id) {

		Users users = new Users();
		users.setId(id);
		users.setRoles(Set.of(Roles.ROLE_CLIENTE));
		users.setEmail(id + "Email@teste.com");
		users.setUsername(id + "Username");
		users.setPassword("password");

		return users;
	}

	public static Categoria createCategoria(Long id) {

		Categoria categoria = new Categoria();
		categoria.setId(id);
		categoria.setTipo("Tipo Teste");

		return categoria;
	}

	public static Funcionamento createFuncionamento(Long id) {

		Funcionamento funcionamento = new Funcionamento();
		funcionamento.setId(id);
		funcionamento.setRaioFuncionamentoKm(1L);

		return funcionamento;
	}

	public static Restaurante createRestaurante(Long id) {

		Restaurante restaurante = new Restaurante();
		restaurante.setId(id);
		restaurante.setNomeFantasia("Restaurante Teste");
		restaurante.setCpfCnpj("99999999999");
		restaurante.setTelefone("44999999999");
		restaurante.setEndereco(createEndereco(id));
		restaurante.setCategoria(createCategoria(id));
		restaurante.setUsers(createUsers(id));
		restaurante.setFuncionamento(createFuncionamento(id));

		return restaurante;
	}

	public static Grupo createGrupo(Long id) {

		Grupo grupo = new Grupo();
		grupo.setId(id);
		grupo.setNome("Grupo Teste");
		grupo.setRestaurante(createRestaurante(id));

		return grupo;
	}

	public static Produto createProduto(Long id) {

		Produto produto = new Produto();
		produto.setId(id);
		produto.setNome("Produto Teste");
		produto.setValor(BigDecimal.TEN);
		produto.setGrupo(createGrupo(id));

		return produto;
	}

}
