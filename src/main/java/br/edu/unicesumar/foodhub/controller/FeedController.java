package br.edu.unicesumar.foodhub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.foodhub.dto.FeedDTO;
import br.edu.unicesumar.foodhub.service.FeedService;

@RestController
@RequestMapping("/api/feed")
public class FeedController {

	@Autowired
	private FeedService Service;

	@GetMapping
	public ResponseEntity<List<FeedDTO>> signIn() {

		return ResponseEntity.ok(Service.getFeed());
	}

}
