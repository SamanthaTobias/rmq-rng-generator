package io.github.samanthatobias.messaging.rabbitmq.rabbitmqrandomnumbergenerator.controller;

import io.github.samanthatobias.messaging.rabbitmq.rabbitmqrandomnumbergenerator.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NumberController {

	@Autowired
	private NumberService numberService;

	@GetMapping("/start")
	public ResponseEntity<?> start() {
		numberService.startGenerating();
		return ResponseEntity.ok().build();
	}

	@GetMapping("/end")
	public ResponseEntity<?> end() {
		numberService.stopGenerating();
		return ResponseEntity.ok().build();
	}

	@PostMapping("/setPeriod")
	public ResponseEntity<?> setPeriod(@RequestParam int seconds) {
		try {
			numberService.setPeriod(seconds);
			return ResponseEntity.ok().build();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return ResponseEntity.unprocessableEntity().build();
		}
	}

}
