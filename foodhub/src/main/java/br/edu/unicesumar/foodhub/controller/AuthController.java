package br.edu.unicesumar.foodhub.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.foodhub.config.auth.jwt.Jwt;
import br.edu.unicesumar.foodhub.dto.sign.SignIn;
import br.edu.unicesumar.foodhub.service.UsersService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UsersService usersService;

	@PostMapping("/signin")
	public ResponseEntity<Jwt> signIn(@Valid @RequestBody SignIn signIn) {
		return ResponseEntity.ok(usersService.signIn(signIn));
	}

}
