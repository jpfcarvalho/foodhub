package br.edu.unicesumar.foodhub.dto.sign;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class SignUp {

	@NotNull
	private String username;

	@Length(min = 6, max = 20)
	private String password;

}
