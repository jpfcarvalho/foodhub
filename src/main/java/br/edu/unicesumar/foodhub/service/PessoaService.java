package br.edu.unicesumar.foodhub.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import br.edu.unicesumar.foodhub.domain.Pessoa;

@Primary
@Service
public class PessoaService extends UsersService<Pessoa> {

}
